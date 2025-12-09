drop table if exists feature_management;

create table feature_management(
    feature_name varchar(100) not null primary key,
    enabled boolean not null
);

insert into feature_management(feature_name, enabled)
values ('experimental', true),
       ('development', false);
