create table token_info
(
    id               int unsigned auto_increment comment '用户编号'
        primary key,
    token_id         varchar(255) not null comment 'tokenID',
    contract_address varchar(255) not null comment '合约地址',
    owner            varchar(255) not null comment '所有者',
    nftStandard_id   int(255) not null comment '合约类型0：721；1：1155',
    image            varchar(255) not null comment 'token image',
    name             varchar(255) not null comment 'name',
    `desc`           varchar(255) not null comment '描述',
    create_time      timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);
create table raffle_info
(
    id               int unsigned auto_increment comment '用户编号'
        primary key,
    raffleAddress    varchar(255) not null comment 'Raffle 地址唯一',
    owner            varchar(255) not null comment '所有者',
    token_id         varchar(255) not null comment 'tokenID',
    contract_address varchar(255) not null comment '合约地址',
    tickets          int(255) not null comment ' tickets 销售总份数',
    ticketPrice      varchar(255) not null comment ' tickets 单价',
    startTimestamp   bigint(13) not null comment '抽奖活动开始时间（时间戳）',
    endTimestamp     bigint(13) not null comment '抽奖活动结束时间（时间戳）',
    raffleStatus     int(10) not null comment ' 抽奖活动状态',
    create_time      timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);
CREATE TABLE RaffleInfo (
                            id INT NOT NULL AUTO_INCREMENT, -- 自增ID，主键，非空
                            raffle_address VARCHAR(255) NOT NULL, -- 抽奖地址唯一，字符串类型，非空
                            owner VARCHAR(255) NOT NULL, -- 所有者，字符串类型，非空
                            token_id VARCHAR(255) NOT NULL, -- TokenId，字符串类型，非空
                            contract_address VARCHAR(255) NOT NULL, -- 合约地址，字符串类型，非空
                            tickets INT UNSIGNED NOT NULL, -- 销售总份数，整数类型，无符号，非空
                            ticket_price DECIMAL(10,2) NOT NULL, -- 单价，DECIMAL类型，总共10位，其中小数位2位，非空
                            start_timestamp TIMESTAMP NOT NULL, -- 抽奖活动开始时间，时间戳类型，非空
                            end_timestamp TIMESTAMP NOT NULL, -- 抽奖活动结束时间，时间戳类型，非空
                            raffle_status INT NOT NULL, -- 抽奖活动状态，整数类型，非空
                            create_time TIMESTAMP NOT NULL, -- 创建时间，时间戳类型，非空
                            update_time TIMESTAMP NOT NULL, -- 更新时间，时间戳类型，非空
                            PRIMARY KEY (id), -- 设置主键
                            UNIQUE KEY raffle_address_unique (raffle_address) -- 设置抽奖地址唯一约束
) COMMEN