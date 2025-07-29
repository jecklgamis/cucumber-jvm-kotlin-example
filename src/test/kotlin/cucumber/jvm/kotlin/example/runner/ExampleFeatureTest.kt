package cucumber.jvm.kotlin.example.runner

import io.cucumber.junit.platform.engine.Constants.*
import org.junit.platform.suite.api.*

@Suite
@IncludeEngines("cucumber")
@SelectPackages("steps", "features")
@ConfigurationParameters(
    ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps"),
    ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @Wip"),
    ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty"),
    ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber/index.html")
)
class ExampleFeatureTest