insert into price(price, status) values (20000, true);
insert into price(price, status) values (10000, false);

insert into penalty(price, heavy_condition) values (100000, true);
insert into penalty(price, heavy_condition) values (60000, false);

insert into MEMBER(name, membership) values('Daniel', true);
insert into MEMBER(name, membership) values('Andre', false);
insert into MEMBER(name, membership) values('Joe', true);
insert into MEMBER(name, membership) values('Lala', false);

insert into DVD(is_rented, name, new_film) values(false, 'DIE HARD', false);
insert into DVD(is_rented, name, new_film) values(true, 'BLACK ADAM', true);
insert into DVD(is_rented, name, new_film) values(true, 'WEDNESDAY', true);
insert into DVD(is_rented, name, new_film) values(true, 'TOMORROW WAR', true);
insert into DVD(is_rented, name, new_film) values(true, 'DUNE', true);
insert into DVD(is_rented, name, new_film) values(true, 'INFINITE', true);
insert into DVD(is_rented, name, new_film) values(false, 'BATTLE: LOS ANGELES', false);
insert into DVD(is_rented, name, new_film) values(false, 'GODFATHER 1', false);
insert into DVD(is_rented, name, new_film) values(false, 'GODFATHER 2', false);
insert into DVD(is_rented, name, new_film) values(true, 'GODFATHER 3', false);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(1, 7, '2022-10-10', '2022-10-15', null, 100000, 45000);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(2, 3, '2022-10-12', '2022-10-22', null, 100000, 200000);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(3, 1, '2022-10-10', '2022-10-12', null, 100000, 18000);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(4, 2, '2022-11-29', null, null, 100000, null);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(1, 4, '2022-12-10', null, null, 100000, null);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(3, 5, '2022-09-29', null, null, 100000, null);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(2, 3, '2022-10-25', null, null, 100000, null);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(2, 10, '2022-12-09', null, null, 100000, null);

insert into RENT(member_id, dvd_id, rent_date, return_date, dvd_condition, deposit, total_paid)
values(4, 6, '2022-11-20', null, null, 100000, null);