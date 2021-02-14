package runnerForServiceAppointment;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/resources/features_ServiceAppointmentModule/CreateNewServiceAppointment.feature",
//glue="BootCamp/src/main/java/mySalesforcePages/",
glue="steps_Campaign",
monochrome=true)
public class RunnerForServiceAppointment extends AbstractTestNGCucumberTests {

}