package edu.balu.test.automate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.AllureConstants;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.model.Allure2ModelJackson;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.model.TestResultContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;


public class SocratesLogWriter implements AllureResultsWriter {

    private static final Logger LOG = LoggerFactory.getLogger(SocratesLogWriter.class);

    private final ObjectMapper mapper;

    public SocratesLogWriter() {
        this.mapper = Allure2ModelJackson.createMapper();
    }

    @Override
    public void write(final TestResult testResult) {
        final String testResultName = Objects.isNull(testResult.getUuid())
                ? generateTestResultName()
                : generateTestResultName(testResult.getUuid());

        try {
            LOG.info(testResultName+":{}",  mapper.writeValueAsString(testResult));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void write(final TestResultContainer testResultContainer) {
        final String testResultContainerName = Objects.isNull(testResultContainer.getUuid())
                ? generateTestResultContainerName()
                : generateTestResultContainerName(testResultContainer.getUuid());

        try {
            LOG.info(testResultContainerName+":{}", mapper.writeValueAsString(testResultContainer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void write(final String source, final InputStream attachment) {
        LOG.info("CANNOT write attachments to the logs");
    }

    protected static String generateTestResultName() {
        return generateTestResultName(UUID.randomUUID().toString());
    }

    protected static String generateTestResultName(final String uuid) {
        return uuid + AllureConstants.TEST_RESULT_FILE_SUFFIX;
    }

    protected static String generateTestResultContainerName() {
        return generateTestResultContainerName(UUID.randomUUID().toString());
    }

    protected static String generateTestResultContainerName(final String uuid) {
        return uuid + AllureConstants.TEST_RESULT_CONTAINER_FILE_SUFFIX;
    }
}

