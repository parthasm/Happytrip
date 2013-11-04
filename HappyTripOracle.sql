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


alter table genericusers
add address varchar2(255);


alter table genericusers
add city varchar2(255);


alter table genericusers
add country varchar2(255);


alter table genericusers
add fullname varchar2(255);


alter table genericusers
add gender varchar2(255);


alter table genericusers
add mobileno varchar2(255);


alter table genericusers
add othercontactinfo varchar2(255);


alter table genericusers
add state varchar2(255);


alter table genericusers
add pincode number(11);


alter table genericusers
add createddate date;


alter table genericusers
add dateofbirth date;

#{searchBean.getStatus(flightRoute)}