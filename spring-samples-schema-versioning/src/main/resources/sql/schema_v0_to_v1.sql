/* v0 to v1 */

update metadata SET value = 1 WHERE property = 'version';
update metadata SET value = NOW() WHERE property = 'update_date';

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
