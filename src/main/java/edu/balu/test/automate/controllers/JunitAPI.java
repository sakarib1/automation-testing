package edu.balu.test.automate.controllers;

import edu.balu.test.automate.SocratesLogWriter;
import edu.balu.test.automate.tc.billto.BillToSubscriptionTests;

import edu.balu.test.automate.tc.txa.TxAVerificationTestSuite;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.junitplatform.AllureJunitPlatform;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherConfig;
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

    @GetMapping("/billtoTests")
    public ResponseEntity<TestExecutionSummary> runJUnits(){

        String pkgName = BillToSubscriptionTests.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(false, false, pkgName,null);
        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }



    @GetMapping("/txaStepsTest")
    public ResponseEntity<TestExecutionSummary> runRestAssuredMigrationTests(@RequestParam boolean generateAllureReport){

        String pkgName = TxAVerificationTestSuite.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(true, generateAllureReport,  pkgName,"acceptance-tests","smoke");


        return ResponseEntity.ok()
                .header("Custom-Header", "summaryReport")
                .body(summary);
    }


    @PostMapping("/executeFilteredTests")
    public ResponseEntity<TestExecutionSummary> runRestAssuredMigrationTests(@RequestBody List<String> tagsList,@RequestParam boolean generateAllureReport){

        String pkgName = TxAVerificationTestSuite.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(true, generateAllureReport, pkgName, tagsList.toArray(new String[tagsList.size()]));


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

    private TestExecutionSummary discoverAndLaunchJunitTestCases(boolean registerAllureListener, boolean generateAllureReport, String pkgName, String... tags){
        LauncherDiscoveryRequest request = buildLauncherDiscoveryRequest(pkgName,tags);

        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
        AllureJunitPlatform allureListener  = new AllureJunitPlatform();
        if(registerAllureListener) {
            if(!generateAllureReport)
                allureListener = new AllureJunitPlatform(new AllureLifecycle(new SocratesLogWriter()));
        }
        LauncherConfig launcherConfig = LauncherConfig.builder()
                .enableTestEngineAutoRegistration(true)
                .enableTestExecutionListenerAutoRegistration(false)
                .addTestEngines()
                .addTestExecutionListeners(summaryListener,allureListener)
                .build();

        Launcher launcher = LauncherFactory.create(launcherConfig);
        launcher.discover(request);
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
