package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public abstract class TestBaseRapor {
    protected static ExtentReports extentReports; //first assign to extent report
    protected static ExtentTest extentTest;
    protected static ExtentHtmlReporter extentHtmlReporter;

    @BeforeTest(alwaysRun = true) // alwaysRun : her zaman çalıştır.
    public void setUpTest() {
        extentReports = new ExtentReports();
        String filePath = System.getProperty("user.dir") + "/test-output/benimraporum.html";
        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("Enviroment", "QA");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Automation Engineer", "Mehmet");
        extentHtmlReporter.config().setDocumentTitle("Remwaste case");
        extentHtmlReporter.config().setReportName("Remwaste case Automation Reports");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotLocation = ReusableMethods.getScreenshot(result.getName());
            extentTest.fail(result.getName());
            extentTest.addScreenCaptureFromPath(screenshotLocation);
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test Case is skipped: " + result.getName());
        }
        Driver.closeDriver();
    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        extentReports.flush();
    }
}
