/* v1 */

drop database if exists sample;
create database sample;
use sample;

create table metadata (
    property varchar(255) not null,
    value varchar(255) not null,
    constraint pk_metadata primary key (property)
);

insert into metadata values ('version', 1), ('update_date', NOW());

create table project (
    id char(36) not null,
    name varchar(45) not null,
    primary key (id)
);

create table task (
    id char(36) not null,
    name varchar(45) not null,
    project_id char(36),
    primary key (id)
);

alter table task 
    add constraint FK_b7i81l1tk1ph95xnhtoftyv53 
    foreign key (project_id) 
    references project (id);
