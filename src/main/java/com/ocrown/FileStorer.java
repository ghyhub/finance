package com.ocrown;

import java.io.FileOutputStream;
import java.io.File;

import com.tencent.wework.Finance;

public class FileStorer {
    String storefile;
    DataFetcher df;
    long sdk;
    FileStorer(DataFetcher df,String storefile){
        this.storefile=storefile;
        this.df=df;
        sdk=df.sdk;
    }
    public String saveFile(String name,String sdkfileid){
        // 媒体文件每次拉取的最大size为512k，因此超过512k的文件需要分片拉取。若该文件未拉取完整，sdk的IsMediaDataFinish接口会返回0，同时通过GetOutIndexBuf接口返回下次拉取需要传入GetMediaData的indexbuf。
        // indexbuf一般格式如右侧所示，”Range:bytes=524288-1048575“，表示这次拉取的是从524288到1048575的分片。单个文件首次拉取填写的indexbuf为空字符串，拉取后续分片时直接填入上次返回的indexbuf即可。
        long ret=0;
        int timeout = 5;
        String proxy=df.proxy;
        String passwd=df.passwd;
        String indexbuf = "";
        String savefile = storefile+name;
        while (true) {
            // 每次使用GetMediaData拉取存档前需要调用NewMediaData获取一个media_data，在使用完media_data中数据后，还需要调用FreeMediaData释放。
            long media_data = Finance.NewMediaData();
            ret = Finance.GetMediaData(sdk, indexbuf, sdkfileid, proxy, passwd, timeout, media_data);
            if (ret != 0) {
                System.out.println("getmediadata ret:" + ret);
                Finance.FreeMediaData(media_data);
                return null;
            }
            System.out.printf("getmediadata outindex len:%d, data_len:%d, is_finis:%d\n",
                    Finance.GetIndexLen(media_data), Finance.GetDataLen(media_data),
                    Finance.IsMediaDataFinish(media_data));
            try {
                // 大于512k的文件会分片拉取，此处需要使用追加写，避免后面的分片覆盖之前的数据。
                FileOutputStream outputStream = new FileOutputStream(new File(savefile), true);
                outputStream.write(Finance.GetData(media_data));
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (Finance.IsMediaDataFinish(media_data) == 1) {
                // 已经拉取完成最后一个分片
                Finance.FreeMediaData(media_data);
                break;
            } else {
                // 获取下次拉取需要使用的indexbuf
                indexbuf = Finance.GetOutIndexBuf(media_data);
                Finance.FreeMediaData(media_data);
            }
        }
        return savefile;
    }
}
