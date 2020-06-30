drop table if exists Quarkus cascade;
drop sequence if exists SEQ_QUARKUS;
create sequence SEQ_QUARKUS start 1 increment 1;
create table Quarkus (
                         id int8 not null,
                         address varchar(255),
                         name varchar(255),
                         primary key (id)
);