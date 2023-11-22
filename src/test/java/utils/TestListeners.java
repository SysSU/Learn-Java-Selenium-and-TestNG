package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

// Util class for handling test listeners
public class TestListeners implements ITestListener {
  private ExtentReports extentReports;
  private Screenshot screenshot;
  // Thread safe objects for parallel testing
  // Otherwise tests will overwrite each other when running in parallel
  private ThreadLocal<ExtentTest> tlTest = new ThreadLocal<ExtentTest>();

  public TestListeners() {
    super();
    // Setup screenshot utility
    this.screenshot = new Screenshot();
  }

  public void onTestStart(ITestResult result) {
    // Create test in extentReport
    ExtentTest test = extentReports.createTest(result.getName());
    // Get browser name from driver and set to test device
    WebDriver driver = null;
    try {
      driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

    } catch (Exception e) {
      e.printStackTrace();
    }
    if (driver != null) {
      Driver driverUtil = new Driver(driver);
      String browser = driverUtil.getBrowserName();
      test.assignDevice(browser);
    }

    // Add test to thread safe object
    tlTest.set(test);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    // Log success to extentReport
    tlTest.get().log(Status.PASS, "Test passed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    // Get Test Driver to take a screenshot.
    // Also the driver initialized in the @BeforeTest method would not work because
    // it is not the same driver.
    WebDriver driver = null;

    try {
      driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Log failure to extentReport
    tlTest.get().log(Status.FAIL, result.getThrowable());
    // Take screenshot and add it to extentReport failed test
    tlTest.get().addScreenCaptureFromPath(screenshot.getScreenshot(result.getName(), driver));
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    // Handle retry logic
    Throwable throwable = result.getThrowable();
    if (throwable != null) {
      // Get Test Driver to take a screenshot.
      // Also the driver initialized in the @BeforeTest method would not work because
      // it is not the same driver.
      WebDriver driver = null;

      try {
        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Log failure to extentReport
      tlTest.get().log(Status.FAIL, result.getThrowable());
      // Take screenshot and add it to extentReport failed test
      tlTest.get().addScreenCaptureFromPath(screenshot.getScreenshot(result.getName(), driver));
    } else if (result.getStatus() == ITestResult.SKIP) {
      // Log skip to extentReport
      tlTest.get().log(Status.SKIP, "Test skipped");
  }
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  @Override
  public void onStart(ITestContext context) {
    // Get suite name
    String suiteName = context.getCurrentXmlTest().getSuite().getName();
    // Setup extentReports for HTML report
    ExtentReporter extentReporter = new ExtentReporter();
    // Set extent report object and set suite name to report name
    extentReports = extentReporter.getReportObject(suiteName);

  }

  @Override
  public void onFinish(ITestContext context) {
    // Save extentReport
    extentReports.flush();
  }

}
