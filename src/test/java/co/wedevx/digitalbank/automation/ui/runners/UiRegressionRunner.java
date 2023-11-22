package co.wedevx.digitalbank.automation.ui.runners;


import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

//to generate Cucumber reports we need to create a runner class

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features")
@ConfigurationParameter(key=GLUE_PROPERTY_NAME, value = "co.wedevx.digitalbank.automation.ui.steps")
@IncludeTags("Test")

public class UiRegressionRunner {

}
