create table if not exists user_account
(
    id         bigserial primary key,
    name       varchar(255),
    surname    varchar(255),
    email      varchar(255),
    password   varchar(255),
    is_enabled boolean,
    role       varchar(255),
    orders_num integer default 0
);

create table genre
(
    id   bigserial primary key,
    name varchar(50)
);

create table if not exists book
(
    id               bigserial primary key,
    title            varchar(255),
    description      varchar(512),
    publication_date date,
    genre_id         bigserial,
    pages            int,
    isbn             varchar(25),
    language         varchar(15),
    storage_num      integer          default 10,
    order_num        integer          default 0,
    author           varchar(255),
    publisher        varchar(255),
    price            double precision default 40.0,

    foreign key (genre_id) references genre (id)
);

create table if not exists user_order
(
    id          bigserial primary key,
    user_id     bigserial,
    status      varchar(15),
    comment     varchar(255),
    address     varchar(255),
    total_price double precision,

    foreign key (user_id) references user_account (id)
);

create table if not exists user_orders_books
(
    order_id bigserial,
    book_id  bigserial,

    foreign key (order_id) references user_order (id),
    foreign key (book_id) references book (id)
);

create table if not exists statistics
(
    id bigserial primary key,
    orders_num bigint,
    users_num bigint,
    books_num bigint
);

