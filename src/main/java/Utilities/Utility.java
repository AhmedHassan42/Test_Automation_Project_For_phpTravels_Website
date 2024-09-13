package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utility {
    private static final String path="TestOutputs/Screenshots/";

    public static void clickOnElement(WebDriver driver, By locator){ //elfekra hena enna mnkrrsh el wait fe kol 7aga m7atagha
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void SendData(WebDriver driver,By locator,String data){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static String getText(WebDriver driver,By locator){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }

    public static WebElement BytoWebelement(WebDriver driver, By locator){
        return driver.findElement(locator);
    }

    public static void ScrollToElement(WebDriver driver,By locator){
        ((JavascriptExecutor)driver).executeScript("argument[0].scrollIntoView();",BytoWebelement(driver,locator));
    }

    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void TakingScreenshot(WebDriver driver,String screenshotName) throws IOException {
        try {
            File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File screenDestination= new File(path + screenshotName + "-" + getTimeStamp() +".png");
            FileUtils.copyFile(src, screenDestination);
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenDestination.getPath())));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void TakeFullscreenshot(WebDriver driver, By locator){
        try {Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                .highlight(BytoWebelement(driver,locator)).save(path);
        }catch (Exception e){
            LogsUtil.error(e.getMessage());
        }


    }

    public static void SelectFromDropDown(WebDriver driver, By locator, String index){
        new Select(BytoWebelement(driver,locator)).selectByVisibleText(index);

    }
}
