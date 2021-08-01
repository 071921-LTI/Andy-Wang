create schema project0;
create table if not exists customer(
	customer_name VARCHAR(30),
	customer_password VARCHAR(30) not null,
	customer_id SERIAL primary key

);

create table if not exists employee(
	employee_name VARCHAR(30),
	employee_password VARCHAR(30) not null,
	employee_id SERIAL primary key
);

create table if not exists items(
	shoe_brand VARCHAR(20),
	shoe_size INTEGER,
	shoe_type VARCHAR(20),
	shoe_color VARCHAR(20),
	shoe_price INTEGER,
	shoe_id SERIAL primary key
);

drop table bidlist ;
create table if not exists bidlist(
--	bid_id INTEGER primary key unique
	item_id INTEGER references items(shoe_id),
	buyer_id INTEGER references customer(customer_id),
	offer_price INTEGER,
	bid_date date,
	payment_total INTEGER,
	item_status VARCHAR(30)
); 

SHOW search_path;
SET search_path TO project0;
select * from items;
insert into items values ('Nike',9,'sneakers','red',10);
select shoe_id from items;

insert into bidlist (item_id ,buyer_id ,offer_price , bid_date, payment_total ,item_status) values (2,1,100, current_date, 0,'pending');

select c.customer_name from customer c, bidlist b 
where c.customer_id = b.buyer_id and c.customer_id = 1;

drop view BIDS;
create view BIDS as
select c.customer_id ,c.customer_name , b.offer_price, b.payment_total , s.shoe_brand ,s.shoe_size,s.shoe_type,s.shoe_color,s.shoe_price,s.shoe_id 
from customer c, bidlist b, items s
where c.customer_id = b.buyer_id and c.customer_id = 1 and s.shoe_id = b.item_id;

select * from BIDS;

select b.item_status,c.customer_name , b.offer_price, b.payment_total , s.shoe_brand ,s.shoe_size,s.shoe_type,s.shoe_color,s.shoe_price
from customer c, bidlist b, items s
where c.customer_id = b.buyer_id and c.customer_id = 1 and s.shoe_id = b.item_id;

delete from bidlist and items where b.item_id = i.shoe_id = 1

update bidlist set offer_price = 1000 where buyer_id = 1 and item_id = 2;