package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class ScenarioFive {
    WebDriver driver;
    private String email;
    private String password;
    public ScenarioFive(String e, String p) {
    	this.email=e;
    	this.password=p;
    }
    @Test
    public void launch() throws IOException, InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://student.me.northeastern.edu/resources");
        driver.manage().window().maximize();

        String screenshotPath = "Screenshots/Five/";



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));

        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_1.png");
        //Enter userName:
        WebElement userNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
        userNameInput.sendKeys(email);
        Thread.sleep(1000);
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_2.png");
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(1000);
        //Enter Password:
        WebElement passwordInput = driver.findElement(By.id("i0118"));
        passwordInput.sendKeys(password);
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_3.png");
        //log in:
        WebElement SignIn = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        SignIn.click();


        //Duo:
        WebElement duoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trust-browser-button")));
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_4.png");
        duoElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idBtn_Back"))).click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_5.png");
        // Academic:
        WebElement academics = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Academics, Classes & Registration')]")));
        academics.click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_6.png");
      
        WebElement academicCalendar = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Academic Calendar")));
        academicCalendar.click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_7.png");
        //switch window
        wait.until(numberOfWindowsToBe(2));
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_8.png");
        //Calender
        WebElement newTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tax-academic-calendars\"]/div/a[1]/div")));
        newTab.click();


        //Change Iframe:
        Thread.sleep(2000);
        WebElement iframe = driver.findElement(By.id("trumba.spud.6.iframe"));
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_9.png");
        new Actions(driver)
                .scrollToElement(iframe)
                .perform();
        Thread.sleep(2000);

        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_10.png");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("trumba.spud.6.iframe")));
        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"mixItem0\"]"));
        if (checkBox.isSelected()) {
            checkBox.click();
        }
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_11.png");
        Thread.sleep(2000);

        driver.switchTo().defaultContent();
        WebElement iframe2 = driver.findElement(By.id("trumba.spud.2.iframe"));
        WebElement bottom = driver.findElement(By.xpath("//*[@id=\"nu-global-footer\"]/footer/div[1]/div[2]/a[3]"));

        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_12.png");
        new Actions(driver)
                .scrollToElement(bottom)
                .perform();
        driver.switchTo().frame(iframe2);
        Thread.sleep(2000);
        WebElement table = driver.findElement(By.xpath("//*[@id=\"ctl04_ctl90_ctl00_actionsWrap\"]/table"));
        WebElement addToMy = table.findElement(By.id("ctl04_ctl90_ctl00_buttonAtmc"));
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_13.png");
        boolean checkpoint = addToMy.isDisplayed();
        Assert.assertTrue(checkpoint);
        Thread.sleep(2000);
     // Quit the WebDriver
        driver.quit(); // Close the WebDriver session
    }
}
