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
	bid_date DATE,
	payment_total INTEGER,
	item_status VARCHAR(30)
); 