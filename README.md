# Travel-Agency-Project
The Project is part of Education for Sorfware Developer at Control Data Traning(CDT) GmbH.

### Rationale for the project
The program developed in the project is intended to transfer the generated data into a relational database model without redundancy. In addition to the cost and time savings through the automation of this process, input errors by the user would sometimes be completely avoided. In addition, these could be more easily monitored and eliminated.

### project objective
Data records should be transferred from existing Excel tables to a relational database. The development, users should be able to transfer worksheets correctly in the previous database and to call them up if necessary.

### As-is analysis
#### Example:
The application interacted with Excel or CSV files on the user's storage medium in the frontend and an MSSQL server in the backend.
The web application runs on a JBOSS server.
The implementation was done using NetBeans Development environment. 

#### The Java library Resource (the current version is available on the internet)
Libraries | Description 
------------ | ------------
*commons-codec-1.12.jar* | JAR files that enable an XLS file to be converted into a CSV file without any problems
*commons-collections4-4.3.jar* |
*curvesapi-1.06.jar* |
*poi-4.1.0.jar* |
*xmlbeans-3.1.0.jar* |
*rs2xml.jar* | JAR file that enables clean processing with Swing
*sqljdbc4.jar* | JAR file that has been added for database access for SQL
*bootstrap.min.js* | Javascript for web design Script to supplement Optical design
*jquery-3.4.1.min.map* | 
**.css* |

The data recorded in Excel should simply be selected via the web browser and automatically transferred to the database without redundancy. A graphical user interface (GUI) is very suitable for this.
