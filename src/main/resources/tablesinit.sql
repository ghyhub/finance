CREATE TABLE weworkconversetable(
    `msgid` VARCHAR(50) PRIMARY KEY,
    `action` VARCHAR(10),
    `from` VARCHAR(50),
    `tolist` VARCHAR(2500),
    `roomid` VARCHAR(50),
    `msgtime` VARCHAR(20),
    `msgtype` VARCHAR(20),
    `time` VARCHAR(20),
    `user` VARCHAR(50)
);

CREATE TABLE agreemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `userid` VARCHAR(50),
    `agree_time` VARCHAR(20),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE calendarmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(50),
    `creator` VARCHAR(50),
    `attendeelist` VARCHAR(2500),
    `starttime` VARCHAR(20),
    `endtime` VARCHAR(20),
    `place` VARCHAR(50),
    `remark` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE cardmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `corpname` VARCHAR(50),
    `userid` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);
 
CREATE TABLE chatrecordmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE chatrecorditemtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `type` VARCHAR(20),
    `msgtime` VARCHAR(20),
    `fromchatroom` VARCHAR(10),
    FOREIGN KEY(msgid) REFERENCES chatrecordmsgtable(msgid)
);

CREATE TABLE collectmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `roomname` VARCHAR(20),
    `creator` VARCHAR(50),
    `createtime` VARCHAR(20),
    `title` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE collectitemtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(50),
    `id` VARCHAR(5),
    `que` VARCHAR(20),
    `type` VARCHAR(10),
    FOREIGN KEY(msgid) REFERENCES collectmsgtable(msgid)
);

CREATE TABLE docmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(20),
    `link_url` VARCHAR(100),
    `creator` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE emotionmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filename` VARCHAR(50),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE externalredpacketmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `type` VARCHAR(5),
    `wish` VARCHAR(100),
    `totalcnt` VARCHAR(10),
    `totalamount` VARCHAR(10),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE filemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filename` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE imagemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE linkmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(20),
    `description` VARCHAR(100),
    `link_url` VARCHAR(100),
    `image_url` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE locationmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `longitude` VARCHAR(15),
    `latitude` VARCHAR(15),
    `adress` VARCHAR(100),
    `title` VARCHAR(20),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE markdownmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `content` VARCHAR(500),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE meetingmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `topic` VARCHAR(20),
    `starttime` VARCHAR(20),
    `endtime` VARCHAR(20),
    `address` VARCHAR(100),
    `remarks` VARCHAR(50),
    `meetingtype` VARCHAR(5),
    `meetingid` VARCHAR(50),
    `status` VARCHAR(5),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE meetingvoicecalltable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `voiceid` VARCHAR(50),
    `endtime` VARCHAR(20),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE meetingdemofiledatatable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filename` VARCHAR(50),
    `demooperator` VARCHAR(50),
    `starttime` VARCHAR(20),
    `endtime` VARCHAR(20),
    FOREIGN KEY(msgid) REFERENCES meetingvoicecalltable(msgid)
);

CREATE TABLE meetingsharescreendatatable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `share` VARCHAR(50),
    `starttime` VARCHAR(20),
    `endtime` VARCHAR(20),
    FOREIGN KEY(msgid) REFERENCES meetingvoicecalltable(msgid)
);

CREATE TABLE mixedmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE mixeditemtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `type` VARCHAR(10),
    FOREIGN KEY(msgid) REFERENCES mixedmsgtable(msgid)
);

CREATE TABLE newsmsgitemtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE newsitemtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(20),
    `description` VARCHAR(100),
    `url` VARCHAR(100),
    `picurl` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES newsmsgitemtable(msgid)
);

CREATE TABLE redpacketmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `type` VARCHAR(5),
    `wish` VARCHAR(100),
    `totalcnt` VARCHAR(5),
    `totalamount` VARCHAR(5),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE revokemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `pre_msgid` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE textmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `content` VARCHAR(1500),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE todomsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(20),
    `content` VARCHAR(200),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE videomsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filepath` VARCHAR(100),
    `play_lenth` VARCHAR(10),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE voicemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filepath` VARCHAR(100),
    `play_lenth` VARCHAR(10),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE voipdocsharemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE votemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(10),
    `item` VARCHAR(200),
    `type` VARCHAR(5),
    `id` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE weappmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(10),
    `description` VARCHAR(100),
    `username` VARCHAR(20),
    `displayname` VARCHAR(20),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
)
