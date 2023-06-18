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

insert into genre
values (1, 'Проза'),
       (2, 'Приключение'),
       (3, 'Поэзия'),
       (4, 'Фэнтези'),
       (5, 'Роман'),
       (6, 'Детектив'),
       (7, 'Нехудожественная литература'),
       (8, 'Ужасы');

insert into book(id, title, description, publication_date, genre_id, pages, isbn, language, author, publisher, storage_num, price)
values (1, 'Великий Гэтсби',
        '"Бурные" двадцатые годы прошлого столетия... Время шикарных вечеринок, "сухого закона" и "легких" денег... Эти "новые американцы" уверены, что расцвет будет вечным, что достигнув вершин власти и богатства, они обретут и личное счастье... Таким был и Джей Гэтсби, ставший жертвой бессмысленной погони за пленительной мечтой об истинной и вечной любви, которой не суждено было сбыться...',
        '2018-01-01', 1, 256, '978-5-17-088830-6', 'RUS', 'Фицжеральд Скотт', 'АСТ', 12, 35.78),
       (2, 'Кладбище домашних животных',
        'Казалось бы, семейство Крид – это настоящее воплощение "американской мечты": отец – преуспевающий врач, красавица мать, прелестные дети. Для полной идиллии им не хватает лишь большого старинного дома, куда они вскоре и переезжают. Но идиллия вдруг стала превращаться в кошмар. Потому что в окружающих их новое жилище вековых лесах скрывается НЕЧТО, более ужасное, чем сама смерть и… более могущественное.',
        '2016-01-01', 8, 480, '978-5-17-099626-1', 'RUS', 'Стивен Кинг', 'АСТ', 15, 60),
       (3, 'Долгая прогулка',
        'Это была страшная игра – игра на выживание. Это была Долгая Прогулка. Прогулка со Смертью, ибо смерть ожидала каждого упавшего. Дорога к счастью – потому что победивший в игре получал все. На долгую прогулку вышли многие – но закончит ее только один. Остальные лягут мертвыми на дороге – потому что дорога к счастью для одного станет последней дорогой для многих.',
        '2019-01-01', 8, 352, '978-5-17-094070-7', 'RUS', 'Стивен Кинг', 'ACT', 25, 74),
       (4, 'Тайные виды на гору Фудзи',
        'Готовы ли вы ощутить реальность так, как переживали ее аскеты и маги древней Индии две с половиной тысячи лет назад? И если да, хватит ли у вас на это денег?',
        '2018-01-01', 5, 416, '978-5-17-082350-6', 'RUS', 'Виктор Пелевин', 'ACT', 5, 34),
       (5, 'Объектно-ориентированное программирование в С++',
        'Благодаря этой книге тысячи пользователей овладели технологией объектно-ориентированного программирования в С++.',
        '2016-01-01', 7, 928, '978-5-17-099623-1', 'RUS', 'Роберт Лафоре', 'ACT', 23, 98),
       (6, 'Python для детей и родителей',
        'Программирование – одна из самых востребованных профессий в наше время, и она останется таковой в ближайшем будущем. Научите своих детей программировать уже сейчас с помощью этой книги!',
        '2019-01-01', 7, 352, '978-5-17-194070-7', 'RUS', 'Брайсон Пэйн', 'ACT', 32, 77);

insert into user_account(id, email, password, is_enabled, role, orders_num, name, surname)
values (1, 'user', '$2a$10$Q2pV/IZs9XhYKh8ZeMKKTOjisu6kxMTA6zFL.hTYvJujWOCTYF9Sq', true, 'ROLE_USER', 0, 'User', 'User'),
       (2, 'admin', '$2a$16$0SjsnuqtGQHIaVISve16uuSmPzialzMFl3S3Z/mQepGKWOy0yQCpW', true, 'ROLE_ADMIN', 0, 'Admin', 'Admin'),
       (3, 'super-admin', '$2a$16$qPFbOORoMHBmIn.2MiSPkOV1gBawphvmGKLtA/BCFXtuMbObFGZhS', true, 'ROLE_SUPER_ADMIN', 0, 'Super-admin', 'Super-admin');

insert into statistics(id, users_num, orders_num, books_num)
values (1, 3, 0, 6);

