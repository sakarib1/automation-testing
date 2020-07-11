package edu.balu.test.automate;

import io.qameta.allure.junitplatform.AllureJunitPlatform;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class TestingMain {

    static Logger log = LoggerFactory.getLogger(TestingMain.class);


    public static void main(String[] args){
        String pkgName = "";//TxAVerificationTestSuite.class.getPackage().getName();
        TestExecutionSummary summary = discoverAndLaunchJunitTestCases(pkgName,true);

    }

    private static LauncherDiscoveryRequest buildLauncherDiscoveryRequest(String pkgName){
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(selectPackage(pkgName))
                //.filters(includeTags("smoke"))
//                .configurationParameter("junit.jupiter.execution.parallel.enabled",
//                        "true")
//                 .configurationParameter(
//                         "junit.jupiter.extensions.autodetection.enabled",
//                         "true")
                .build();
        return request;
    }

    private static TestExecutionSummary discoverAndLaunchJunitTestCases(String pkgName, boolean registerAllureListener){
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
