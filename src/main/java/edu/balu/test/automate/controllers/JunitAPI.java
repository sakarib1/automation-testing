package edu.balu.test.automate.controllers;

import edu.balu.test.automate.tc.billto.BillToSubscriptionTests;

import edu.balu.test.automate.tc.txa.TxAVerificationTestSuite;
import io.qameta.allure.junitplatform.AllureJunitPlatform;
import org.assertj.core.util.Arrays;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.List;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.launcher.TagFilter.includeTags;

@RestController
public class JunitAPI {

    Logger log = LoggerFactory.getLogger(JunitAPI.class);

    @GetMapping("/employeeTests")
    public ResponseEntity<TestExecutionSummary> runJUnits(){

        String pkgName = BillToSubscriptionTests.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(false,pkgName,null);
        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }



    @GetMapping("/txaStepsTest")
    public ResponseEntity<TestExecutionSummary> runRestAssuredStudentTests(){

        String pkgName = TxAVerificationTestSuite.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(true, pkgName,"acceptance-tests","smoke");


        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }


    @PostMapping("/executeFilteredTests")
    public ResponseEntity<TestExecutionSummary> runRestAssuredStudentTests(@RequestBody List<String> tagsList){

        String pkgName = TxAVerificationTestSuite.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(true, pkgName, tagsList.toArray(new String[tagsList.size()]));


        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }

    private LauncherDiscoveryRequest buildLauncherDiscoveryRequest(String pkgName,String... tags){
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(selectPackage(pkgName))
                .filters(includeTags(tags))
                //.configurationParameter("junit.jupiter.execution.parallel.enabled",
                //        "true")
                // .configurationParameter(
                //         "junit.jupiter.extensions.autodetection.enabled",
                //         "true")
                .build();
        return request;
    }

    private TestExecutionSummary discoverAndLaunchJunitTestCases( boolean registerAllureListener,String pkgName,String... tags){
        LauncherDiscoveryRequest request = buildLauncherDiscoveryRequest(pkgName,tags);
        Launcher launcher = LauncherFactory.create();

        launcher.discover(request);
        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(summaryListener);

        if(registerAllureListener) {
            AllureJunitPlatform listener = new AllureJunitPlatform();
            launcher.registerTestExecutionListeners(listener);
        }

        launcher.execute(request);

        TestExecutionSummary summary = summaryListener.getSummary();
        log.info("Found {} smoke tests: {} success, {} failures, {} aborts, {} skips",
                summary.getTestsFoundCount(), summary.getTestsSucceededCount(), summary.getTestsFailedCount(),
                summary.getTestsAbortedCount(), summary.getTestsAbortedCount());

        if (summary.getTestsFailedCount() > 0) {
            summary.printFailuresTo(new PrintWriter(System.out), 3);
            // fail("Smoketest Failed !!");
        }


        log.info("executed....");
        return summary;
    }
}
