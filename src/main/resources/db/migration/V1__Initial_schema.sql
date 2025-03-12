create table t_user (
    id bigserial primary key,
    identifier varchar(255),
    fio varchar(255)
);

create table t_phone (
    id bigserial primary key,
    phone_number varchar(255) not null,
    user_id bigint not null references t_user(id)
);