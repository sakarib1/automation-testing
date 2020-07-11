package edu.balu.test.automate.tc.txa;

import com.jayway.jsonpath.JsonPath;
import edu.balu.test.automate.model.txa.TransactionAccount;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TransactionAccount Lookup ACCEPTANCE-TEST")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("acceptance-tests")
@ExtendWith(VerificationLoggerExtension.class)
public class TxAVerificationTestSuite extends TestBase{

    static Logger log = LoggerFactory.getLogger(TxAVerificationTestSuite.class);

    public TxAVerificationTestSuite(){
        log.info("TransactionLookupTestSuite constructor");
    }

    static List<TransactionAccount> dataSource(){
        log.info("DataSource Executed!!!!");

        //         TODO: connect to data source and gather data
//         TODO: Prepare data for executing steps repeatedly for each one of the data records identified


        List<TransactionAccount> list = new ArrayList<>();
        list.add(new TransactionAccount(1L,"Vernon","Harper","egestas.rhoncus.Proin@massaQuisqueporttitor.org","Financial Analysis", new String[]{"Accounting", "Statistics"}));
        list.add(new TransactionAccount(3L,"Reece","Jason","tincidunt.dui@ultricessit.co.uk","Computer Science", new String[]{"Calculus","Statistics","Algorithms","Software Development", "Ethics"}));
        list.add(new TransactionAccount(2L,"Murphy","Holmes","faucibus.orci.luctus@Duisac.net","Financial Analysis", new String[]{"Accounting", "Statistics"}));
        return list;
    }

    @ParameterizedTest
    @Order(1)
    @Feature("TransactionAccount Migration Feature")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("dataSource")
    void verifyTransactionAccountMigration(TransactionAccount transaction){
        log.info("executing testTransactionAccountLookup");
        Response response = lookupTxAOnTargetSystem(transaction);
        verifyBillToCount(transaction,response);
        verifyBillingAdmins(transaction,response);

    }

    @Step("LookingUp TxA on target system with id = {transaction.id}.")
    Response lookupTxAOnTargetSystem(TransactionAccount transaction){
        log.info("executing lookupTxAOnTargetSystem for TxA-Id#{}",transaction.getId() );

        Response response= RestAssured.given()
                .pathParam("id",transaction.getId())
                .when()
                // .get("http://localhost:8085/student/{id}");
                .get("/{id}");
        
        // response.prettyPrint();

        response.then().statusCode(200);
        return response;
    }

    @Step("Verify Billing Admin for TxA id = {transaction.id}")
    Response verifyBillingAdmins(TransactionAccount transaction,Response response){
        log.info("executing verifyBillingAdmins for TxA-Id#{}",transaction.getId() );

        String jsonResponse = response.asString();
        assertEquals(transaction.getId().intValue(), (Integer) JsonPath.read(jsonResponse,"$.id"));
        assertEquals(transaction.getFirstName(), JsonPath.read(jsonResponse,"$.firstName"));

        return response;
    }

    @Step("Verify Billing Address Count for TxA id = {transaction.id}")
    Response verifyBillToCount(TransactionAccount transaction,Response response){
        log.info("executing verifyBillToCount for TxA-Id#{}",transaction.getId() );

        TransactionAccount txa = response.body().as(TransactionAccount.class);

//        String jsonResponse = response.asString();
//        String[] expected = JsonPath.read(jsonResponse,"$.courses[*]");
        //assertEquals(transaction.getCourses(),containsInAnyOrder(txa.getCourses()));
        assertEquals(transaction.getCourses().length, txa.getCourses().length,"No.Of Bill-To Addresses do not match");

        verifyBillToAddress(transaction,response);
        return response;
    }

    @Step("Verify Billing Address for TxA id = {transaction.id}")
    Response verifyBillToAddress(TransactionAccount transaction,Response response){
        log.info("executing verifyBillToAddress for TxA-Id#{}",transaction.getId() );

        String jsonResponse = response.asString();
        assertEquals(transaction.getId().intValue(), (Integer) JsonPath.read(jsonResponse,"$.id"));
        assertEquals(transaction.getProgramme(), JsonPath.read(jsonResponse,"$.programme"));

        return response;
    }

    @ParameterizedTest
    @Order(2)
    @Feature("InvoiceGroup Migration Feature")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("dataSource")
    void verifyInvoiceGroupMigration(TransactionAccount transaction){
        log.info("executing verifyInvoiceGroupMigration");
        assertTrue(true);

    }
}
