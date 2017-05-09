# exBackend

## Branch Docker

<strong>Updated 2017-05-09</strong><br>
The branch now contains a docker-compose.yml file
- Package the application with <code>mvn clean package</code>. This will make the Dockerfile for the Spring-Bot application.
- Run <code>docker-compose up</code> to start the containers. The MySQL image will create the database on startup.
- The Spring-Boot application will also start with the above command, but the logger doesn't seem to be able to connect to the database from inside a docker container.
- Start the Spring-Boot application in a normal manner: <code>mvn spring-boot:run</code>. This will trigger flyway to create all the required tables and the application should work.
