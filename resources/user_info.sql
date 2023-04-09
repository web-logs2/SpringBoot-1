create table 'user_info'
(
    'id' int(11) NOT NULL AUTO_INCREMENT,
    'wallet_id' varchar(255) DEFAULT NULL,
    'nick_name' varchar(255) DEFAULT NULL,
    'profile_picture' varchar(255) DEFAULT NULL,
    'email' varchar(255) DEFAULT NULL,
    'desc' varchar(255) DEFAULT NULL,
     PRIMARY KEY ('id')
);

create table  'subscribe_user_info'
(
    'id' int(11)       NOT NULL AUTO_INCREMENT,
    'subscriber_wallet' varchar(255) DEFAULT NULL,
    'subscribee_wallet' varchar(255) DEFAULT NULL,
    PRIMARY KEY ('id')
)

create table  'favorite_info'
(
    'id' int(11)       NOT NULL AUTO_INCREMENT,
    'subscriber_wallet' varchar(255) DEFAULT NULL,
    'activity_id'       varchar(255) DEFAULT NULL,
    'subscribee_wallet' varchar(255) DEFAULT NULL,
    PRIMARY KEY ('id')
)

