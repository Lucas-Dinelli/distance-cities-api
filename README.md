# distance-cities-api
Project to calculate distance between cities
<br/><br/>

## **Requirements:**

- Spring Boot 2.5.6
- Spring Data JPA
- Java 8
- Maven
- Lombok
- JUnit 5
- Mockito
- Swagger
- PostgreSQL

---

## **Observations:**
  - Handled and customized exceptions
  - Unit tests
  - Deployed in heroku
  - Being on a Heroku free plan, you may need to refresh the page until it loads
  
---

## **Distances**

- Examples between Rio de Janeiro and São Paulo:
  - By name:
    - [https://distance-cities.herokuapp.com/cities/distance/name?from=Rio de Janeiro&ufFrom=RJ&to=São Paulo&ufTo=SP](https://distance-cities.herokuapp.com/cities/distance/name?from=Rio%20de%20Janeiro&ufFrom=RJ&to=S%C3%A3o%20Paulo&ufTo=SP)

  ##
  
  - Choosing unit of measure (meter, kilometer or mile):
    - [https://distance-cities.herokuapp.com/cities/distance/name?from=Rio de Janeiro&ufFrom=RJ&to=São Paulo&ufTo=SP&unit=kilometer](https://distance-cities.herokuapp.com/cities/distance/name?from=Rio%20de%20Janeiro&ufFrom=RJ&to=S%C3%A3o%20Paulo&ufTo=SP&unit=kilometer)
    
  ##
  
  - By id:
    - https://distance-cities.herokuapp.com/cities/distance/id?from=3658&to=5270&unit=kilometer
  

---

## **How to access?**

- Swagger:
  - https://distance-cities.herokuapp.com/swagger-ui/index.html
  
##

- All cities (use page):
  - https://distance-cities.herokuapp.com/cities

##

- City by name (example):
  - [https://distance-cities.herokuapp.com/cities/Rio de Janeiro](https://distance-cities.herokuapp.com/cities/Rio%20de%20Janeiro)

 ---
 
## **For local development**

- Database:

   > * Use PostgreSQL
   > * Install pgAdmin (recommended, but optional)
   > * Create a database called **cities_db**
   > * Use the created database
   > * Download the SQL files from [data source](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL)
   > * Import these files and run each one
   > * Now, create the following extensions:
   <br/>
  
     ~~~sql
     CREATE EXTENSION cube; 
     CREATE EXTENSION earthdistance;
     ~~~
    
##

- In Java:

  > * Open the **application-dev.properties**
  > * Put your username of the database
  > * Put your password of the database
  
  ##

  > * Change the **application.properties** to:
     ~~~
     spring.profiles.active=dev
     ~~~
     
  ##

  > * Now, just run the application and access it with the links provided above replacing **https://distance-cities.herokuapp.com** with:
     ~~~  
     http://localhost:8080
     ~~~
 
