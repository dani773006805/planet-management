create table managers (id bigint generated by default as identity, age integer, name varchar(255), planet_sum integer, primary key (id));
create table planets (id bigint generated by default as identity, name varchar(255), population bigint, manager_id bigint, primary key (id));
alter table planets add constraint FKwe foreign key (manager_id) references managers;
