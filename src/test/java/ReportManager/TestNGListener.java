package ReportManager;

import java.io.IOException;
import java.util.List;

import Core.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import Core.BaseClass;

public class TestNGListener implements ITestListener {

    ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {

        extent = ExtentManager.getInstance("testng");

        String className = context.getClass().getName();

        extent.setSystemInfo("TestCase", className);

        String os = context.getCurrentXmlTest().getParameter("OS");

        extent.setSystemInfo("Operating System", os);

        String browser = context.getCurrentXmlTest().getParameter("Browser");

        extent.setSystemInfo("Browser", browser);

        List<String> groups = context.getCurrentXmlTest().getIncludedGroups();

        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest("testng",
                result.getMethod().getMethodName()
        );
        ExtentTestManager.assignCategory(
                result.getMethod().getGroups()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.pass(result.getMethod().getMethodName() + " executed successfully");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.fail(result.getThrowable());

        try {

            String path = BaseClass.captureScreenshot("testng/" + result.getMethod().getMethodName());
            ExtentTestManager.attachScreenshot(path);

        }

        catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager.skip(result.getMethod().getMethodName() + " skipped");

    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        ExtentTestManager.unload();

    }

}