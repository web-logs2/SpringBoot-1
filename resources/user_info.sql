create table token_info
(
    id               int unsigned auto_increment comment '用户编号'
        primary key,
    token_id         varchar(255)                        not null comment 'tokenID',
    contract_address varchar(255)                        not null comment '合约地址',
    owner            varchar(255)                        not null comment '所有者',
    nftStandard_id   int(255)                            not null comment '合约类型0：721；1：1155',
    image            varchar(255)                        not null comment 'token image',
    name             varchar(255)                        not null comment 'name',
    `desc`             varchar(255)                        not null comment '描述',
    create_time      timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);
create table raffle_info
(
    id               int unsigned auto_increment comment '用户编号'
        primary key,
    raffleAddress    varchar(255)                        not null comment 'Raffle 地址唯一',
    owner            varchar(255)                        not null comment '所有者',
    token_id         varchar(255)                        not null comment 'tokenID',
    contract_address varchar(255)                        not null comment '合约地址',
    tickets          int(255)                            not null comment ' tickets 销售总份数',
    ticketPrice      varchar(255)                        not null comment ' tickets 单价',
    startTimestamp   bigint(13)                          not null comment '抽奖活动开始时间（时间戳）',
    endTimestamp     bigint(13)                          not null comment '抽奖活动结束时间（时间戳）',
    raffleStatus   int(10)                             not null comment ' 抽奖活动状态',
    create_time      timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);
