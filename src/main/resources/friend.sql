CREATE TABLE friend 
( 
  friend_id VARCHAR(32) NOT NULL 
, member_id CHAR(8) NOT NULL 
, friend_member_id CHAR(8) NOT NULL 
, friend_group VARCHAR(16) NOT NULL 
, friend_level VARCHAR(16) NOT NULL 
, CONSTRAINT PRIMARY KEY ( friend_id ) );