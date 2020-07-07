# LAUNCHING JUNITS PROGRAMMATICALLY

This is a reference implmentation for discovering and launching Junit Test Cases programmtically.

##Demonstrates usage of:
- Junit5
- RestAssured
- Allure Reporting Framework
- Spring Boot
- Swagger-UI

##Setup:
- Student App docket tejasvi/student-app
````
docker pull tejasn1/student-app   
docker run -p 8085:8080 -d tejasn1/student-app
````
- Allure setup
````

- Mac OSX:

brew install allure

- Other OS Steps: https://docs.qameta.io/allure/#_installing_a_commandline

application.properties
allure.results.directory=target/allure-results


````

##Run Configuration
````
VM options :

-javaagent:"/Users/bsakariboina/.m2/repository/org/aspectj/aspectjweaver/1.9.4/aspectjweaver-1.9.4.jar"

- Run the tests using the Rest APIs

- in the project directory run the below command.

allure serve allure-results/

It opnes http://192.168.1.2:64602/index.html

````

Why Allure??
- Easy to understand and short learning curve. Keeping things simple.
- Good reporting
- Junit5 compatibility