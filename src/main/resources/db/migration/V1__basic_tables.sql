create table if not exists author
(
    id          bigserial primary key,
    name        varchar(70),
    surname     varchar(70),
    patronymic  varchar(70),
    birth_date  date,
    description varchar(512),
    website     varchar(255)
);

create table if not exists publisher
(
    id              bigserial primary key,
    name            varchar(255),
    foundation_date date,
    website         varchar(255),
    email           varchar(255),
    phone_number    varchar(20),
    description     varchar(512)
);

create table if not exists book
(
    id               bigserial primary key,
    title            varchar(255),
    description      varchar(512),
    publication_date date,
    genre            varchar(255),
    pages            int,
    isbn             varchar(25),
    language         varchar(15),

    author_id        bigserial
        constraint FK_book_author_id references author,
    publisher_id     bigserial
        constraint FK_book_publisher_id references publisher
);

create table if not exists authors_books
(
    author_id bigserial,
    book_id   bigserial
);

alter table if exists authors_books
    add constraint FK_authors_books_author_id foreign key (author_id) references author;
alter table if exists authors_books
    add constraint FK_authors_books_book_id foreign key (book_id) references book;

create table if not exists publishers_books
(
    publisher_id bigserial,
    book_id      bigserial
);

alter table if exists publishers_books
    add constraint FK_publishers_books_publisher_id foreign key (publisher_id) references publisher;
alter table if exists publishers_books
    add constraint FK_publishers_books_book_id foreign key (book_id) references book;
