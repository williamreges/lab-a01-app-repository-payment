package com.example.payment.bdd;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParametersResource;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, " +
        "html:target/cucumber-reports/cucumber-report.html, " +
        "json:target/cucumber-reports/cucumber.json")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.payment.bdd")
@ConfigurationParametersResource("cucumber.properties")
public class RunCucumberIT {
}
