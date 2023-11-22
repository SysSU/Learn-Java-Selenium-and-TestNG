package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
  private Data dataUtil = new Data();
  private ExtentReports extentReports;

  public ExtentReports getReportObject(String fileName) {
    System.out.println("Setting up extent reports");
    // Extent Reports
    ExtentSparkReporter reporter = new ExtentSparkReporter(
        dataUtil.getProjectDirectory() + "src\\test\\java\\reports\\" + fileName + ".html");
    ExtentReports extentReports = new ExtentReports();
    extentReports.attachReporter(reporter);
    this.extentReports = extentReports;
    return this.extentReports;
  }

}
