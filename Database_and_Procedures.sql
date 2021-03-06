CREATE DATABASE bloodbank;

USE bloodbank;

CREATE TABLE administration(name varchar(20) NOT NULL, pass varchar(20) NOT NULL);

CREATE TABLE employee(name varchar(20) NOT NULL, pass varchar(20) NOT NULL, primary key (name));

CREATE TABLE hospital(name varchar(20) NOT NULL, pass varchar(20) NOT NULL, primary key (name));

CREATE TABLE blood(donationID int not null, donor_f_name varchar(200), donor_l_name varchar(200), donor_address varchar(200), bType varchar(5), quant float, primary key(donationID));

INSERT INTO administration (name, pass) values ('admin', 'admin');

delimiter // 
create procedure addemp(in name varchar(20),in pass varchar(20))
begin
insert into employee(name, pass) values (name, pass);
end //

create procedure addhosp(in name varchar(20),in pass varchar(20))
begin
insert into hospital(name, pass) values (name, pass);
end //

create procedure donate(in donationID int, in donor_f_name varchar(200), in donor_l_name varchar(200),  donor_address varchar(200), bType varchar(5), quant float)
begin
insert into blood(donationID, donor_f_name, donor_l_name, donor_address, bType, quant) values (donationID, donor_f_name, donor_l_name, donor_address, bType, quant);
end //

create procedure maxid(out maxid int)
begin
select max(donationID) into maxid from blood;
end //

create procedure bcount(in type varchar(5), out count float)
begin
select sum(quant) into count from blood where bType = type;
end //

create procedure adminlogin(in n varchar(20), in p varchar(20), out outname varchar(20), out outpass varchar(20))
begin
select name into outname from administration where name = n and pass = p;
select pass into outpass from administration where name = n and pass = p;
end //

create procedure emplogin(in n varchar(20), in p varchar(20), out outname varchar(20), out outpass varchar(20))
begin
select name into outname from employee where name = n and pass = p;
select pass into outpass from employee where name = n and pass = p;
end //

create procedure hosplogin(in n varchar(20), in p varchar(20), out outname varchar(20), out outpass varchar(20))
begin
select name into outname from hospital where name = n and pass = p;
select pass into outpass from hospital where name = n and pass = p;
end //

create procedure donationlist(in id int, out f_name varchar(200), out l_name varchar(200), out out_add varchar(200), out out_type varchar(5), out q float)
begin
select donor_f_name into f_name from blood where donationID = id;
select donor_l_name into l_name from blood where donationID = id;
select donor_address into out_add from blood where donationID = id;
select bType into out_type from blood where donationID = id;
select quant into q from blood where donationID = id;
end //

