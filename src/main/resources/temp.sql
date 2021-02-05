use test;
drop table chatrecord;
drop TABLE weworkchatdata;
#TRUNCATE TABLE weworkchatdata;
#select *from weworkchatdata;
#select *from chatrecord;
#INSERT INTO weworkchatdata (`tolist`,`msgtime`,`msgid`,`action`,`from`,`msgtype`,`roomid`,`content`) SELECT'[wmcOiVDAAAT71iDKJAGZFvLusB9ZLKeQ]','2021-01-31 18:16:19','2457988473653741150/_1612088179/_external','send','ChengXiaoYu','text','','付经理咋差了20块钱' FROM DUAL WHERE NOT EXISTS(SELECT msgid FROM weworkchatdata WHERE `tolist`='[wmcOiVDAAAT71iDKJAGZFvLusB9ZLKeQ]'AND`msgtime`='2021-01-31 18:16:19'AND`msgid`='2457988473653741150_1612088179_external'AND`action`='send'AND`from`='ChengXiaoYu'AND`msgtype`='text'AND`roomid`=''AND`content`='付经理咋差了20块钱');
