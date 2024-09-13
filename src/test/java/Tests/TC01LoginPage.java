package Tests;

import Pages.P01LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.IInvokedmethod;
import Listeners.ITestmethod;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

@Listeners({ITestmethod.class, IInvokedmethod.class})
public class TC01LoginPage {
    @BeforeMethod
    public void setup() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Login_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Owner("Ahmed Hassan")
    @Description("verify that when the user enter valid credential will login successfully")
    @Test
    public void ValidLoginTC() throws IOException {
        new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
                .password(getJsonData("ValidLoginCustomer","password"))
                .LoginButtonClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
    }
    @AfterMethod
    public void quit(){
        quitDriver();
    }
}
