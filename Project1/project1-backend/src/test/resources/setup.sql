drop table if exists ers_reimbursement;
drop table if exists ers_users;
drop table if exists ers_reimbursement_status;
drop table if exists ers_user_roles;
drop table if exists ers_reimbursement_type;

--create schema project1;
create table if not exists ERS_REIMBURSEMENT_STATUS(
	REIMB_STATUS_ID SERIAL not null primary key ,
	REIMB_STATUS VARCHAR(10) not null 
);

create table if not exists ERS_REIMBURSEMENT_TYPE(
	REIMB_TYPE_ID SERIAL not null primary key ,
	REIMB_TYPE VARCHAR(10)
);

create table if not exists ERS_USER_ROLES(
	ERS_USER_ROLE_ID SERIAL not null primary key ,
	USER_ROLE VARCHAR(10) not null 
);

create table if not exists ERS_USERS(
	ERS_USERS_ID SERIAL not null primary key,
	ERS_USERNAME VARCHAR(50) not null ,
	ERS_PASSWORD VARCHAR(50) not null ,
	USER_FIRST_NAME VARCHAR(100) not null ,
	USER_LAST_NAME VARCHAR(100) not null ,
	USER_EMAIL VARCHAR(150) not null ,
	USER_ROLE_ID int references ERS_USER_ROLES(ERS_USER_ROLE_ID) ,
	unique(ERS_USERNAME,USER_EMAIL)
);

create table if not exists ERS_REIMBURSEMENT(
	REIMB_ID SERIAL not null primary key ,
	REIMB_AMOUNT numeric not null ,
	REIMB_SUBMITTED TIMESTAMP not null ,
	REIMB_RESOLVED TIMESTAMP,
	REIMB_DESCRIPTION VARCHAR(250),
	REIMB_RECEIPT bytea,
	REIMB_AUTHOR int not null references ERS_USERS(ERS_USERS_ID),
	REIMB_RESOLVER int references ERS_USERS(ERS_USERS_ID),
	REIMB_STATUS_ID int not null references ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID),
	REIMB_TYPE_ID int not null references ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID)
);

--insert into ERS_REIMBURSEMENT_TYPE (reimb_type) values ('lodging');
--insert into ERS_REIMBURSEMENT_TYPE (reimb_type) values ('travel');
--insert into ERS_REIMBURSEMENT_TYPE (reimb_type) values ('food');
--insert into ERS_REIMBURSEMENT_TYPE (reimb_type) values ('other');
--
--insert into ERS_USER_ROLES (user_role) values ('manager');
--insert into ERS_USER_ROLES (user_role) values ('employee');
--
--insert into ERS_REIMBURSEMENT_STATUS (reimb_status) values ('pending');
--insert into ERS_REIMBURSEMENT_STATUS (reimb_status) values ('resolved');
--
--insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
--values ('username', 'password', 'first', 'last', 'employee@email.com', 2);
--insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
--values ('manager', 'password', 'first', 'last', 'manager@email.com', 1);
--
--insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_author, reimb_status_id, reimb_type_id)
--values (100, current_timestamp, 2, 1, 1);
--insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_author, reimb_status_id, reimb_type_id)
--values (10, current_timestamp, 2, 1, 2);
