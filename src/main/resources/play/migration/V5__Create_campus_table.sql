-- auto-generated definition
create table campus
(
    id            bigint auto_increment
        primary key,
    title         varchar(255)     not null,
    description   text             not null,
    tag           varchar(255)     not null,
    gmt_create    bigint           not null,
    like_count    bigint default 0 not null,
    view_count    bigint default 0 not null,
    comment_count bigint           not null
);

