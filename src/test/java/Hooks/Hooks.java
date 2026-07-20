package Hooks;

import Core.BaseClass;
import Core.DriverFactory;
import Core.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    @Before(order = 0)
    public void setup() throws Exception {
        Properties p = new Properties();
        BaseClass.environment = System.getProperty("environment", "QA");
        String configFile = System.getProperty("user.dir")+ "//src//test//resources//Config//" + BaseClass.environment.toLowerCase() + ".properties";
        FileReader file = new FileReader(configFile);
        p.load(file);

        BaseClass.executionType = System.getProperty("executionType", p.getProperty("executiontype"));
        BaseClass.browser = System.getProperty("browser", p.getProperty("browser"));
        BaseClass.operatingSystem = System.getProperty("os", "Windows");
        BaseClass.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        DriverManager.initializeDriver(BaseClass.operatingSystem,BaseClass.browser,BaseClass.executionType,BaseClass.headless);
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverFactory.getDriver().get(p.getProperty("appUrl"));
    }

    @After
    public void tearDown(){
        if(DriverFactory.getDriver()!=null){
        DriverFactory.getDriver().quit();
        DriverFactory.unload();
        }
    }
}
