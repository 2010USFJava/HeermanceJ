create schema if not exists pokemonland authorization annacarl;
set search_path to pokemonland; 


create table trainer( --trainer objects
tid serial primary key,
tname varchar(20)
);

create table pokemon( --pokemon objects
pid serial primary key,
pname varchar(20)
);

create table roster( --which trainer has which pokemon
tid int,
pid int,
foreign key (tid) references trainer(tid) on delete cascade,
foreign key (pid) references pokemon(pid) on delete cascade
);

create table ability( --ability objects
aid serial primary key,
aname varchar(20),
adescription text
);

create table ability_list( --which moves each pokemon has
pid int,
aid int,
foreign key (pid) references pokemon(pid) on delete cascade,
foreign key (aid) references ability(aid) on delete cascade
);

select * from trainer;
select * from pokemon;
select * from ability;
select * from roster;
select * from ability_list;



