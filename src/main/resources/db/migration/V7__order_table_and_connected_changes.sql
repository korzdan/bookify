alter table book
add column price double precision default 50.0;

create table if not exists user_order(
    id bigserial primary key,
    user_id bigserial,
    status varchar(15),

    foreign key (user_id) references user_account (id)
);

create table if not exists user_orders_books(
    order_id bigserial,
    book_id bigserial,

    foreign key (order_id) references user_order (id),
    foreign key (book_id) references book (id)
);
