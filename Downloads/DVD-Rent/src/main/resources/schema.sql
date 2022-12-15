create table MEMBER(
    id bigint primary key auto_increment,
    name varchar not null,
    membership boolean not null
);

create table DVD(
    id bigint primary key auto_increment,
    is_rented boolean not null,
    name varchar not null,
    new_film boolean not null
);

create table RENT(
    id bigint primary key auto_increment,
    member_id bigint,
    dvd_id bigint,
    deposit bigint,
    rent_date date not null,
    return_date date,
    dvd_condition boolean,
    total_paid bigint,
    foreign key (dvd_id) references DVD(id),
    foreign key (member_id) references MEMBER(id)
);

create table price(
    id bigint primary key auto_increment,
    price bigint,
    status boolean
);

create table penalty(
    id bigint primary key auto_increment,
    price bigint,
    heavy_condition boolean
);

