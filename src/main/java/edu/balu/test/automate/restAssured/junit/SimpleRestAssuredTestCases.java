package edu.balu.test.automate.restAssured.junit;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DisplayName("My RestAssured and Allure Test Cases")
public class SimpleRestAssuredTestCases extends TestBase {

    Logger log = LoggerFactory.getLogger(SimpleRestAssuredTestCases.class);


//    private void styles(){
//        RestAssured.given()
//                .queryParam("","")
//                .when()
//                .get("https://www.google.com")
//                .then();
//
//        RestAssured.given()
//                .expect()
//                .when();
//    }

    @DisplayName("Fetch All Students")
    @Test
    void getAllStudents(){
//        RequestSpecification requestSpecification = RestAssured.given();
//        Response response = requestSpecification.get("http://localhost:8085/student/list");
//        response.prettyPrint();
//        ValidatableResponse validatableResponse = response.then();
//        validatableResponse.statusCode(200);

        log.info("executing getAllStudents()");


        RestAssured.given()
                .when()
                 .get("http://localhost:8085/student/list")
                //.get("/list")
                .then()
                .statusCode(201);

        RestAssured.given()
                .expect()
                .statusCode(200)
                .when()
                .get("http://localhost:8085/student/list");
                //.get("/list");
    }

    @DisplayName("Get Single Student data -QueryParams")
    @Test
    void getOneCSStudent(){

        Map<String, Object> map = new HashMap<>();
        map.put("programme","Computer Science");
        map.put("limit", 1);


       Response response= RestAssured.given()
//                .queryParam("programme","Computer Science")
//                .queryParam("limit", 1)
                //.queryParams("programme","Computer Science","limit", 1)
                .queryParams(map)
                .when()
               // .get("http://localhost:8085/student/list");
               .get("/list");
       response.prettyPrint();


    }

    @DisplayName("Get Single Student data -PathParams")
    @Test
    void getStudentById(){


        Response response= RestAssured.given()
                .pathParam("id","2")
                .when()
               // .get("http://localhost:8085/student/{id}");
                .get("/{id}");
        response.prettyPrint();


    }

    @DisplayName("Get Single Student data -PathParams")
    @Test
    void getStudentSpecificDataOfStudent(){


        Response response= RestAssured.given()
                .pathParam("id","2")
                .when()
                // .get("http://localhost:8085/student/{id}");
                .get("/{id}");
        response.prettyPrint();


    }


    @DisplayName("Fetch Stident data and parse using JSONPath")
    @Test
    void fetchStudentData(){

        String jsonResponse= RestAssured.given()
               // .pathParam("id","2")
                .when()
                // .get("http://localhost:8085/student/{id}");
                .get("/list").asString();

        List<HashMap<String,Object>> allData = JsonPath.read(jsonResponse,"$");

        allData.stream().forEach(System.out::println);

        List<Integer> allIds = JsonPath.read(jsonResponse,"$.[*].id");


        List<Integer> allFinanceStudents = JsonPath.read(jsonResponse,"$.[?@].firstName");


        allIds.stream().forEach(System.out::println);


        int id = JsonPath.read(jsonResponse,"$[2].id");
        String firstName = JsonPath.read(jsonResponse,"$[2].firstName");

        log.info("       Id: {}",id);
        log.info("FirstName: {}",firstName);
    }
}
