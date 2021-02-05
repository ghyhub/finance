package com.ocrown;

import com.tencent.wework.Finance;
import java.io.File;
import java.io.FileOutputStream;

public class FileDemo {
    public static void main(String[] args) {
        String corpid = "ww092ba56a177e8f0f";
        String secret = "A2toQDniaSRfoOc0pZLrtNAvW1vaIAUhUaDnQ6IvKDE";

        long ret = 0;
        long sdk = Finance.NewSdk();
        ret = Finance.Init(sdk, corpid, secret);

        if (ret != 0) {
            Finance.DestroySdk(sdk);
            System.out.println("init sdk err ret " + ret);
            return;
        }
        // 拉取媒体文件
        String sdkfileid = "Cu8DKjEqRmRZQmNqWWxrWGlNdXlPSkZOaGp1UG9MK04wSVE3MXlKTHpUb"
                + "EFkQ2U2bDBxNDJ1SGdjWHBkV1Blckc3YStYT1hxMlFIUkVFR3hBVUdlaz"
                + "RNb1BYUE84c1JQakF2VVdFdFp6NXJiaHJ3Mjdqc3dvRG5veUlwVk1VU2h"
                + "6UEJvSEJHUGFwVVJJNkhZcTUwOGNiTXBvdTI0enV3am5QNld4OXhzRTRi"
                + "UVdseWJsK2gyYUpwOVpSQjl4OXZtVGErSFNNdVYraitNbU5aSWhBcys5Q"
                + "1BManJ5TlZFSm5uOEQxb05HYXFjcWRSNlo3a0ZYdjRTdVMrQWdkS2FkVGdONndmMkFITldpVnoxV1ZRdUxUbUYyWmFqdWFJc3N0Z284d01rOHY4U0pnSjdSL"
                + "2lUN1QvTTBxbnRUcmN5MGc3Q04vZ2R2Ym52Q1hiR3pPNHpYTGJlTnJobzR6L01GLzVZM3RDTGg3SUEweVZyelF3U3hpazlKeVU2dkV0WmZmdWh2QmtObHAxR"
                + "2F4eGpIZm94Ni9yQStpTTdWQmZkMkVkaEZqVjhKN3pBRmxZSW9ZeEhsVWg3Z2pPU2JSeWhBT0NSY2djWjZrOXZ0OVdZQ2ZBTDZXYmtmYm84aXVGZEdFZmdNQ"
                + "XJXMWRGNVJaNFhqMWM9EjhORGRmTVRZNE9EZzFNemM1Tmpjd056Z3hNRjg1TURnM05ESTVNakZmTVRZeE1UZ3dNelV6TlE9PRogNzg3Njc1Njk2NDZiNzE2NTZkNmM2MzcxNzQ2NTY3Njc=";
        String proxy = "";
        String passwd = "";
        int timeout = 5;
        String savefile = "D:\\图片1";

        // 媒体文件每次拉取的最大size为512k，因此超过512k的文件需要分片拉取。若该文件未拉取完整，sdk的IsMediaDataFinish接口会返回0，同时通过GetOutIndexBuf接口返回下次拉取需要传入GetMediaData的indexbuf。
        // indexbuf一般格式如右侧所示，”Range:bytes=524288-1048575“，表示这次拉取的是从524288到1048575的分片。单个文件首次拉取填写的indexbuf为空字符串，拉取后续分片时直接填入上次返回的indexbuf即可。
        String indexbuf = "";
        while (true) {
            // 每次使用GetMediaData拉取存档前需要调用NewMediaData获取一个media_data，在使用完media_data中数据后，还需要调用FreeMediaData释放。
            long media_data = Finance.NewMediaData();
            ret = Finance.GetMediaData(sdk, indexbuf, sdkfileid, proxy, passwd, timeout, media_data);
            if (ret != 0) {
                System.out.println("getmediadata ret:" + ret);
                Finance.FreeMediaData(media_data);
                return;
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

    }
}
