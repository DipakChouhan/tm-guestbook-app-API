# tm-guestbook-app-API

A Spring boot REST API project to support Guest Book Application. This contains all the APIs required for our Guest Book Application to work properly.
Below are few activities performed in this application using endpoints:
1. New user can be created using register endpoint.
2. User can add either a text or a image entry in guest book.
3. User can view, edit and delete his entry.
4. Admin can view all the guest book entries.
5. Admin can approve, edit, delete a particular entry.

## Pre requisite

1. Maven
2. Java 15
3. Any IDE, preferably Intellij IDEA (Optional)

## Steps to build and run

1. Clone the project to local machine using command 
> git clone https://github.com/DipakChouhan/tm-guestbook-app-API.git
2. Use maven command to generate the JAR file 
> mvn clean install
3. Once the jar file is created, use below command to start the application
> java -jar <complete_path_to_jar_file.jar>

## Running test cases

Make sure you are in folder where pom.xml file resides.
1. Run all test cases
> mvn -Dtest=GuestBookMainTests test
2. Run single test case (Replace method-name with name of the test method you want to run)
> mvn -Dtest=GuestBookMainTests#method-name test

## Creating admin user
1. First create a regular user using registraion API endpoint.
2. Update the ROLE of the user in database to `ADMIN` using command `update user set role = 'ADMIN' where user_email = <user_email>`.
