# Springbank
Implements Spring to form a simple bank.

## Requirements
* Maven 3.3.9 or higher.
* Java Development Kit 1.8.0
* Java 8.0

## Database
The dialect for the database is MySQL. Table structure and sample data have been included in `/db/`.
The configuration for MySQL can be found in `src/main/resources`.
Please note that the actual database password has been changed.

## Setup
The current configuration for MySQL contains an incorrect password.
To setup the application you have update `spring.datasource.password` with the provided password.
If you do not have a password you wil have to set-up your own database with the provided
table structure and sample data files and update the configuration accordingly

## Running the application
Before running the application run `mvn install`.
Run the application by running `mvn spring-boot:run`.

## Testing
Testing can be done easily by running `mvn test`.

