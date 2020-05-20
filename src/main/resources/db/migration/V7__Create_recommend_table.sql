-- auto-generated definition
create table recommend
(
    id            bigint auto_increment
        primary key,
    title         varchar(255)     not null,
    description   text             not null,
    tag           varchar(512)     not null,
    gmt_create    bigint           not null,
    gmt_modified  bigint           not null,
    creator       bigint           not null,
    view_count    bigint default 0 not null,
    like_count    bigint default 0 not null,
    comment_count bigint default 0 not null
);

