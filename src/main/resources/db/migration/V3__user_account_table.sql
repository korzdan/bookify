create table if not exists user_account (
    id bigserial primary key,
    email varchar(255),
    password varchar(255),
    is_enabled boolean,
    role varchar(15)
);
