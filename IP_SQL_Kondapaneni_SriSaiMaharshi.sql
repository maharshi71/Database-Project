Create TABLE Employee(Ename varchar(40) primary key,Address varchar(40))

Create table Technicalstaff(Ename varchar(40) Primary key,D_obtained varchar(20),tech_position varchar(10),foreign key(Ename)references employee(Ename)  on delete CASCADE )
ALTER table Technicalstaff  ADD CONSTRAINT CHK_Technical_staff ​CHECK​ (D_obtained IN (​'BS'​, ​'MS'​, ​'Phd'​))

Create table Qualitycontroller(Ename varchar(40) Primary key,Type_checked varchar(20),foreign key(Ename)references employee(Ename)  on delete CASCADE )
ALTER table QualityController ADD CONSTRAINT CHK_Type_checked ​CHECK​ (Type_checked IN (​'PRODUCT1'​, ​'PRODUCT2'​, ​'PRODUCT3'​))

Create table Worker(Ename varchar(40) Primary key,Max_products_produces int,foreign key(Ename)references employee(Ename)  on delete CASCADE )

create table product(Pid int primary key,date_produced date,time_spent int,person_produced varchar(40) not null foreign key references worker(ename), person_tested varchar(40) not null foreign key references qualitycontroller(ename), person_repaired varchar(40) foreign key references technicalStaff(ename))

create table m1_details(pid int ,Size char(10), major_software varchar(20),foreign key(pid) references product(pid) on DELETE CASCADE,aid int,cost int, foreign key(aid) references account(aid) on delete cascade, PRIMARY KEY (aid,pid))
ALTER table m1_details  ADD CONSTRAINT CHK_size ​CHECK​ (Size IN (​'small'​, ​'Medium'​, ​'large'​))

create table m2_details(pid int, Size char(10) CONSTRAINT CHK_s2 ​CHECK​ (Size IN (​'small'​, ​'Medium'​, ​'large'​)), color varchar(20),foreign key(pid) references product(pid) on DELETE CASCADE,aid int,cost int, foreign key(aid) references account(aid) on delete cascade, PRIMARY KEY (aid,pid))

create table m3_details(pid int, Size char(10) CONSTRAINT CHK_s3 ​CHECK​ (Size IN (​'small'​, ​'Medium'​, ​'large'​)), weight int,foreign key(pid) references product(pid) on DELETE CASCADE,aid int,cost int, foreign key(aid) references account(aid) on delete cascade, PRIMARY KEY (aid,pid))

create table account(Aid int Primary key, date_established date)

create table customers(Cname varchar(40) PRIMARY KEY,caddress varchar(40),pid int foreign key references product(pid))

create table Complaint(Cid int primary Key, Date_of_complaint date, Description varchar(50), TreatmentExpected varchar(20)​,CONSTRAINT​ CHK_treatment_value ​CHECK​ (TreatmentExpected IN (​'moneyback'​, ​'anotherproduct'​​)))

create table accident(anumber int primary key,A_DATE date,no_work_lost int)

create table Error(cid int foreign key references complaint(cid) ON DELETE CASCADE, cname varchar(40) foreign key references customers(cname) ON DELETE CASCADE, pid int Foreign Key references product(pid) ON DELETE CASCADE,PRIMARY KEY (cid,cname,pid))

create table TS_Accident(Anumber int FOREIGN Key references accident(anumber) on Delete CASCADE,ename  varchar(40) foreign key references TechnicalStaff(ename)on Delete CASCADE,pid int foreign key references product(pid)on Delete CASCADE, PRIMARY KEY (pid,ename,anumber))

create table W_accident(Anumber int FOREIGN Key (anumber) references accident(anumber) ON DELETE CASCADE ,ename  varchar(40) foreign key references worker(ename) ON DELETE CASCADE,pid int foreign key references product(pid) ON DELETE CASCADE, PRIMARY KEY (pid,ename,anumber))

create table requests(qname varchar(40) FOREIGN KEY references qualitycontroller(ename), tname varchar (40) FOREIGN KEY references technicalStaff(ename),pid int FOREIGN KEY references product(pid),PRIMARY KEY(qname,tname,pid))

create table Repairs(pid int foreign key references product(pid) ON DELETE CASCADE, cid int  foreign key references complaint(cid) ON DELETE CASCADE, ename varchar(40) foreign key references Technicalstaff(ename) ON DELETE CASCADE,Date_of_repair date,PRIMARY KEY(pid,cid,ename),)

Create INDEX yak on accident(a_date);

Create Index index2 on m2_details(color)

ALTER TABLE Repairs ADD degree VARCHAR(20) CONSTRAINT deg CHECK (degree in ('BS','MS','Phd'));
ALTER TABLE Repairs ADD types VARCHAR(20) CONSTRAINT typ CHECK (types in ('Product1','Product2','Product3'));
ALTER TABLE Repairs ADD CONSTRAINT chk_degree CHECK (dbo.CEDUCATION(degree, types) = 1)

/* this is to add constraints in the repairs table since only the technical staff with Graduate education can do repairs for Product 1 */
CREATE FUNCTION dbo.CEDUCATION(@D_g VARCHAR(20), @ty_p VARCHAR(20))
    RETURNS INT
AS
    BEGIN
        IF @ty_p in ('Product1') and @D_g not in ('MS','Phd')
            RETURN 0
        RETURN 1
    END
GO

/*inserting into Tables*/
insert into employee values('luffy','DrakeDrive Norman'),
('Zoro','swordsman Norman'),
('sanji','12th avenue tulsa'),
('nami','brooks st oklahoma'),
('ussop','cleaveland edmond'),
('surya','andhrapradesh india'),
('bhargav','hyderabad india'),
('ramya','banglore united states'),
('navya','visakhapatnam madhyapradesh'),
('madhu','delhi utterpradesh'),
('swetha','tokyo china'),
('ankur','andhrapradesh india'),
('ram','hyderabad india'),
('cheng','banglore united states'),
('sita','visakhapatnam madhyapradesh'),
('sasuke','london portland'),
('naruto','paris englang'),
('kyubii','konaha village')

select * from employee



/*inserting into Technical Staff */
insert into Technicalstaff values('Zoro','MS','Developer'),('sanji','Phd','UIDesigner'),('bhargav','BS','Networking')
insert into Technicalstaff values('surya','bs','hardware'),('ram','ms','Developer')

select * from Technicalstaff


/*inserting into QualityController*/
insert into Qualitycontroller values('nami','product1'),
('ussop','product2'),
('kim','product3'),
('kyubii','product3'),
('ankur','product2')

select * from Qualitycontroller

/* insreting into Worker */
insert into Worker values('luffy',10),
('navya',20),('ramya',10),('madhu',40),('sasuke',20)

select * from worker

/*inserting into Product */
insert into product values(100,'2018-05-12',20,'luffy','nami',NULL)
(101,'2018-09-22',20,'navya','kim','sanji'),
(102,'2018-10-01',400,'madhu','kim','bhargav'),
(103,'2018-01-15',900,'ramya','kyubii',null),
(104,'2018-02-18',800,'luffy','ussop','ram'),
(105,'2018-03-06',100,'sasuke','kyubii','surya'),
(106,'2018-04-30',300,'ramya','ankur',NULL),
(107,'2018-06-30',500,'navya','ankur',NULL),
(108,'2018-07-19',230,'luffy','ussop',NULL),
(109,'2018-08-03',250,'madhu','nami',NULL)

select * from product 

/* inserting into M1_details*/
INSERT into m1_details values(100,'small','mac',1,3000),(9,'medium','java',1009,3000)

Select * from m1_details

/* inserting into M2_details*/
INSERT into M2_details VALUES(102,'Large','red',3,4000),(103,'medium','Yellow',4,3400)
select * from m2_details

/* inserting values into M2_details*/
INSERT into M3_details VALUES(104,'Large',30,3,4000),(106,'medium',100,4,3400)
select * FROM m3_details

/*inserting into account */
insert into account values (1,'2018-05-12'),
(2,'2018-09-22'),
(3,'2018-10-01'),
(4,'2018-01-15'),
(5,'2018-02-18'),
(6,'2018-03-06'),
(7,'2018-04-30'),
(8,'2018-06-30'),
(9,'2018-07-19'),
(10,'2018-08-03')

select * from account

/* inserting Values into Customers */

insert into customers values('Luffy','drakedrive oklahoma',100),
('surya','andhrapradesh india',101),
('krishna','hyderabad india',102),
('ramya','banglore united states',107),
('navya','visakhapatnam madhyapradesh',109),
('joshmitha','delhi utterpradesh',103),
('swetha','tokyo china',100),
('raj','andhrapradesh india',106),
('anirudh','hyderabad india',108),
('sandeep','banglore united states',107)

select * from customers

/*inserting into complaints*/
insert into complaint values (1,'2018-10-01','doesnt turn on ','anotherproduct'),
(2,'2018-05-15','keyboard issues ','moneyback'),
(3,'2018-06-28','colour faded ','anotherproduct'),
(4,'2019-01-01','incorrect size ','anotherproduct'),
(5,'2018-11-11','hardware problem ','moneyback'),
(6,'2018-12-12','excess weight','moneyback')

select * from Complaint 

/*inserting into accident */
insert into accident values(100,'2018-10-11',10)
insert into accident values(300,'2018-01-15',25)

select * from accident

/*inserting into error*/
insert into error values(1,'luffy',100)

select * from Error

/* inserting into TS_Accident*/
insert into TS_Accident values(200,'Zoro',100)

select * from TS_Accident

/*inserting into W_Accident*/
INSERT into W_accident values(300,'luffy',100)

select * from W_accident

/* Inserting Values into requests */
insert into requests VALUES('cheng','naruto',9),('chopper','ram',4),('kyubii','sita',3),('kim','surya',8)

Select * from requests

    
/*inserting into Repairs*/
insert into repairs values('5','1','rafal','2018-03-12','bs','product2'),('10','3','naruto','2018-03-12','phd','product2')

select * from Repairs


/* 7query  */
select date_produced,time_spent from Product where pid= 100

/*8 Query */
select pid from product where person_produced ='name'

/* 9 Query */
select count(e.cid) from error e , product p where e.pid= p.pid and person_tested='"+pt+"'

/* 10 Query */
select sum(c.cost) from m3_details c,requests r where c.pid= r.pid and r.qname ='nami'

/* 11 Query */
select c.cname from customers c, m2_details p where c.pid = p.pid intersect
select c.cname from customers c, m2_details e where c.pid= e.pid and e.color in ('red')

/* Query 12 */
Select sum(a.no_work_lost) from accident a, ts_accident t, repairs r where r.pid= t.pid and a.anumber = t.anumber

/* 13 Query */
select c.cname from customers c,worker w where c.cname= w.ename

/* Query 14 */
select DISTINCT(c.cname) from customers c,product p where c.cname=p.person_produced or c.cname=p.person_repaired or c.cname = p.person_tested
/* Query 15*/

select ((Select avg(m.cost) from m1_details m, account a where m.aid = a.aid and year(a.date_established)='2018')+
(Select avg(m.cost) from m2_details m, account a where m.aid = a.aid and year(a.date_established)='2018')+
(Select avg(m.cost) from m3_details m, account a where m.aid = a.aid and year(a.date_established)='2018'))/3 as Average

/* Query 17 */
DELETE FROM accident WHERE a_date between '2018-01-01' AND '2018-12-01'

/* Procedure For Query 16 */
CREATE PROCEDURE query16
    @ename VARCHAR(40)
AS
    BEGIN
        SET NOCOUNT ON
        DECLARE @name VARCHAR(40);
        DECLARE @tp VARCHAR(40);
        DECLARE @deg VARCHAR(40);
        DECLARE @pos VARCHAR(40);
        SET @pos = (select tech_position from technicalStaff where ename = '@ename');
        SET @deg=(select D_obtained from technicalStaff where ename = '@ename');
        SET @tp = (select type_checked from qualitycontroller where ename = '@name');
        delete from technicalstaff where ename='@ename';
        delete from qualitycontroller where ename= '@name';
        insert into qualitycontroller values ('@name','@tp');
        insert into technicalstaff values('@ename','@deg','@pos');
    END
GO

create or replace PROCEDURE query16( T_NAME IN VARCHAR,
Q_NAME IN VARCHAR)
AS
BEGIN
UPDATE T_STAFF SET T_NAME = Q_NAME where t_name=T_NAME; UPDATE COMPREASON_RELATION SET T_NAME = Q_NAME where t_name = T_NAME;
UPDATE PRODUCT SET T_NAME = Q_NAME where t_name = T_NAME; UPDATE T_OCCURS SET T_NAME = Q_NAME where t_name = T_NAME;
END SWAP_VALUES;
