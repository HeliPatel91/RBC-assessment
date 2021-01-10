# Spring Boot Dow Jones Index Application

## About
 An application to upload weekly stock data via CSV file. It allows to retrieve the records by passing stock ticker.
 It has an JSON interface to create the records which can be used by other application to insert the records on certain functionality.
 
## Technology stack & other Open-source libraries
### Data
* 	[H2 Database Engine](https://www.h2database.com/html/main.html) - Java SQL database. Embedded and server modes; in-memory databases

### Server - Backend
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 1.8
* 	[Maven](https://maven.apache.org/) - Dependency Management                                                                           

###  Libraries and Plugins
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
*   [Spring Boot](https://spring.io/) - With Spring Boot in your app, just a few lines of code is all you need to start building services like a boss.
*   [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/) - Commons CSV reads and writes files in variations of the Comma Separated Value (CSV) format.

### Others 
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system

## Features and To-Do

* 	[x] REST API
* 	[x] Bulk CSV data upload
* 	[x] Retrieval of stocks by stock ticker
* 	[x] creation of record by JSON request - which can be integrated by other application
* 	[x] Unit Tests
* 	[x] Software documentation - [Swagger](https://swagger.io/)
* 	[x] H2 console to query and view DB data
* 	[ ] External Database instead of in-memory
*   [ ] Functional Tests, Integration Tests
* 	[ ] Validate each field for not null and not empty
*   [ ] New table for error records which has data issues
*   [ ] Add CSV Download functionality to retrieve all records in CSV file
*   [ ] Support for more currencies
*   [ ] Date format can be changed to appropriate format to perform calculation
* 	[ ] [Spring Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-profiles) (dev, production, qa, staging, test)
* 	[ ] API [Rate Limiting](https://en.wikipedia.org/wiki/Rate_limiting)
* 	[ ] [Docker](https://www.docker.com/) to deploy on a container
* 	[ ] [Javadoc](https://en.wikipedia.org/wiki/Javadoc)
* 	[ ] [Spring Security](https://spring.io/projects/spring-security)
* 	[ ] [HTTPS](https://en.wikipedia.org/wiki/HTTPS) with (self-signed certificate)[https://en.wikipedia.org/wiki/Self-signed_certificate]

## Getting Started

These instructions will get you a copy of the project and enable you to run on your local machine.

## Installing

#### Running the application with IDE

* 	Clone the Git repository.
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open IntelliJ
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you cloned project
	* Select the project
* 	Choose the Spring Boot Application file (DowJonesIndexApplication.java)
* 	Right Click on the file and Run as Java Application

## Explore Rest APIs

*   Open "http://localhost:8080/swagger-ui.html"

<img src="Screenshots\Swagger_UI.png"/>

*   Try uploading the data file in below 

##### Accessing Data in H2 Database
