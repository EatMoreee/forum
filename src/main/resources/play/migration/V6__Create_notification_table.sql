-- auto-generated definition
create table notification
(
    id            bigint auto_increment
        primary key,
    notifier      bigint        not null,
    receiver      bigint        not null,
    area          int           not null,
    type          int           not null,
    gmt_create    bigint        not null,
    status        int default 0 not null,
    outerid       bigint        not null,
    notifier_name varchar(100)  null,
    outer_title   varchar(255)  null
);

