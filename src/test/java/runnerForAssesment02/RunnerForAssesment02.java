package runnerForAssesment02;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/resources/features_Assesment02/Assesment02.feature",
glue="steps_Campaign",
monochrome=true)
public class RunnerForAssesment02 extends AbstractTestNGCucumberTests {

}
