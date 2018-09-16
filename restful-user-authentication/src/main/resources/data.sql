insert into user(id,first_name,last_name,birth_date,self_description,email_id,user_name,password) values(1001,'Daddam','Baccha',sysdate(),'Mein bada chef hoon','DaddamBaccha@example.com','Daddam_baccha','password');
insert into user(id,first_name,last_name,birth_date,self_description,email_id,user_name,password) values(1002,'Fish','Baccha',sysdate(),'Mein bada nahi chef hoon','FishBaccha@example.com','Fish_baccha','password');
insert into user(id,first_name,last_name,birth_date,self_description,email_id,user_name,password) values(1003,'Dino','Baccha',sysdate(),'Mein bada chef hoon re pakka','DinoBaccha@example.com','Dino_baccha','password');
--create table user (id integer not null, birth_date timestamp, email_id varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), recipe_id integer, self_description varchar(255), user_name varchar(255), primary key (id))
--posts (id integer not null, user_id integer, primary key (id))

insert into posts(id,user_id) values(11001,1001);
insert into posts(id,user_id) values(11002,1001);
insert into posts(id,user_id) values(11003,1001);
insert into posts(id,user_id) values(11004,1001);

insert into posts(id,user_id) values(11005,1002);
insert into posts(id,user_id) values(11006,1002);
insert into posts(id,user_id) values(11007,1002);
insert into posts(id,user_id) values(11008,1002);

insert into posts(id,user_id) values(11009,1003);
insert into posts(id,user_id) values(11010,1003);
insert into posts(id,user_id) values(11011,1003);
insert into posts(id,user_id) values(11012,1003);
