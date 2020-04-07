create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

create table city
(
    id               bigint not null,
    city_description varchar(255),
    city_name        varchar(255),
    primary key (id)
) engine = InnoDB;


insert into hibernate_sequence
values (1);