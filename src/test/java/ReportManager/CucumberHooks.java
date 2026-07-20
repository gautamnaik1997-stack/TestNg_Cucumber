package ReportManager;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import Core.BaseClass;

import static ReportManager.ExtentManager.extent;

public class CucumberHooks {


    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        // Initialize Extent Report
        ExtentManager.getInstance("cucumber");
        ExtentTestManager.startTest("cucumber",scenario.getName());
        extent.setSystemInfo("Browser", BaseClass.browser);
        extent.setSystemInfo("Environment", BaseClass.environment);
        extent.setSystemInfo("Execution Type", BaseClass.executionType);
        extent.setSystemInfo("Operating System", BaseClass.operatingSystem);
        ExtentTestManager.assignCategory(scenario.getSourceTagNames().toArray(new String[0]));
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