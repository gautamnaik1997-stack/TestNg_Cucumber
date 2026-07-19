package ReportManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    private static ExtentReports extent;
    private static String reportName;
    private static String reportType;

    private ExtentManager() {
        // Prevent object creation
    }

    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }

    public static ExtentReports getInstance(String type) {

        if (extent == null || !type.equalsIgnoreCase(reportType)) {

            reportType = type;

            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

            reportName = "report_" + timestamp + ".html";

            String reportFolder = System.getProperty("user.dir")
                    + File.separator
                    + "reports"
                    + File.separator
                    + type;

            new File(reportFolder).mkdirs();

            String reportPath = reportFolder + File.separator + reportName;

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Functional Testing");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project Name", "Selenium With Jenkins");
            extent.setSystemInfo("Reporter", System.getProperty("user.name"));
        }

        return extent;
    }

    public static String getReportName() {
        return reportName;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}