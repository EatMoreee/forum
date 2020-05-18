-- auto-generated definition
create table user
(
    id              bigint auto_increment
        primary key,
    account_id      varchar(100)     null,
    name            varchar(50)      null,
    token           char(36)         null,
    gmt_create      bigint           null,
    gmt_modified    bigint           null,
    bio             varchar(255)     null,
    avatar_url      varchar(100)     null,
    grade           int    default 1 not null,
    empirical_value bigint default 0 not null
);

