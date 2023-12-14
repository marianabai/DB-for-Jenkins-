package co.wedevx.digitalbank.automation.ui.runners;


import io.cucumber.junit.platform.engine.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

//to generate Cucumber reports we need to create a runner class

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features")
@ConfigurationParameter(key=GLUE_PROPERTY_NAME, value = "co.wedevx.digitalbank.automation.ui.steps")
@IncludeTags("Test")
//@Cucumber
//@CucumberOptions(features = "classpath:feature", tags = "@component-test",
//        glue = {"feature.component"},
//        plugin = {"json:${project.build.directory}/CucumberReports/report.json"})


public class UiRegressionRunner {

}
