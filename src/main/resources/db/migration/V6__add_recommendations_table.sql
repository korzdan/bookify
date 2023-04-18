create table genre
(
    id   bigserial primary key,
    name varchar(50)
);

insert into genre
values (1, 'Проза'),
       (2, 'Приключение'),
       (3, 'Поэзия'),
       (4, 'Фэнтези'),
       (5, 'Роман'),
       (6, 'Детектив'),
       (7, 'Нехудожественная литература'),
       (8, 'Ужасы');

alter table book
    drop column genre;
alter table book
    add column genre_id bigserial;
alter table book
    add constraint FK_book_genre_id foreign key (genre_id) references genre;

update book
set genre_id = 1
where id = 1;
update book
set genre_id = 8
where id in (2, 3);

create table user_genre_recommendations
(
    user_account_id bigserial,
    genre_id        bigserial,

    foreign key (user_account_id) references user_account (id),
    foreign key (genre_id) references genre (id)
);

create table user_author_recommendations
(
    user_account_id bigserial,
    author_id       bigserial,

    foreign key (user_account_id) references user_account (id),
    foreign key (author_id) references author (id)
);
