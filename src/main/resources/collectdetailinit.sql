CREATE TABLE collectdetail(
    `msgid` VARCHAR(50),
    `id` VARCHAR(50),
    `que` VARCHAR(100),
    `type` VARCHAR(20),

    FOREIGN KEY(msgid) REFERENCES weworkchatdata(msgid)
)