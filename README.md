# Description

ClusteredData Warehouse
Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB.

## Request logic as following:

* Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
* Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
* System should not import same request twice.
* No rollback allowed, what every rows imported should be saved in DB.

## Deliverables should be ready to work including:
* Use Actual Db, you can select between (Postgres, MySql or MongoDB)
* Workable deployment including sample file (Docker Compose).
* Maven or Gradle project is required for full source code.
* Proper error/exception handling.
* Proper Logging.
* Proper unit testing with respected Coverage.
* Proper documentation using md.
* Delivered over Githhub.com.
* Makefile to streamline running application (plus).

## The implementation.
you can download the project and import it as maven project into eclipse. also you will find in the project file dump file from the database. you can import into MYSQL using this command:
* First, log in to MySQL as root or another user with sufficient privileges to create new databases:

```bash
$ mysql -u root -p
```
* This command will bring you into the MySQL shell prompt. Next, create a new database with the following command. In this example, the new database is called progresssoft :

```bash
musql> CREATE DATABASE progresssoft;
```
* You’ll see this output confirming the database creation.

```bash
Query OK, 1 row affected (0.00 sec)
```
* From the normal command line, you can import the dump file with the following command:

```bash
$ mysql -u root -p 1234 < progresssoft.sql
```
or by import it directly using sqlyog workbench .. etc.
The database used in this project is MYSQL, java backend and at the end create a website and linked it with the backend.

## Usage
You will find in this section the full description about the languages and the frameworks used in this project.

* ### batabase
we used mysql as a database in this project, in our database we create the mane table which is the deals as required in the requirement.
And also create a transaction log table to handle all the errors occur in the procedures.
Also create procedures that contain all the requirements and call it in the backend side. you will find below all the procedures and the signature, description for each one.
* add new deal procedure: this procedure will add a new record into the deals table you will find the signature below.
```bash
PROCEDURE DEALS_ADD_NEW_DEAL(
		OUT P_ERR_CODE 				VARCHAR(4000),
		OUT P_ERR_MESSAGE			VARCHAR(4000),
		OUT P_DEAL_ID 				INT(25),
		IN 	P_FROM_CURRENCY_CODE 	VARCHAR(4),
		IN 	P_TO_CURRENCY_CODE 		VARCHAR(4),
		IN 	P_DEAL_TIME				DATETIME,
		IN 	P_DEAL_AMOUNT 			DOUBLE(24,8)
	)
```
* Update deal procedure: this procedure will update the deal details based on the deal id, you can find the signature below.
```bash
PROCEDURE DEALS_UPDATE_DEAL(
	OUT P_ERR_CODE 				VARCHAR(4000),
	OUT P_ERR_MESSAGE			VARCHAR(4000),
	IN  P_DEAL_ID 				INT,
	IN 	P_FROM_CURRENCY_CODE 	VARCHAR(4),
	IN 	P_TO_CURRENCY_CODE 		VARCHAR(4),
	IN 	P_DEAL_TIME				DATETIME,
	IN 	P_DEAL_AMOUNT 			DOUBLE(24,8)
)
```
* Get deal procedure: this procedure will retrieve all the record from the deal table, you have the id as a in parameter in the procedure, if you passed the id the procedure will select the record with this id only, else if you enter the value as null the procedure will select all the record for you. you can find the signature below.

```bash
PROCEDURE DEALS_GET_DEAL(
	OUT P_ERR_CODE 				VARCHAR(4000),
	OUT P_ERR_MESSAGE			VARCHAR(4000),
	IN  P_DEAL_ID 				INT
)
```
* at the end delete deal procedure: this procedure will delete the deal from the table based on the deal id. you can find the signature belwo.
```bash
PROCEDURE DEALS_DELETE_DEAL(
	OUT P_ERR_CODE 				VARCHAR(4000),
	OUT P_ERR_MESSAGE			VARCHAR(4000),
	IN  P_DEAL_ID 				INT
)
```
* ### batabase
In the java side, we create a connection bool to manage the connection with the database, and we used spring-boot frameworks, handle the exceptions, create logger and at the end we create an APIs for each procedure.

* ### website 
A basic website with HTML ,CSS and JavaScript we called the created APIs in the JavaScript and then connect it with the GUI.

# How to run.
when you import the project as existing maven project go to src/main/java > com.zakaria.deals > main > Application.java and run this file which is the main file.
then run the index.html file to manage the GUI.

for database configuration go to src/main/resources > open dbconfig.properties and set the configuration.  