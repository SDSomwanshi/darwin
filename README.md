# RecipeManager Back End
This project was generated with Spring boot version 2.0.0.RELEASE

#Development Server
Run RecipeManagerApplication for a local profile on local server. Navigate to http://localhost:8080/api/

#Build
Run `gradle clean build` to build the backend project. Gradle version 4.10.3 is used

#Api layer
Swagger open api version 3.0.4 is used to generate rest layer under generated directory in buildDirectory
after successful build of project

#Api Testing
Postman collection has added under the posmancollection directory of all exposed api. Run postman collection
to execute backend apis

#Running unit Test
Run `gradle test` to execute unit tests via junit and mockito

#Database
H2 embedded database is used to store the data via JPA

#UI
README.md file is available to run recipemanager-ui project via angular js

#Git repository
https://github.com/SDSomwanshi/recipemanager
