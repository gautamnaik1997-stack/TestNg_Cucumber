package Hooks;

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
    @Before
    public void setup() throws Exception {
        Properties p = new Properties();
        String environment = System.getProperty("environment", "QA");
        String configFile = System.getProperty("user.dir")+ "//src//test//resources//Config//" + environment.toLowerCase() + ".properties";
        FileReader file = new FileReader(configFile);
        p.load(file);

        String executionType = System.getProperty("executionType", p.getProperty("executiontype"));
        String browser = System.getProperty("browser", p.getProperty("browser"));
        String os = System.getProperty("os", "Windows");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        DriverManager.initializeDriver(os,browser,executionType,headless);
        DriverFactory.getDriver().manage().window().maximize();
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
