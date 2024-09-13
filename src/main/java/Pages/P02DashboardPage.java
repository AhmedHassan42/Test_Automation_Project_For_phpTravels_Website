package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02DashboardPage {
    private final WebDriver driver;
    private final By iconLogo= By.xpath("//a[contains(@class,'navbar')]");

    public P02DashboardPage(WebDriver driver) {
        this.driver= driver;
    }

    public P03HomePage ClickOnLogo() {
        Utility.clickOnElement(driver, iconLogo);
        return new P03HomePage(driver);
    }

    }





