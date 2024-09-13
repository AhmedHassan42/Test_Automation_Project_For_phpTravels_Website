package Tests;

import Pages.P01LoginPage;
import Pages.P02DashboardPage;
import Pages.P03HomePage;
import Pages.P04FlightsPage;
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
public class TC04FlightsPage {

   @BeforeMethod
   public void setup() throws IOException {
       SetupDriver(getPropertyValue("environment", "browser"));
       getDriver().get(getPropertyValue("environment", "Login_URL"));
       getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
   }

   @Owner("Ahmed Hassan")
   @Description("verify that when the user enter from and destination cities the search results will be correct for all flights")
   @Test
   public void CheckFlightsResult() throws IOException {
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
       LogsUtil.info(new P04FlightsPage(getDriver()).GetTextFromAndDestinationFlights());
       Assert.assertTrue(new P04FlightsPage(getDriver()).ChecktextFromAndDestinationFlights("Dubai - Cairo"));
   }
   @Owner("Ahmed Hassan")
   @Description("verify that when the user enter return date in the future the error message will appear")
   @Test
   public void CheckSearchForReturnDateTripInFuture() throws IOException {
       new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
               .password(getJsonData("ValidLoginCustomer","password"))
               .LoginButtonClick();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
       new P02DashboardPage(getDriver()).ClickOnLogo();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
       new P03HomePage(getDriver()).DubaiDXBFromcity("Dubai").CairoDestinationcity("cairo")
               .EnterDate("16-10-2024");
       new P04FlightsPage(getDriver()).ClickroundTripButton().EnterReturnDate("18-09-2024")
               .ClickSearch();
       Assert.assertTrue(new P03HomePage(getDriver()).CheckNoFlightsFoundIsDisplayed());
   }
   @Owner("Ahmed Hassan")
   @Description("verify that when the user  select economy flight type on search will results correctly")
   @Test
   public void CheckSearchForEconomyTypeResult() throws IOException {
       new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
               .password(getJsonData("ValidLoginCustomer","password"))
               .LoginButtonClick();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
       new P02DashboardPage(getDriver()).ClickOnLogo();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
       new P03HomePage(getDriver()).DubaiDXBFromcity("Dubai").CairoDestinationcity("cairo")
               .EnterDate("16-10-2024");
       new P04FlightsPage(getDriver()).SelectFlightType("Economy").ClickSearch().ClickOnMoreDetailsButton();
       Assert.assertTrue(new P04FlightsPage(getDriver()).CheckFlightTypeResult("economy"));

   }
   @Owner("Ahmed Hassan")
   @Description("verify that when the user  select economy premium flight type on search will results correctly")
   @Test
   public void CheckSearchForEconomyPremiumTypeResult() throws IOException {
       new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
               .password(getJsonData("ValidLoginCustomer","password"))
               .LoginButtonClick();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
       new P02DashboardPage(getDriver()).ClickOnLogo();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
       new P03HomePage(getDriver()).DubaiDXBFromcity("Dubai").CairoDestinationcity("cairo")
               .EnterDate("16-10-2024");
       new P04FlightsPage(getDriver()).SelectFlightType("\n" +
               "Economy Premium").ClickSearch().ClickOnMoreDetailsButton();
       Assert.assertTrue(new P04FlightsPage(getDriver()).CheckFlightTypeResult("economy_premium"));

   }
   @Owner("Ahmed Hassan")
   @Description("verify that when the user  select business class flight type on search will results correctly")
   @Test
   public void CheckSearchForBusinessTypeResult() throws IOException {
       new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
               .password(getJsonData("ValidLoginCustomer","password"))
               .LoginButtonClick();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
       new P02DashboardPage(getDriver()).ClickOnLogo();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
       new P03HomePage(getDriver()).DubaiDXBFromcity("Dubai").CairoDestinationcity("cairo")
               .EnterDate("16-10-2024");
       new P04FlightsPage(getDriver()).SelectFlightType("Business").ClickSearch().ClickOnMoreDetailsButton();
       Assert.assertTrue(new P04FlightsPage(getDriver()).CheckFlightTypeResult("business"));

   }
   @Owner("Ahmed Hassan")
   @Description("verify that when the user  select first class flight type on search will results correctly")
   @Test
   public void CheckSearchForFirstClassTypeResult() throws IOException {
       new P01LoginPage(getDriver()).email(getJsonData("ValidLoginCustomer","email"))
               .password(getJsonData("ValidLoginCustomer","password"))
               .LoginButtonClick();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Dashboard_URL"));
       new P02DashboardPage(getDriver()).ClickOnLogo();
       Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
       new P03HomePage(getDriver()).DubaiDXBFromcity("Dubai").CairoDestinationcity("cairo")
               .EnterDate("16-10-2024");
       new P04FlightsPage(getDriver()).SelectFlightType("First").ClickSearch().ClickOnMoreDetailsButton();
       Assert.assertTrue(new P04FlightsPage(getDriver()).CheckFlightTypeResult("first"));

   }


   @AfterMethod
   public void quit(){
       //quitDriver();
   }
}
