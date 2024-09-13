package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P03HomePage {
    private final WebDriver driver;
    private final By EnterFromCity=By.cssSelector("input.select2-search__field");
    private final By EnterDestinationCity=By.cssSelector("input.select2-search__field");
    private final By clickEnterFromCity=By.xpath("//span[contains(@class,'select2-container--default') and @data-select2-id=2='combobox']");
    private final By clickEnterDestination=By.xpath("//span[contains(@class,'select2-container--default') and @data-select2-id=5='combobox']");
    private final By DubaiXNBFromButton= By.xpath("//button[contains(@class,'btn-outline-primary') and .='XNB']");
    private final By DubaiDXBFromButton= By.xpath("//button[contains(@class,'btn-outline-primary') and .='DXB']");
    private final By DubaiXNBDestinationButton= By.xpath("//button[contains(@class,'btn-outline-primary') and .='XNB']");
    private final By CairoDestinationButton= By.xpath("//button[contains(@class,'btn-outline-primary') and .='CAI']");
    private final By DateField= By.cssSelector("input#departure");
    private final By SearchButton= By.cssSelector("button#flights-search");
    private final By NoFoundFlightImage= By.xpath("//img[contains(@class,'shadow')]");
    private final By Foundflight = By.cssSelector("h2 span.j_listABTit");




    public P03HomePage(WebDriver driver) {
        this.driver=driver;
    }

    public P03HomePage DubaiXNBFromcity(String EnterCity){
        Utility.clickOnElement(driver,clickEnterFromCity);
        Utility.SendData(driver,EnterFromCity,EnterCity);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(DubaiXNBFromButton));
        Utility.clickOnElement(driver,DubaiXNBFromButton);
        return this;
    }
    public P03HomePage DubaiDXBFromcity(String EnterCity){
        Utility.clickOnElement(driver,clickEnterFromCity);
        Utility.SendData(driver,EnterFromCity,EnterCity);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(DubaiDXBFromButton));
        Utility.clickOnElement(driver,DubaiDXBFromButton);
        return this;
    }
    public P03HomePage DubaiDestinationcity(String EnterCity){
        Utility.clickOnElement(driver,clickEnterDestination);
        Utility.SendData(driver,EnterFromCity,EnterCity);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(DubaiXNBDestinationButton));
        Utility.clickOnElement(driver,DubaiXNBDestinationButton);
        return this;
    }
    public P03HomePage CairoDestinationcity(String EnterCity){
        Utility.clickOnElement(driver,clickEnterDestination);
        Utility.SendData(driver,EnterDestinationCity,EnterCity);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(CairoDestinationButton));
        Utility.clickOnElement(driver,CairoDestinationButton);
        return this;
    }
    public P03HomePage EnterDate(String DateText){
        driver.findElement(DateField).clear();
        Utility.SendData(driver,DateField, DateText);
        return this;
    }
    public P04FlightsPage ClickSearch(){
        Utility.clickOnElement(driver,SearchButton);
        return new P04FlightsPage(driver);
    }
    public boolean CheckNoFlightsFoundIsDisplayed(){
        try {
            new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(NoFoundFlightImage));
            return driver.findElement(NoFoundFlightImage).isDisplayed();
        }catch (Exception e){
            LogsUtil.error(e.getMessage());
        }
        return false;
    }
    public boolean CheckFlightsFoundIsDisplayed(){
        return driver.findElement(Foundflight).isDisplayed();
    }
    public String Alertmessage(){
        return driver.switchTo().alert().getText();
    }
    public boolean CheckAlertMessage(){
        if(Alertmessage().equalsIgnoreCase("Flying City and Destination City Can`t be same")){
            return true;
        }else return false;
    }


}
