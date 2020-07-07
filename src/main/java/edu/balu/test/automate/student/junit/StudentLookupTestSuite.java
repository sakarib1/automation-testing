package edu.balu.test.automate.student.junit;

import com.jayway.jsonpath.JsonPath;
import edu.balu.test.automate.model.student.Student;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Student Lookup Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentLookupTestSuite {

    static Logger log = LoggerFactory.getLogger(StudentLookupTestSuite.class);

    @BeforeAll
    @DisplayName("Fetch data from Book Keeping DB for this cohort")
    static void  initializeTestData(){

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
        RestAssured.basePath = "/student";

//         TODO: connect to data source and gather data
//         TODO: Prepare data for executing steps repeatedly for each one of the data records identified
    }

    static List<Student> dataSource(){
        log.info("DataSource Executed!!!!");
        List<Student> list = new ArrayList<>();
        list.add(new Student(1L,"Vernon","Harper","egestas.rhoncus.Proin@massaQuisqueporttitor.org","Financial Analysis", new String[]{"Accounting", "Statistics"}));
        list.add(new Student(2L,"Murphy","Holmes","faucibus.orci.luctus@Duisac.net","Financial Analysis", new String[]{"Accounting", "Statistics"}));
        return list;
    }

    @ParameterizedTest
    @Order(1)
    @Feature("Student Lookup Feature")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("dataSource")
    void testStudentLookup(Student student){
        log.info("executing testStudentLookup");
        Response response = performStudentLookup(student);
        performAssertion(student,response);
    }

    @Step("LookingUp Student Data {student.id} / {student.firstName}.")
    Response performStudentLookup(Student student){
        log.info("executing performStudentLookup");

        Response response= RestAssured.given()
                .pathParam("id",student.getId())
                .when()
                // .get("http://localhost:8085/student/{id}");
                .get("/{id}");

        response.then().statusCode(200);
        return response;
    }

    @Step("Comparing Student Data {student.id} / {student.firstName}.")
    Response performAssertion(Student student,Response response){
        log.info("executing performAssertion");

        String jsonResponse = response.asString();
        assertEquals(student.getId().intValue(), (Integer) JsonPath.read(jsonResponse,"$.id"));
        assertEquals(student.getLastName(), JsonPath.read(jsonResponse,"$.firstName"));

        return response;
    }
}
