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
)

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
)

CREATE TABLE cardmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `corpname` VARCHAR(50),
    `userid` VARCHAR(50),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
) 
 
CREATE TABLE chatrecordmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `title` VARCHAR(50),
)

CREATE TABLE chatrecorditemtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    
)

CREATE TABLE textmsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `content` VARCHAR(1500),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE imagemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE filemsgtable(
    `msgid` VARCHAR(50),
    `index` VARCHAR(5),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
)