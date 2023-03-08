create table if not exists hardware (
    id identity,
    code varchar(100) not null unique,
    name varchar(100) not null,
    type varchar(100) not null,
    stock int not null,
    price numeric not null
);

create table if not exists review (
    id identity,
    title varchar(100) not null unique,
    text varchar(1000) not null,
    rating bigint not null,
    hardware_id bigint,
    constraint fk_hardware foreign key (hardware_id) references hardware(id)
);

create table if not exists user (
    id identity,
    username varchar(100) not null unique,
    password varchar(1000) not null
    );

create table if not exists authority (
    id   identity,
    authority_name varchar(100) not null unique
    );

create table if not exists user_authority (
    user_id      bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
    );