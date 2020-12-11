select * from person_info;
select * from user_constraints Where table_name = 'Person_Info';
desc person_info;
select * from employees;

delete from person_info;

select person_id from person_info where person_id = (select max(person_id) from person_info);

update person_info set person_id = person_id - 1 where person_id =
(select max(person_id) from person_info) and count(*) != 0;

select count(*) from person_info;

/*FOREIGN KEY (ÄÃ·³)

REFERENCES ÂüÁ¶ÇÒ_Å×ÀÌºí (ÂüÁ¶ÇÒ_ÄÃ·³)*/
-- FOREIGN KEY (Seat_Number) REFERENCES Realtime_Seat_Info (Seat_Number)

--CREATE Table Person_Info  (
--  Person_Id number(8) constraint Info_Persons_Id_PK primary key,
--  Phone_Number varchar2(20) constraint Info_Phone_Number_UK_NN_CK unique not null check(regexp_like(Phone_Number, '01[0-9]-[0-9]{4}-[0-9]{4}')),
--  Person_Name varchar2(20) constraint Info_Person_Name_NN_CK not null check(regexp_like(Person_Name, '[°¡-ÆR]{2,4}')),
--  PW varchar2(30) constraint Info_PW_NN_CK not null check(regexp_like(PW, '[a-zA-Z0-9[:punct:]]{8,12}')),
--  Total_Payment number(8) constraint Info_Total_Payment_NN_CK not null check(Total_Payment >= 0)
--);
--
--create Table Realtime_Person_Statement (
--  Person_Id number(8) constraint state_Persons_Id_PK primary key,
--  Check_Time timestamp constraint state_Check_Time_NN not null,
--  Seat_Number number(2) constraint state_Seat_Number_NN not null, 
--  Locker_Number number(2) constraint state_Locker_Number_NN not null,
--  Person_Statement varchar2(20) constraint state_Person_Statement_NN not null
-- );
delete from person_info;

Select * from person_info order by check_time;

SELECT phone_number FROM person_info;
SELECT phone_number FROM person_info;

select * from Realtime_Seat_Info;
select * from Realtime_Locker_Info;
select * from Payment_Record;
select * from seat_price;

select * from locker_price;
select * from daily_sales;

drop table person_info;
drop table Realtime_Seat_Info;
drop table Realtime_Locker_Info;
drop table Payment_Record;
drop table seat_price;
drop table locker_price;
drop table daily_sales;
drop sequence signupseq;

insert into person_info(person_id, check_time, person_name, Person_Birth, phone_number, pw, total_payment) values(72,'231232','ÇöÅÂÅÂ', '19831010', '010-1111-0000','12345678', 0);
SELECT Person_Id, Person_Name, Phone_Number, PW FROM Person_Info where Phone_Number = '010-1111-0000';
CREATE SEQUENCE SignUpSeq MINVALUE 1 INCREMENT BY 1;
drop sequence signupseq;

insert into person_info (person_id) values(1);
/*

*/
commit;
SELECT max(person_id) KEEP(DENSE_RANK FIRST ORDER BY person_id DESC) FROM person_info;

CREATE Table Person_Info  (
  Person_Id number(8) constraint Info_Persons_Id_PK primary key,
  Check_Time varchar2(30) constraint Info_Check_Time_NN not null,
  Person_Name varchar2(20) constraint Info_Person_Name_NN not null 
                            constraint Info_Person_Name_CK check(regexp_like(Person_Name, '[°¡-ÆR]{2,4}')),
  Person_Birth varchar2 (20) constraint Info_Person_Birth_NN not null
                            constraint Info_Person_Birth_CK check(regexp_like(Person_Birth, '[0-9]{8}')),
  Phone_Number varchar2(20) constraint Info_Phone_Number_UK unique
                        constraint Info_Phone_Number_NN not null 
                   constraint Info_Phone_Number_CK check(regexp_like(Phone_Number, '^01[0-9]-[0-9]{4}-[0-9]{4}$')),
  PW varchar2(30) constraint Info_PW_NN not null
                constraint Info_PW_CK check(regexp_like(PW, '[a-zA-Z0-9[:punct:]]{8,12}$')),
  Total_Payment number(8) constraint Info_Total_Payment_NN not null ,
  Login_State varchar2(20) default 'Off' constraint Info_Login_State_NN not null
                            constraint Info_Loging_State_CK check(login_state in ('On','Off')),
  Seat_Number number(2), 
  Locker_Number number(2),
  Room_Number number(4),
  Expiration_seat timestamp,
  Expiration_locker timestamp,
  Expiration_room timestamp,
  Seat_Type varchar2(20)
);

insert into person_info(person_id, check_time, person_name, person_birth,
phone_number, pw, total_payment, login_state,expiration_seat,expiration_locker,expiration_room,seat_type) 
values(40,'231232','±èÇö±è','20000912','010-1143-1111','12345678', 0, 'On','20/01/01 00:00:00.000000000','20/01/01 00:00:00.000000000','20/01/01 00:00:00.000000000','x');

insert into person_info(person_id, check_time, person_name, person_birth,
phone_number, pw, total_payment, login_state,expiration_seat,expiration_locker,expiration_room,seat_type) 
values(50,'231232','Çö±è±è','20000912','010-1143-1011','12345678', 0, 'Off','20/01/01 00:00:00.000000000','20/01/01 00:00:00.000000000','20/01/01 00:00:00.000000000','x');

create Table seat(
  Seat_Number number(3) ,
  Seat_Statement varchar2(20),
  time_enter  timestamp,
  time_checkout timestamp
);
 
create Table locker(
  Locker_Number number(3) ,
  Locker_Statement varchar2(20)  ,
  l_time_enter  timestamp,
  l_time_checkout timestamp
);

Create Table Payment_Record(
  Paid_Time timestamp ,
  Exit_Time timestamp ,
  Seat_Type varchar2(30) ,
  Locker_Type varchar2(30),
  Pay_Method varchar2(30),
  Payment number(7)
);

create Table room(
  Locker_Number number(3) ,
  Locker_Statement varchar2(20)  ,
  r_time_enter  timestamp,
  r_time_checkout timestamp
);

insert into seat(Seat_Number,Seat_Statement) values (1,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (2,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (3,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (4,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (5,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (6,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (7,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (8,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (9,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (10,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (11,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (12,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (13,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (14,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (15,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (16,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (17,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (18,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (19,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (20,'»ç¿ë °¡´É') ;

insert into seat(Seat_Number,Seat_Statement) values (101,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (102,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (103,'»ç¿ë °¡´É') ;
insert into seat(Seat_Number,Seat_Statement) values (104,'»ç¿ë °¡´É') ;

insert into locker(Locker_Number,Locker_Statement) values (1,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (2,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (3,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (4,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (5,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (6,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (7,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (8,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (9,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (10,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (11,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (12,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (13,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (14,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (15,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (16,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (17,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (18,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (19,'»ç¿ë °¡´É') ;
insert into locker(Locker_Number,Locker_Statement) values (20,'»ç¿ë °¡´É') ;