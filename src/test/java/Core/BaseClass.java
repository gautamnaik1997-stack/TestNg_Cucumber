package Core;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


public class BaseClass {
    public Logger logger;
    public Properties p;

    @Parameters({"OS", "Browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(String os, String br) throws Exception {
        logger = LogManager.getLogger(this.getClass());
        String environment = System.getProperty("environment", "QA");
        String configFile = System.getProperty("user.dir")+ "//src//test//resources//config//" + environment.toLowerCase() + ".properties";
        p=new Properties();
        FileReader file = new FileReader(configFile);
        p.load(file);
        String executionType = System.getProperty("executionType", p.getProperty("executiontype"));
        String browser = System.getProperty("browser", br);
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        DriverManager.initializeDriver(os,browser,executionType,headless);
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverFactory.getDriver().get(p.getProperty("appUrl"));
    }

    @AfterClass(alwaysRun = true)
    public void teardown(){
        if(DriverFactory.getDriver()!=null) {
            DriverFactory.getDriver().quit();
            DriverFactory.unload();
        }
    }

    public static String captureScreenshot(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        String path = System.getProperty("user.dir")+"//screenshots//" + testName + "_" + timeStamp +".png";
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(path);
        targetFile.getParentFile().mkdirs();
        FileUtils.copyFile(sourceFile, targetFile);
        return path;
    }

    public static String getTitle(){
       return DriverFactory.getDriver().getTitle();
    }

    public static String getRandomString(){
        return RandomStringUtils.randomAlphabetic(5);
    }
    public static String getRandomEmail(){
        return RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";
    }
    public static String getRandomPhoneNumber(){
        return RandomStringUtils.randomNumeric(10);
    }
}
