package Tests;

import DriverFactory.DriverFactory;
import Pages.P01LoginPage;
import Pages.P02DashboardPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import Listeners.IInvokedmethod;
import Listeners.ITestmethod;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

@Listeners({ITestmethod.class, IInvokedmethod.class})
public class TC02DashboardPage {
    @BeforeMethod
    public void setup() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Login_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Owner("Ahmed Hassan")
    @Description("verify that when the user click on logo in the header will redirect to home page")
    @Test
    public void CheckLogoRedirection() throws IOException {
        new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
                .password(getJsonData("ValidLoginCustomer","password"))
                .LoginButtonClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
        new P02DashboardPage(getDriver()).ClickOnLogo();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
    }

    @AfterMethod
    public void quit(){
        quitDriver();
    }
}
