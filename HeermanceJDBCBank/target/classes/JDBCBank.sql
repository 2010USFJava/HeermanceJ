create schema if not exists jdbcbank authorization JHeermance;
set search_path to jdbcbank;

--creating tables--
create table "Customer"(
"Customer_ID" serial primary key,
"F_Name" varchar not null,
"M_Name" varchar, 
"L_Name" varchar not null,
"Address_Street" varchar not null,
"Address_City" varchar not null,
"Address_State" varchar not null,
"Address_Zipcode" varchar not null,
"Username" varchar not null unique,
"Password" varchar not null,
"Accounts" int
);

create table "Accounts"(
"Account_ID" serial primary key,
"Account_Type" varchar not null,
"Account_Status" bool not null,
"Balance" numeric(100,2)
);

create table "Account_Users"( --allows for multiple joint users
"Account_ID" int not null,
"Customer_ID" int not null,
foreign key ("Account_ID") references "Accounts"("Account_ID") on delete cascade,
foreign key ("Customer_ID") references "Customer"("Customer_ID") on delete cascade
);

--
	

delete from "Customer" where "Username" = 'TestUsertest';
commit;

drop table "Account_Users";
drop table "Accounts";

select * from "Account_Users" right join "Accounts" on "Account_Users"."Account_ID"="Accounts"."Account_ID";
select * from "Account_Users" right join "Customer" on "Account_Users"."Customer_ID" = "Customer"."Customer_ID";

