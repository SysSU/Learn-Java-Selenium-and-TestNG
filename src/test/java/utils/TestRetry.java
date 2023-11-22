package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// Util class for handling test retries
public class TestRetry implements IRetryAnalyzer {

  int count = 0;
  int maxCount = 1;

  @Override
  public boolean retry(ITestResult result) {
    
    if (count < maxCount) {
      count++;
      result.setStatus(ITestResult.FAILURE);
      return true;
    }

    return false;
  }

}
