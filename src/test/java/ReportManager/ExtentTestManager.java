package ReportManager;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private ExtentTestManager() {
    }

    public static void startTest(String reportType, String testName) {
        ExtentManager.getInstance(reportType);
        test.set(ExtentManager.createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void pass(String message) {
        getTest().pass(message);
    }

    public static void fail(Throwable throwable) {
        getTest().fail(throwable);
    }

    public static void skip(String message) {
        getTest().skip(message);
    }

    public static void info(String message) {
        getTest().info(message);
    }

    public static void assignCategory(String... category) {
        getTest().assignCategory(category);
    }

    public static void attachScreenshot(String path) {

        try {
            getTest().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void unload() {
        test.remove();
    }

}