package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:Features",
        glue = {"Hooks","stepDefinitions","ReportManager"},
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-report.html"},
        tags= "@sanity"
)

public class LoginRunnerTest extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
