CREATE TABLE weworkchatdata (
    `msgid` VARCHAR(50) PRIMARY KEY,
    `action` VARCHAR(20),
    `from` VARCHAR(50),
    `tolist` VARCHAR(2500),
    `roomid` VARCHAR(50),
    `msgtime` VARCHAR(20),
    `msgtype` VARCHAR(20),
    `content` VARCHAR(1500),
    `filepath` VARCHAR(500),
    `play_length` VARCHAR(50),
    `pre_msgid` VARCHAR(50),
    `corpname` VARCHAR(50),
    `userid` VARCHAR(50),
    `agree_time` VARCHAR(20),

    `longitude` VARCHAR(15),
    `latitude` VARCHAR(15),
    `address` VARCHAR(50),

    `title` VARCHAR(50),
    `description` VARCHAR(100),
    `username` VARCHAR(50),
    `displayname` VARCHAR(50),

    `link_url` VARCHAR(100),
    `image_url` VARCHAR(100),
    `item` VARCHAR(200),

    `room_name` VARCHAR(50),
    `creator` VARCHAR(50),
    `create_time` VARCHAR(20),

    `type` VARCHAR(10),
    `wish` VARCHAR(50),
    `totalcnt` VARCHAR(10),
    `totalamount` VARCHAR(10) COMMENT'红包总金额，单位为分',

    `topic` VARCHAR(50) COMMENT'会议话题',
    `starttime` VARCHAR(20) COMMENT'会议开始时间',
    `endtime` VARCHAR(20) COMMENT'会议结束时间',
    `adress` VARCHAR(100) COMMENT'会议地址',
    `remarks` VARCHAR(50),
    `meetingtype` VARCHAR(10) COMMENT'会议类型。101发起会议邀请消息、102处理会议邀请消息',
    `meetingid` VARCHAR(10) COMMENT'会议id',
    `status` VARCHAR(10) COMMENT'会议邀请处理状态。1 参加会议、2 拒绝会议、3 待定、4 未被邀请、5 会议已取消、6 会议已过期、7 不在房间内。Uint32类型。',
    `place` VARCHAR(100)
);
