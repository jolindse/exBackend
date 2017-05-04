# exBackend

## Branch Docker
- To install and run a MySQL docker image, please run:
<br><code>docker run --name mysql -d -e MYSQL_ROOT_PASSWORD=kanban -p 3306:3306 mysql</code>
- To initialize the DB, please run:<br>
<code>mvn flyway:migrate</code>
- To build the exBackend docker image, please run:<br>
<code>sudo mvn clean package -DskipTests</code>
- To run the exBackend docker image, please run:
<br><code>docker run --name exbackend -d -p 8080:8080 ex-backend</code>

### Issues
Something is wrong with the logger configuration src/main/resources/logback.xml
<p>If you want to run the application by <code>mvn spring-boot:run</code> it works to connect to the dockerized db, but you have to remove all the tables first in order to get flyway migration to work.


