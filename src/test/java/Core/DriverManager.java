package Core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private DriverManager() {
        // Prevent object creation
    }
    public static void initializeDriver(String os, String browser, String executionType, boolean headless)
            throws Exception {

        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions = new EdgeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // ==========================
        // Headless Configuration
        // ==========================

        if(headless) {

            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--no-sandbox");

            edgeOptions.addArguments("--headless=new");
            edgeOptions.addArguments("--disable-gpu");
            edgeOptions.addArguments("--disable-extensions");
            edgeOptions.addArguments("--disable-popup-blocking");
            edgeOptions.addArguments("--disable-infobars");
            edgeOptions.addArguments("--remote-debugging-port=0");
            edgeOptions.addArguments("--disable-dev-shm-usage");
            edgeOptions.addArguments("--no-sandbox");
            edgeOptions.addArguments("--window-size=1920,1080");

            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("--width=1920");
            firefoxOptions.addArguments("--height=1080");
        }

        // ==========================
        // Headless Configuration
        // ==========================
        if(browser==null || browser.trim().isEmpty()) {
            browser = "Edge";
        }
        // ==========================
        // Remote Execution
        // ==========================
        if(executionType.equalsIgnoreCase("remote")) {
            String huburl = "http://localhost:4444";
            switch(browser.toLowerCase()) {
                case "chrome": chromeOptions.setPlatformName(os);
                    DriverFactory.setDriver(new RemoteWebDriver(new URL(huburl), chromeOptions));break;
                case "edge": edgeOptions.setPlatformName(os);
                    DriverFactory.setDriver(new RemoteWebDriver(new URL(huburl), edgeOptions));break;
                case "firefox": firefoxOptions.setPlatformName(os);
                    DriverFactory.setDriver(new RemoteWebDriver(new URL(huburl), firefoxOptions));break;
                default: throw new IllegalArgumentException("Inavlid browser: "+ browser);
            }
        }
        // ==========================
        // Local Execution
        // ==========================
        if(executionType.equalsIgnoreCase("local")) {
            switch(browser.toLowerCase()) {
                case "chrome": DriverFactory.setDriver(new ChromeDriver(chromeOptions));break;
                case "edge": DriverFactory.setDriver(new EdgeDriver(edgeOptions));break;
                case "firefox": DriverFactory.setDriver(new FirefoxDriver(firefoxOptions)); break;
                default: throw new IllegalArgumentException("Inavlid browser: "+ browser);
            }
        }
        else {throw new IllegalArgumentException(
                    "Invalid execution type : " + executionType);
        }

        // ==========================
        // Validation
        // ==========================
        if (DriverFactory.getDriver() == null) {
            throw new RuntimeException(
                    "WebDriver was not initialized. Browser = " + browser +
                            ", ExecutionType = " + executionType
            );
        }
        // ==========================
        // Common Browser Setup
        // ==========================
        DriverFactory.getDriver().manage().deleteAllCookies();
        if(headless) {
            DriverFactory.getDriver().manage().window().maximize();
        }
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
