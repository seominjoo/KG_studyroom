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

CREATE SEQUENCE SignUpSeq MINVALUE 1 INCREMENT BY 1;
drop sequence signupseq;

insert into person_info (person_id) values(1);
/*

*/

SELECT max(person_id) KEEP(DENSE_RANK FIRST ORDER BY person_id DESC) FROM person_info;

-- StudyRoom_KIOSK
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
  Seat_Number number(2), 
  Locker_Number number(2),
  Person_Statement varchar2(20)
);

SELECT Person_Name, Phone_Number, PW FROM Person_Info;
create Table Realtime_Seat_Info(
  Seat_Number number(2) constraint Seat_Number_PK primary key,
  Seat_Statement varchar2(20) constraint Seat_Statement_NN not null
);

Create Table Realtime_Locker_Info(
  Locker_Number number(2) constraint Locker_Number_PK primary key,
  Locker_Statement varchar2(20) constraint Locker_Statement_NN not null
);

Create Table Payment_Record(
  Paid_Time timestamp constraint Paid_Time_PK primary key,
  Exit_Time timestamp constraint Exit_Time_NN not null,
  Person_Id number(8) constraint Person_Id_NN not null,
  Seat_Type varchar2(20) constraint Seat_Type_NN not null,
  Locker_Type varchar2(20) constraint Locker_Type_NN not null,
  Pay_Method varchar2(20),
  Payment number(6),
  BonusPoints number(5)
);

create Table Seat_Price(
  Seat_Type varchar2(20) constraint Seat_Type_PK primary key,
  Seat_Price number(6) constraint Seat_Price_NN not null
);

create Table Locker_Price(
  Locker_Type varchar2(20) constraint Locker_Type_PK primary key,
  Locker_Price number(6) constraint Locker_Price_NN not null
);

Create table Daily_Sales(
    Check_Date date constraint Check_Date_PK primary key,
    TodayPass_Count number(4),
    RegularPass_Count number(4),
    LockerPass_Count number(4),
    Total_Price number(6)
);

ALTER TABLE Person_Info ADD CONSTRAINT Info_Seat_Number_FK
FOREIGN KEY (Seat_Number) REFERENCES realtime_seat_info (seat_number);

ALTER TABLE Person_Info ADD CONSTRAINT Info_Locker_Number_FK
FOREIGN KEY (Locker_Number) REFERENCES realtime_Locker_info (Locker_number);

ALTER TABLE Payment_Record ADD CONSTRAINT record_person_id_FK
FOREIGN KEY (Person_Id) REFERENCES Person_Info (Person_Id);

ALTER TABLE Payment_Record ADD CONSTRAINT record_Seat_type_FK
FOREIGN KEY (Seat_type) REFERENCES Seat_price (Seat_type);

ALTER TABLE Payment_Record ADD CONSTRAINT record_Locker_Type_FK
FOREIGN KEY (Locker_Type) REFERENCES Locker_Price (Locker_Type);

/*
create Table Daily_TodayPass_Sales{
  Check_Date timestamp [pk]
  Price int
  Counts int
  Total_Price int
}

create Table Daily_RegularPass_Sales{
  Check_Date timestamp [pk]
  Price int
  Counts int
  Total_Price int
}

create Table Daily_LockerPass_Sales{
  Check_Date timestamp [pk]
  Price int
  Counts int
  Total_Price int
}

create Table Daily_Sum_Sales{
  Check_Date timestamp [pk]
  Total_Price int
}
*/