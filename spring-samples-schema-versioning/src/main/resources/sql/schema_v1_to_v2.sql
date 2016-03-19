/* v1 to v2 */

update metadata SET value = 2 WHERE property = 'version';
update metadata SET value = NOW() WHERE property = 'update_date';

alter table task 
    add column description varchar(45);
