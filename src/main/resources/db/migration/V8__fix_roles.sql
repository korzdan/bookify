alter table user_account
alter column role type varchar(25);

update user_account
set role = 'ROLE_' || role
where id in (3, 4, 5);
