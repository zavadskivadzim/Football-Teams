#### Build project
Run terminal command in project directory:
```
mvn clean install
``` 

###Start Rest application
To start Rest server (rest-app module) with Postgresql Database:
```
java -Ddb_user=zavadski -Ddb_pass=zavadski -jar ./rest-app/target/rest-app-0.0.1-SNAPSHOT.jar
```
To start Rest server (rest-app module) with H2 Database:
```
java -jar ./rest-app/target/rest-app-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
```
To start Web-application:
```
java -jar ./web-app/target/web-app-0.0.1-SNAPSHOT.jar
```
