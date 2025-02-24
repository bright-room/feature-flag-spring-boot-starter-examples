drop table if exists feature_management;

create table feature_management(
    api varchar(100) not null primary key,
    enabled boolean not null
);

insert into feature_management(api, enabled)
values ('new-api', false),
       ('beta-feature', true);
