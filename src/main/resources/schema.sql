drop table if exists candies;
create table candies (
    id identity primary key,
    name varchar(50) not null,
    description varchar(50) not null,
    price float not null
);

drop table if exists orders;
create table orders (
    id identity primary key,
    customerName varchar(50) not null,
    address varchar(50) not null,
    candyId bigint,
    paymentMethod varchar(50) not null,
    phoneNumber varchar(20) not null
);

alter table orders add foreign key(candyId) references candies(id);