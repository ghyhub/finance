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

CREATE TABLE textmsgtable(
    `msgid` VARCHAR(50),
    `content` VARCHAR(1500),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE imagemsgtable(
    `msgid` VARCHAR(50),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
);

CREATE TABLE filemsgtable(
    `msgid` VARCHAR(50),
    `filepath` VARCHAR(100),
    FOREIGN KEY(msgid) REFERENCES weworkconversetable(msgid)
)