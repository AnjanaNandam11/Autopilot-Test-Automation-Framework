package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class ScenarioFour {
    WebDriver driver;
    @Test
    public void launch() throws InterruptedException {
        String screenshotPath = "Screenshots/Four/";

      try{
          driver = new ChromeDriver();
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));


          driver.get("https://onesearch.library.northeastern.edu/discovery/search?vid=01NEU_INST:NU&lang=en");
          driver.manage().window().maximize();
          ScreenshotUtil.takeScreenshot(driver,screenshotPath+"Step_1.png");
          //click to digital
          WebElement digital = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mainMenu\"]/div[5]/a/span")));
          digital.click();
          //switch window
          wait.until(numberOfWindowsToBe(2));

          ScreenshotUtil.takeScreenshot(driver,screenshotPath+"Step_2.png");
          String originalWindow = driver.getWindowHandle();
          for (String windowHandle : driver.getWindowHandles()) {
              if (!originalWindow.contentEquals(windowHandle)) {
                  driver.switchTo().window(windowHandle);
                  break;
              }
          }

          ScreenshotUtil.takeScreenshot(driver,screenshotPath+"Step_3.png");
          WebElement dataset = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/div[1]/section/div[1]/a[5]")));
          dataset.click();
          ScreenshotUtil.takeScreenshot(driver,screenshotPath+"Step_4.png");
          WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(
                  By.xpath("//input[@id='searchFieldHeader' and @placeholder='Search this featured content']")));
          searchField.sendKeys("csv");
          ScreenshotUtil.takeScreenshot(driver,screenshotPath+"Step_5.png");
          WebElement zipfile= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/div[2]/main/section/ul/article[1]/div/div/div/div/div[1]/a[1]")));
          zipfile.click();
          ScreenshotUtil.takeScreenshot(driver,screenshotPath+"Step_6.png");
          Thread.sleep(2000);
          ScreenshotUtil.takeScreenshot(driver,screenshotPath+"Step_7.png");

      }finally {
          driver.quit();
      }
    }
}
