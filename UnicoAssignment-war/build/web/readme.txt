Unico Assignment Prepared by Ajitabh Jha
_________________________________________________________________________________________________
This Unico assignment is an interesting blend of variety of webservices options available today. Traditionally SOAP based web services have been used and have been very widely used in the indsutry. However, these days REST based web services are gaining popularity. RESTful services as they are called is nothing but an architectural style chosen by Architects and developers given the simplicity and similarity of it with web requests. REST services are based on HTTP protocol and uses the operations permitted by HTTP - PUT, POST, GET and DELETE. The operations are designed in such a fashion that an appropriate HTTP operation can be assigned to it.
For e.g. a listing or retrieval of a record would typically be a GET request. But adding any records/document will be a PUT operation. POST is suitable for operations which are dealing with either creating new or updating existing resource/record. DELETE, as the name signifies is used to remove a record/resource from server.

_______________________________
Application Server
_______________________________
To achieve all the objectives of this assignment, I have used 
 - Glassfish server with 
 - Netbeans IDE. 
 - I have Jersey based JAX-RS implementation which Sun (or now Oracle's) specification of J2EE based RESTFul services.

As a pre-requisite, the server must be configured to use JMS and JDBC. Following resources must be configured in the server before the application can be started.
 - Create a JMS Connection Factory (JNDI name - jms/UnicoConnectionFactory)
 - Create a JMS queue (JNDI name - jms/unicoAssignmentQueue)
 - Create a JDBC DataSource - (JNDI name jdbc/UnicoDS)

 The Application must be configured for 20 concurrent users and hence the connection pool for both JMS and JDBC must be configured to start with an initial pool of 20.

_______________________________
Database
_______________________________
I have used Apache Derby (Java DB) database for quickly testing the code. The database (UNICO) must contain the below table.

create table "UNICO".GCD
(
	REQUEST_ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
	I1 INTEGER not null,
	I2 INTEGER not null,
	GCD INTEGER,
	CREATION_DATETIME TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
)

_______________________________
Application Assumptions
_______________________________
Application can be deployed as it is on any J2EE Application server provided the pre-requisites are met. This is a typical web application which uses a welcome file "index.html" to provide access to all operations created in this application.
Page can be accessed by something similar to:
http://localhost:8080/UnicoAssignment-war/
OR
http://localhost:8080/UnicoAssignment-war/index.html

where localhost is the servername and 8080 is the port running the application server. Additionally, context root can also be modified as needed.

JQuery based AJAX client is used to consume both RESTFul as well as SOAP based services.
Operations must follow a sequence to get correct results.
1. First two operands must be added to JMS queue. These operands must be non-negative and non-zero integers. Appropriate validation and error messages are displayed if wrong data is entered. Any character other than numbers have been porhibited from entering the two text boxes.
2. The operand's added in JMS will not go to database until GCD is calculated. When GCD is calculated for two numbers from JMS queue, a record is inserted in GCD table in the database. This record contains I1, I2, and GCD with creation time. Since everytime JMS will be popped from start of queue it is guaranteed to follow LIFO order. Creation time will be oldest for oldest items in queue.
Note: Calculate GCD may take soome time if there are no parameters in the JMS queue as it. Ultimately it should time out after a minute or so.
3. Now, the listing of GCDs can be done which is picked from database. Please note: there may be unused items in JMS yet, but it will not fetch them.
4. Finally, a sum of GCDs can be invoked.

The application has been fully tested on Chrome browser.


