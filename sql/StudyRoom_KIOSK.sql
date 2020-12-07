-- StudyRoom_KIOSK

CREATE Table Person_Info  (
  Person_Id number(8) constraint Info_Persons_Id_PK primary key,
  Check_Time timestamp constraint Info_Check_Time_NN not null,
  Seat_Number number(2) constraint Info_Seat_Number_NN not null, 
  Locker_Number number(2) constraint Info_Locker_Number_NN not null,
  Person_Statement varchar2(20) constraint Info_Person_Statement_NN not null,
  Person_Name varchar2(20) constraint Info_Person_Name_NN_CK not null check(regexp_like(Person_Name, '[°¡-ÆR]{2,4}')),
  Phone_Number varchar2(20) constraint Info_Phone_Number_UK_NN_CK unique not null check(regexp_like(Phone_Number, '01[0-9]-[0-9]{4}-[0-9]{4}')),
  PW varchar2(30) constraint Info_PW_NN_CK not null check(regexp_like(PW, '[a-zA-Z0-9[:punct:]]{8,12}')),
  Total_Payment number(8) constraint Info_Total_Payment_NN_CK not null check(Total_Payment >= 0)
);

select*from person_info;

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

INSERT INTO Seat_Price VALUES ('Daily_2Hr', 3000);
INSERT INTO Seat_Price VALUES ('Daily_4Hr', 4500);
INSERT INTO Seat_Price VALUES ('Daily_6Hr', 6000);
INSERT INTO Seat_Price VALUES ('Daily_8Hr', 7500);
INSERT INTO Seat_Price VALUES ('Daily_12Hr', 10000);
INSERT INTO Seat_Price VALUES ('Weekly_2Wk', 90000);
INSERT INTO Seat_Price VALUES ('Weekly_4Wk', 130000);
INSERT INTO Seat_Price VALUES ('Weekly_8Wk', 250000);

INSERT INTO Seat_Price VALUES ('Room_2Hr', 3000);
INSERT INTO Seat_Price VALUES ('Room_4Hr', 4500);
INSERT INTO Seat_Price VALUES ('Room_6Hr', 6000);
INSERT INTO Seat_Price VALUES ('Room_8Hr', 7500);
INSERT INTO Seat_Price VALUES ('Room_12Hr', 10000);
INSERT INTO Seat_Price VALUES ('Room_2Wk', 90000);
INSERT INTO Seat_Price VALUES ('Room_4Wk', 130000);
INSERT INTO Seat_Price VALUES ('Room_8Wk', 250000);
SELECT * FROM Seat_Price;

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

select * from seat;
select * from locker;

commit;

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
