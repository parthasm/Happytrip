CREATE  TABLE      airlines   (
    AirlineId    NUMBER (11) NOT NULL   ,
    AirlineName   VARCHAR2(255)   ,
    AirlineCode   VARCHAR2(255)   ,
  PRIMARY KEY (  AirlineId  ) );

CREATE  TABLE      cities   (
    CityId    NUMBER (11) NOT NULL   ,
    CityName   VARCHAR2(255)   ,
  PRIMARY KEY (  CityId  ) );

CREATE  TABLE   routes_airline   (
    RouteId    NUMBER (11) NOT NULL  ,
    FromCityId    NUMBER (11) ,
    ToCityId    NUMBER (11) ,
    DistanceInKms   NUMBER(7,2)     ,
    durationInHrs   VARCHAR2(255)     ,
  PRIMARY KEY (  RouteId  ) ,
    FOREIGN KEY (  ToCityId   )
    REFERENCES   cities   (  CityId   ),
    FOREIGN KEY (  FromCityId   )
    REFERENCES   cities   (  CityId   ));

CREATE  TABLE      flights   (
    FlightId    NUMBER (11) NOT NULL   ,
    FlightName   VARCHAR2(255)   ,
    NoOfSeats    NUMBER (11)   ,
    airlineId    NUMBER (11) NOT NULL ,
  PRIMARY KEY (  FlightId  ) ,
   FOREIGN KEY (  airlineId   )
    REFERENCES    airlines   (  AirlineId   ));

CREATE  TABLE      flight_routes   (
    FlightRouteId    NUMBER (11) NOT NULL   ,
    DepartureTime   VARCHAR2(255)   ,
    ArrivalTime   VARCHAR2(255)   ,
    costPerTicket   NUMBER(7,2)  ,
    flightId    NUMBER (11) NOT NULL ,
    routeId    NUMBER (11) NOT NULL ,
  PRIMARY KEY (  FlightRouteId  ) ,
  FOREIGN KEY (  routeId   )
    REFERENCES    routes_airline   (  RouteId   ),
    FOREIGN KEY (  flightId   )
    REFERENCES    flights   (  FlightId   ));

create table genericUsers (
UserId    NUMBER (11) NOT NULL   ,
EmailId   VARCHAR2(255)   ,
Password   VARCHAR2(255)   ,
PRIMARY KEY (  UserId  ));


CREATE  TABLE      regusers   (
    UserId    NUMBER (11) NOT NULL   ,
    FullName   VARCHAR2(255)   ,    
    CreatedDate   DATE   ,
    Gender   CHAR(1)   ,
    DateOfBirth   DATE   ,
    Address   VARCHAR2(255)   ,
    City   VARCHAR2(255)   ,
    State   VARCHAR2(255)   ,
    Country   VARCHAR2(255)   ,
    Pincode    NUMBER (11)   ,
    MobileNo   VARCHAR2(255)   ,
    OtherContactInfo   VARCHAR2(255)   ,
  PRIMARY KEY (  UserId  ),
  FOREIGN KEY (  UserId   )
    REFERENCES    genericUsers   (  UserId   ) );

create table guestusers (
UserId    NUMBER (11) NOT NULL   ,
PRIMARY KEY (  UserId  ),
  FOREIGN KEY (  UserId   )
    REFERENCES    genericUsers   (  UserId   ) );


CREATE  TABLE      payments   (
    PaymentId    NUMBER (11) NOT NULL   ,
    PaymentDate   DATE   ,
    PaidAmount   NUMBER(7,2)   ,
    PaymentReferenceNo   VARCHAR2(255)   ,
  PRIMARY KEY (  PaymentId  ) );

CREATE  TABLE      bookings_airlines   (
    BookingId    NUMBER (11) NOT NULL   ,
    BookingDate   DATE   ,
    NoOfSeats    NUMBER (11)   ,
    TotalCost   NUMBER(9,2)   ,
    userId    NUMBER (11) NOT NULL ,
    FlightRouteId    NUMBER (11) NOT NULL ,
    PaymentId    NUMBER (11)   ,
  PRIMARY KEY (  BookingId  ) , 
    FOREIGN KEY (  FlightRouteId   )
    REFERENCES    flight_routes   (  FlightRouteId   ),
    FOREIGN KEY (  PaymentId   )
    REFERENCES    payments   (  PaymentId   ),
FOREIGN KEY (  UserId   )
    REFERENCES    genericUsers   (  UserId   ) );

CREATE  TABLE      passengres   (
    PassengerId    NUMBER (11) NOT NULL   ,
    FullName   VARCHAR2(255)   ,
    Gender   CHAR(1)   ,
    DateOfBirth   DATE   ,
    bookingId    NUMBER (11) NOT NULL ,
  PRIMARY KEY (  PassengerId  ) ,
   FOREIGN KEY (  bookingId   )
    REFERENCES    bookings_airlines   (  BookingId   ));

insert into airlines values(1,'Kingfisher','KNF');
insert into airlines values(2,'Jet Airways','JET');

insert into cities values(1,'Kolkata');
insert into cities values(2,'Mumbai');
insert into cities values(3,'Bhopal');
insert into cities values(4,'Jaipur');

insert into routes_airline values(1,1,2,1000.0,'2hrs30minutes');
insert into routes_airline values(2,1,3,500.0,'1hrs30minutes');
insert into routes_airline values(3,2,3,550.0,'1hrs25minutes');
insert into routes_airline values(4,2,1,1000.0,'2hrs25minutes');
insert into routes_airline values(5,3,1,500.0,'1hrs35minutes');
insert into routes_airline values(6,3,2,550.0,'1hrs32minutes');
insert into routes_airline values(7,2,4,350.0,'1hrs02minutes');
insert into routes_airline values(8,1,4,1350.0,'3hrs02minutes');

insert into flights values(1,'KF001',192,1);
insert into flights values(2,'JK002',192,2);
insert into flights values(3,'KF003',192,1);
insert into flights values(4,'JK004',192,2);
insert into flights values(5,'JP005',32,2);

insert into flight_routes values(1,'10:20pm','1:00am',7000.0,1,1);
insert into flight_routes values(2,'10:20pm','11:50pm',3200.0,1,2);
insert into flight_routes values(3,'11:59pm','1:00am',7000.0,1,6);
insert into flight_routes values(4,'11:30am','1:00pm',6000.0,2,1);
insert into flight_routes values(5,'1:07am','1:30am',2000.0,5,7);
insert into flight_routes values(6,'1:07pm','4:09pm',9000.0,3,8);