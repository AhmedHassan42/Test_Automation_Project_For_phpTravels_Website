package Listeners;

import Utilities.LogsUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestmethod implements ITestListener {
    public void onTestStart(ITestResult result) {
        LogsUtil.info("Test case "+ result.getName() +" started");
    }

    public void onTestSuccess(ITestResult result) {
        LogsUtil.info(result.getName() + " Test pass");
    }

    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test case"+ result.getName() +" skipped");

    }


}
