package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P04FlightsPage {
    public static List<WebElement> allFromandDestinationText;
    private final WebDriver driver;
    private final By FromAndDestination= By.xpath("//div[contains(@class,'col-md-6')][2]//p[@class='mb-0'][2]");
    private final By LowestToHigherButton= By.xpath("//button[@data-value='asc']");
    private final By ReturnDateField= By.cssSelector("input#return_date");
    private final By RoundTripButton= By.cssSelector("input#round-trip");
    private final By SearchButton= By.cssSelector("button#flights-search");
    private final By FlightTypeDropDown= By.cssSelector("select#flight_type");
    private final By FlightTypeResults= By.xpath("(//span[contains(@class,'text-capitalize')][3]/strong[@class='text-dark'])[1]");
    private final By MoreDetailsButton= By.xpath("(//div[contains(@class,'order-1')]//button[contains(@class,'btn-outline-primary') and @type='button'])[1]");


    public P04FlightsPage(WebDriver driver) {
        this.driver=driver;
    }
    public String GetTextFromAndDestinationFlights(){
        try {
            allFromandDestinationText = driver.findElements(FromAndDestination);
            for(WebElement elements: allFromandDestinationText) {
                return elements.getText();
            }
        }catch (Exception e){
            LogsUtil.error(e.getMessage());
        }
        return "No Trips found";
    }
    public boolean ChecktextFromAndDestinationFlights(String text){
        return GetTextFromAndDestinationFlights().equalsIgnoreCase(text);
    }
    public P04FlightsPage ClickroundTripButton(){
        Utility.clickOnElement(driver,RoundTripButton);
        return this;
    }

    public P04FlightsPage EnterReturnDate(String ReturnDateText){
        driver.findElement(ReturnDateField).clear();
        Utility.SendData(driver,ReturnDateField,ReturnDateText);
        return this;
    }
    public P04FlightsPage ClickSearch(){
        Utility.clickOnElement(driver,SearchButton);
        return new P04FlightsPage(driver);
    }
    public P04FlightsPage SelectFlightType(String flighttype){
        new Select(Utility.BytoWebelement(driver,FlightTypeDropDown)).selectByVisibleText(flighttype);
        return this;
    }
    public P04FlightsPage ClickOnMoreDetailsButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(MoreDetailsButton));
        Utility.clickOnElement(driver,MoreDetailsButton);
        return this;
    }
    public boolean CheckFlightTypeResult(String flightType){
        String FlightText= Utility.getText(driver,FlightTypeResults);
        LogsUtil.info("Type of flight is: "+ FlightText);
        if(FlightText.equalsIgnoreCase(flightType)){
            return true;
        }else return false;
    }
}
