package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Screenshot {
  private Data dataUtil;

  public Screenshot() {
    this.dataUtil = new Data();
  }

  public String getScreenshot(String testName, WebDriver driver) {
    Driver driverUtil = new Driver(driver);
    TakesScreenshot ts = (TakesScreenshot) driver;
    File sc = ts.getScreenshotAs(OutputType.FILE);
    // Get current browser from driver
    String browser = driverUtil.getBrowserName();
    String filePath = dataUtil.getProjectDirectory() + "src\\test\\java\\screenshots\\" + testName + "\\"
        + browser + "\\screenshot.png";
    try {
      FileUtils.copyFile(sc, new File(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return filePath;

  }

}
