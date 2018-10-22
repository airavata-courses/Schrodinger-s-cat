--insert into user(id,first_name,last_name,birth_date,self_description,email_id,user_name,password,role) values(1001,'Daddam','Baccha',sysdate(),'Mein bada chef hoon','DaddamBaccha@example.com','Daddam_baccha','secret1','USER');
--insert into user(id,first_name,last_name,birth_date,self_description,email_id,user_name,password,role) values(1002,'Fish','Baccha',sysdate(),'Mein bada nahi chef hoon','FishBaccha@example.com','Fish_baccha','secret1','USER');
--insert into user(id,first_name,last_name,birth_date,self_description,email_id,user_name,password,role) values(1003,'Dino','Baccha',sysdate(),'Mein bada chef hoon re pakka','DinoBaccha@example.com','Dino_baccha','secret1','USER');
--insert into user(id,first_name,last_name,birth_date,self_description,email_id,user_name,password,role) values(1004,'Nawaz','Baccha',sysdate(),'Mein bada chef hoon','nawazkh@iu.edu','testAdmin','secret1','ADMIN');
----create table user (id integer not null, birth_date timestamp, email_id varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), recipe_id integer, self_description varchar(255), user_name varchar(255), primary key (id))
----posts (id integer not null, user_id integer, primary key (id))
--
--insert into posts(id,user_id) values(11001,1001);
--insert into posts(id,user_id) values(11002,1001);
--insert into posts(id,user_id) values(11003,1001);
--insert into posts(id,user_id) values(11004,1001);
--
--insert into posts(id,user_id) values(11005,1002);
--insert into posts(id,user_id) values(11006,1002);
--insert into posts(id,user_id) values(11007,1002);
--insert into posts(id,user_id) values(11008,1002);
--
--insert into posts(id,user_id) values(11009,1003);
--insert into posts(id,user_id) values(11010,1003);
--insert into posts(id,user_id) values(11011,1003);
--insert into posts(id,user_id) values(11012,1003);
--
--insert into posts(id,user_id) values(11013,1004);
--insert into posts(id,user_id) values(11014,1004);


--DELETE FROM role WHERE role_id = 1;
--INSERT INTO role(role_id, role) VALUES (1, 'ADMIN');
--insert INTO role VALUES (2,'ADMIN');
--insert into user (active, email, first_name, last_name, password, self_description, username, user_id) values (1, 'nawazkh@iu.edu', 'nawaz','hussain', 'bada cook hoon bhaiya', ?, ?, ?);

insert into role (role_id, role)
select 
    '1', 'ADMIN'
where not exists (
    select * from role where role_id = 1 and role = 'ADMIN'
);