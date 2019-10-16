package plivo_sdet.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		plugin = {"json:target/Cucumber.json"},
		glue = {"classpath:plivo_sdet.stepdefs"}
		)
public class TestRunner  extends AbstractTestNGCucumberTests {
	
}