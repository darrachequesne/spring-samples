/* v0 */

drop database if exists sample;
create database sample;
use sample;

create table metadata (
    property varchar(255) not null,
    value varchar(255) not null,
    constraint pk_metadata primary key (property)
);

insert into metadata values ('version', 0), ('update_date', NOW());
