package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
  private WebDriver driver;

  public Driver(WebDriver driver) {
    this.driver = driver;
  }

  public String getBrowserName() {
    Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
    return cap.getBrowserName().toLowerCase();
  }
}
