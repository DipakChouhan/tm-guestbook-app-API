# tm-guestbook-app-API
A Spring boot REST API project to support Guest Book Application. This contains all the APIs required for our Guest Book Application to work properly.
Below are few activities performed in this application using endpoints:
1. New user can be created using register endpoint.
2. User can add either a text or a image entry in guest book.
3. User can view, edit and delete his entry.
4. Admin can view all the guest book entries.
5. Admin can approve, edit, delete a particular entry.

# Pre requisite
1. Maven
2. Java 15
3. Any IDE, preferably Intellij IDEA (Optional)

# Steps to build and run
1. Clone the project to local machine using command 
> git clone https://github.com/DipakChouhan/tm-guestbook-app-API.git
2. Use maven command to generate the JAR file 
> mvn clean install
3. Once the jar file is created, use below command to start the application
> java -jar <complete_path_to_jar_file.jar>
