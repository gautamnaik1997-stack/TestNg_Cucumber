package ReportManager;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import Core.BaseClass;

public class CucumberHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        // Initialize Extent Report
        ExtentManager.getInstance("cucumber");
        ExtentTestManager.startTest("cucumber",scenario.getName());
    }
    @After
    public void afterScenario(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            ExtentTestManager.fail(new Exception("Scenario Failed"));
            String path = BaseClass.captureScreenshot("cucumber/" + scenario.getName());
            ExtentTestManager.attachScreenshot(path);
        }

        else {
            ExtentTestManager.pass("Scenario Passed");
        }
        ExtentManager.flushReport();
        ExtentTestManager.unload();
    }

}