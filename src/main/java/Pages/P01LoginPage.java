package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01LoginPage {
    private final WebDriver driver;
    private final By email= By.cssSelector("#email.form-control");
    private final By password= By.cssSelector("#password.form-control");
    private final By LoginButton= By.id("submitBTN");

    public P01LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01LoginPage email(String emailtext)
    {
        Utility.SendData(driver,email,emailtext);
        return this;
    }
    public P01LoginPage password(String passwordtext)
    {
        Utility.SendData(driver,password,passwordtext);
        return this;
    }
    public P02DashboardPage LoginButtonClick(){
        Utility.clickOnElement(driver,LoginButton);
        return new P02DashboardPage(driver);

    }
}
