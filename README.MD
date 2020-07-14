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


````

##5 features we were loooking for
````
- Launching test cases programmatically up on an event.
- Test Step dependency - If a test case step fails, the next steps should not be performed for that test data.
- Ordering test cases in a user defined order. - JUnit supports this by providing the necessary annotations for this.
- Report generation with specifics around the order of execution and failures. It should be not writing it to the file system
- For each test data being tested with, any failures should be recorded in the database accordingly.
