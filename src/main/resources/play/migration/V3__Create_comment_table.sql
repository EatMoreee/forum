-- auto-generated definition
create table comment
(
    id            bigint auto_increment
        primary key,
    parent_id     bigint           not null,
    type          bigint           not null,
    commentator   bigint           not null,
    gmt_create    bigint           not null,
    gmt_modified  bigint           not null,
    like_count    bigint default 0 not null,
    content       varchar(1024)    null,
    comment_count bigint default 0 null
);

