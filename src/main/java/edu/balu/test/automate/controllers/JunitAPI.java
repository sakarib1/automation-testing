package edu.balu.test.automate.controllers;

import edu.balu.test.automate.restAssured.junit.SimpleRestAssuredTestCases;
import edu.balu.test.automate.simple.junit.SimpleJUnitEmployeeTestCases;
import edu.balu.test.automate.student.junit.StudentLookupTestSuite;
import io.qameta.allure.junitplatform.AllureJunitPlatform;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@RestController
public class JunitAPI {

    Logger log = LoggerFactory.getLogger(JunitAPI.class);

    @GetMapping("/employeeTests")
    public ResponseEntity<TestExecutionSummary> runJUnits(){

        String pkgName = SimpleJUnitEmployeeTestCases.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(pkgName,false);
        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }

    @GetMapping("/studentTests")
    public ResponseEntity<TestExecutionSummary> runRestAssuredJUnits(){

        String pkgName = SimpleRestAssuredTestCases.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(pkgName,true);


        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }

    @GetMapping("/studentStepsTest")
    public ResponseEntity<TestExecutionSummary> runRestAssuredStudentTests(){

        String pkgName = StudentLookupTestSuite.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(pkgName,true);


        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }


    private LauncherDiscoveryRequest buildLauncherDiscoveryRequest(String pkgName){
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(selectPackage(pkgName))
                //.filters(includeTags("smoke"))
                //.configurationParameter("junit.jupiter.execution.parallel.enabled",
                //        "true")
                // .configurationParameter(
                //         "junit.jupiter.extensions.autodetection.enabled",
                //         "true")
                .build();
        return request;
    }

    private TestExecutionSummary discoverAndLaunchJunitTestCases(String pkgName, boolean registerAllureListener){
        LauncherDiscoveryRequest request = buildLauncherDiscoveryRequest(pkgName);
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
