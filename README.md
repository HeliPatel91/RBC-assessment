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
* 	[GIT](https://git-scm.com/) - Free and Open-Source distributed version control system

## Features

* 	[x] REST API
* 	[x] Bulk CSV data upload
* 	[x] Retrieval of stocks by stock ticker
* 	[x] creation of record by JSON request - which can be integrated by other application
* 	[x] Unit Tests
* 	[x] Software documentation - [Swagger](https://swagger.io/)
* 	[x] H2 console to query and view DB data
* 	[x] [Docker](https://www.docker.com/) to deploy on a container

### Future enhancements
* 	[ ] External Database instead of in-memory
*   [ ] Functional Tests, Integration Tests
* 	[ ] Sanity check of each field in the data (current data set has only 2 empty columns)
*   [ ] New table to capture error records for analysis
*   [ ] Add CSV Download functionality to retrieve all records in CSV file
*   [ ] Date format can be changed to appropriate format to perform calculation
* 	[ ] [Spring Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-profiles) (dev, production, qa, staging, test)
* 	[ ] API [Rate Limiting](https://en.wikipedia.org/wiki/Rate_limiting)
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

### Running the application with Maven
```shell
$ git clone https://github.com/HeliPatel91/RBC-assessment.git
$ cd RBC-assessment
$ mvn clean install
$ cd target
$ java -jar RBC-assessment-1.0-SNAPSHOT.jar
```

### Running the application via Docker
```shell
$ git clone https://github.com/HeliPatel91/RBC-assessment.git
$ cd RBC-assessment
$ mvn clean install
$ docker build . -t rbc-assessment (make sure docker desktop is running)
$ docker run -p 8080:8080 rbc-assessment
```

## Explore Rest APIs

*   Open "http://localhost:8080/swagger-ui.html"

<img src="Screenshots\Swagger_UI.png"/>

##### Accessing uploaded Data in H2 Database

###### H2 Console

URL to access H2 console: **http://localhost:8080/h2-console**

Fill the login form as follows and click on Connect:

* 	Saved Settings: **Generic H2 (Embedded)**
* 	Setting Name: **Generic H2 (Embedded)**
* 	Driver class: **org.h2.Driver**
* 	JDBC URL: **jdbc:h2:mem:indexDb**
* 	User Name: **rbc-user**
* 	Password: **rbc-pass**

<img src="Screenshots\H2_console_connection_details.png"/>

*   Verify WEEKLY_INDEX table is created

<img src="Screenshots\H2_console_no_data.png"/>

### Testing of Bulk Upload functionality

*   Try uploading the data file in CSV format in the UploadController -> /api/v1/upload API

<img src="Screenshots\Bulk_upload_API.png"/>

*   Verify the upload is successful

<img src="Screenshots\Bulk_upload_API_successful.png"/>

*   Verify the uploaded data is added in the database

<img src="Screenshots\Bulk_data_uploaded_H2.png"/>

*   Total count is 750 as the data set provided has 750 records

<img src="Screenshots\H2_count_after_bulk_upload.png"/>

*   Try uploading file in different format

<img src="Screenshots\wrong_format_file_upload.png"/>

### Testing of stock retrieval functionality

*   Use IndexController -> /api/v1/stock/ GET API with passing stockTicker = AA

<img src="Screenshots\getByStock_API.png"/>

*   Verify response has 25 elements as it returns both 1 and 2 quarters AA stock

*   Try with stockTicker which does not exists -> it should return empty list

### Testing of single record insertion functionality

*   Use IndexController -> /api/v1/stock/ POST API with payload json as below

<img src="Screenshots\insertRecord_API.png"/>

*   Request body

```json
{
    "quarter": 1,
    "stock": "AA",
    "date": "01/07/2011",
    "open": 15.82,
    "high": 16.72,
    "low": 15.78,
    "close": 16.42,
    "volume": 239655616,
    "percentChangePrice": 3.79267,
    "percentChangeVolumeOverLastWk": "",
    "previousWeeksVolume": "",
    "nextWeeksOpen": 16.71,
    "nextWeeksClose": 15.97,
    "percentChangeNextWeeksPrice": -4.42849,
    "daysToNextDividend": 26,
    "percentReturnNextDividend": 0.182704
}
```

<img src="Screenshots\insertRecord_API_successful.png"/>

*   Verify data uploaded in the database

<img src="Screenshots\H2_console_after_upload_insert.png"/>







