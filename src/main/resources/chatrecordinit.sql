CREATE TABLE chatrecord(
    `msgid` VARCHAR(50),
    `type` VARCHAR(20),
    `msgtime` VARCHAR(20),
    `content` VARCHAR(1500),
    `from_chatroom` VARCHAR(10),
    
    FOREIGN KEY(msgid) REFERENCES weworkchatdata(msgid)
)