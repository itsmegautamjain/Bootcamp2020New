package runnerForCampaign;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/resources/features_CampaignModule/CreateCampaign.feature",glue="src/main/java/mySalesforcePages",
   monochrome=true)
public class RunnerForCampaign extends AbstractTestNGCucumberTests {

	
}
