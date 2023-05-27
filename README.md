# Taxi-Booking
Taxi Booking System built using JSP Servlets MVC Framework and back-end using MYSQL .

Youtube link : https://youtu.be/icOsExj3ilM

Features : 
- All code is developed using MVC framework so the model , views , and business logic is written seperated so that the new features can be added easily.
- considering each Location is 15kms away from the adjacent location.
- It takes 60 mins to travel from one location to another
- Each taxi charges Rs.100 minimum for the first 5 kilometers and Rs.10 for the subsequent kilometers.
- Taxi works from 6AM to night 12AM
- The admin can able to track taxi's earnings , taxis current spot and he can reset the details at the end of the day to their original position
- The admin can able to add new taxis to the database
- All taxi’s are initially stationed at their original position.
- When a customer books a Taxi, a free taxi at that location is allocated
- If no free taxi is available at that location, a free taxi at the nearest location is allocated.
- If two taxi’s are free at the same point, one with lower earning is allocated
- Note that the taxi only charges the customer from the pickup point to the drop point. Not the distance it travels from an adjacent point to pickup the customer.
- If no taxi is free at that time, booking is rejected
