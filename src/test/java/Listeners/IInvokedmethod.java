package Listeners;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedmethod implements IInvokedMethodListener {
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus()==ITestResult.FAILURE){
            try {
                LogsUtil.info("Test case failed");
                Utility.TakingScreenshot(getDriver(),testResult.getName());
                //Utility.TakeFullscreenshot(getDriver(),new HomePage(getDriver()).getIconCard());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
