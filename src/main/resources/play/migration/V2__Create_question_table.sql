-- auto-generated definition
create table question
(
    id            bigint auto_increment
        primary key,
    title         varchar(50)      null,
    tag           varchar(255)     null,
    description   text             null,
    gmt_create    bigint           null,
    gmt_modified  bigint           null,
    creator       bigint           null,
    comment_count bigint default 0 not null,
    view_count    bigint default 0 not null,
    like_count    bigint default 0 not null
);

