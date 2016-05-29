CREATE TABLE message 
( 
  msg_id VARCHAR(32) NOT NULL 
, from_id CHAR(8) NOT NULL 
, to_id CHAR(8) NOT NULL 
, content VARCHAR(255) NULL 
, is_send VARCHAR(1) NOT NULL 
, date_time VARCHAR(32) NOT NULL  
, CONSTRAINT PRIMARY KEY ( msg_id ) );