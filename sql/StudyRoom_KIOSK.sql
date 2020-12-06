-- StudyRoom_KIOSK

CREATE Table Person_Info  (
  Person_Id number(8) constraint Person_Id_PK primary key,
  Phone_Number varchar2(20) constraint Phone_Number_UK_NN_CK unique not null check(regexp_like(Phone_Number, '01[0-9]-[0-9]{4}-[0-9]{4}')),
  Person_Name varchar2(20) constraint Person_Name_NN_CK not null check(regexp_like(Person_Name, '[°¡-ÆR]{2,4}')),
  PW varchar2(30) constraint PW_NN_CK not null check(regexp_like(PW, '[a-zA-Z0-9[:punct:]]{8,12}')),
  Total_Payment number(8) constraint Total_Payment_NN_CK not null check(Total_Payment >= 0)
);

select * from person_info;
select * from user_constraints Where table_name = 'Person_Info';
desc person_info;

Table Realtime_Person_Statement {
  Person_Id int [pk]
  Check_Time timestamp
  Seat_Number int
  Locker_Number int
  Person_Statement varchar
 }

Table Realtime_Seat_Info{
  Seat_Number int [pk]
  Seat_Statement varchar
}

Table Realtime_Locker_Info{
  Locker_Number int [pk]
  Locker_Statement varchar
}

Table Payment_Record{
  Paid_Time timestamp [pk]
  Exit_Time timestamp
  Person_Id int
  Seat_Type varchar
  Locker_Type varchar
  Pay_Method varchar
  Payment int
  BonusPoints int
}

Table seat_price{
  Seat_type varchar [pk]
  seat_price int
}

Table Locker_Price{
  Locker_Type varchar [pk]
  Locker_Price int
}

Table Daily_TodayPass_Sales{
  Check_Date timestamp [pk]
  Price int
  Counts int
  Total_Price int
}

Table Daily_RegularPass_Sales{
  Check_Date timestamp [pk]
  Price int
  Counts int
  Total_Price int
}

Table Daily_LockerPass_Sales{
  Check_Date timestamp [pk]
  Price int
  Counts int
  Total_Price int
}

Table Daily_Sum_Sales{
  Check_Date timestamp [pk]
  Total_Price int
}



Ref: "Realtime_Person_Statement"."Seat_Number" < "Realtime_Seat_Info"."Seat_Number"

Ref: "Realtime_Person_Statement"."Locker_Number" < "Realtime_Locker_Info"."Locker_Number"

Ref: "Payment_Record"."Person_Id" < "Person_Info"."Person_Id"

Ref: "Payment_Record"."Seat_Type" < "seat_price"."Seat_type"

Ref: "Payment_Record"."Locker_Type" < "Locker_Price"."Locker_Type"