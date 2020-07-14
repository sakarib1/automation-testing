package edu.balu.test.automate.tc.txa;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VerificationLoggerExtension implements TestWatcher, AfterAllCallback {

    private static final Logger LOG = LoggerFactory.getLogger(VerificationLoggerExtension.class);

    private static final Namespace namespace = Namespace.create(VerificationLoggerExtension.class);

    private List<TestExecutionStatus> testExecutionStatus = new ArrayList<>();

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOG.info("TEST-CASE:{}:DISABLED;REASON:{}", context.getDisplayName(), reason.orElse("No reason"));

        testExecutionStatus.add(TestExecutionStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOG.info("TEST-CASE:{}:SUCCESS ", context.getDisplayName());
        testExecutionStatus.add(TestExecutionStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOG.info("TEST-CASE:{}:ABORTED;REASON:{}", context.getDisplayName(), cause.getMessage());
        testExecutionStatus.add(TestExecutionStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOG.info("Test Case:{}:FAILED;REASON:{}", context.getDisplayName(), cause.getMessage());
        testExecutionStatus.add(TestExecutionStatus.FAILED);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestExecutionStatus, Long> summary = testExecutionStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LOG.info("TEST-SUMMARY for {} {}", context.getDisplayName(), summary.toString());
    }

    private enum TestExecutionStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED
    }


}