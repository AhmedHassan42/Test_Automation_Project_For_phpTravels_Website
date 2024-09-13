package Tests;

import Pages.P01LoginPage;
import Pages.P02DashboardPage;
import Pages.P03HomePage;
import Utilities.LogsUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.IInvokedmethod;
import Listeners.ITestmethod;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

@Listeners({ITestmethod.class,IInvokedmethod.class})
public class TC03HomePage {
    @BeforeMethod
    public void setup() throws IOException {
        SetupDriver(getPropertyValue("environment", "browser"));
        getDriver().get(getPropertyValue("environment", "Login_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Owner("Ahmed Hassan")
    @Description("verify that when the user enter not available flight data, no results found is returned")
    @Test
    public void CheckNotFoundFlightIsDisplayed() throws IOException {
        new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
                .password(getJsonData("ValidLoginCustomer","password"))
                .LoginButtonClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
        new P02DashboardPage(getDriver()).ClickOnLogo();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        new P03HomePage(getDriver()).DubaiXNBFromcity("Dubai").CairoDestinationcity("cairo")
                .EnterDate("16-10-2024").ClickSearch();
        Assert.assertTrue(new P03HomePage(getDriver()).CheckNoFlightsFoundIsDisplayed());
    }

    @Owner("Ahmed Hassan")
    @Description("verify that when the user enter available flight data,results found is returned")
    @Test
    public void CheckFoundFlightIsDisplayed() throws IOException {
        new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
                .password(getJsonData("ValidLoginCustomer","password"))
                .LoginButtonClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
        new P02DashboardPage(getDriver()).ClickOnLogo();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        new P03HomePage(getDriver()).DubaiDXBFromcity("Dubai").CairoDestinationcity("cairo")
                .EnterDate("16-10-2024").ClickSearch();
        Assert.assertTrue(new P03HomePage(getDriver()).CheckFlightsFoundIsDisplayed());
        LogsUtil.info(String.valueOf(new P03HomePage(getDriver()).CheckFlightsFoundIsDisplayed()));
    }

    @Owner("Ahmed Hassan")
    @Description("verify that when the user enter same city in from and destination fields alert message will displayed")
    @Test
    public void CheckAlertMessage() throws IOException {
        new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
                .password(getJsonData("ValidLoginCustomer","password"))
                .LoginButtonClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
        new P02DashboardPage(getDriver()).ClickOnLogo();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        new P03HomePage(getDriver()).DubaiXNBFromcity("Dubai").DubaiDestinationcity("Dubai")
                .EnterDate("16-10-2024").ClickSearch();
        Assert.assertTrue(new P03HomePage(getDriver()).CheckAlertMessage());
        LogsUtil.info(new P03HomePage(getDriver()).Alertmessage());
    }
    @AfterMethod
    public void quit(){
        quitDriver();
    }
    }
