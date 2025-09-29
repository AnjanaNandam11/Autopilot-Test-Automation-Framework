package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashMap;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class ScenarioOne {
    WebDriver driver;
    private String email;
    private String userId;
    private String password;
    
    public ScenarioOne(String e, String u, String p) {
    	this.email= e;
    	this.userId=u;
    	this.password=p;
    }
    @Test
    public void launch() throws InterruptedException, AWTException {

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("download.default_directory", "C:/Users/Urmi/Desktop");
        chromePrefs.put("download.prompt_for_download", false);

        options.addArguments("download.default_directory=C:/Users/Urmi/Desktop");
        options.setExperimentalOption("prefs", chromePrefs);



        String screenshotPath = "Screenshots/One/";
        
        driver = new ChromeDriver(options);
        driver.get("https://me.northeastern.edu/");
        driver.manage().window().maximize();

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

        Thread.sleep(1000);

        WebElement duoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trust-browser-button")));
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_4.png");
        duoElement.click();

        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_5.png");
        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idBtn_Back")));
        noButton.click();


        //WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[2]")));
       // ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Strp_6.png");
       // continueBtn.click();

        WebElement resource = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='link-resources']")));
        resource.click();

        WebElement academics = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Academics, Classes & Registration')]")));
        academics.click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_7.png");

        WebElement transcript = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("My Transcript")));
        transcript.click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_8.png");
        
        //switch window
        wait.until(numberOfWindowsToBe(2));
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement userIdInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        userIdInput.sendKeys(userId);
        WebElement passWord2 = wait.until(ExpectedConditions.elementToBeClickable(By.name("j_password")));
        passWord2.sendKeys(password);
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section/div/div[2]/div/form/div[3]/button\n")));
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_9.png");
        submit.click();


        //Duo:
        driver.switchTo().frame("duo_iframe"); // duo iframe
        WebElement pushButton;

        try {
            // get element:
            pushButton = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'auth-button') and contains(@class, 'positive')][@type='submit']"))));
            pushButton.click();
        } catch (StaleElementReferenceException e) {
            // if any exception, re-get the element
            pushButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(@class, 'auth-button') and contains(@class, 'positive')][@type='submit']")));
            pushButton.click();
        }

        driver.switchTo().defaultContent();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_10.png");

        //select
        Select drpCountry = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("levl_id"))));
        drpCountry.selectByVisibleText("Graduate");
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_11.png");
        WebElement findTranscript = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/form/input")));
        findTranscript.click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_12.png");
        // download pdf

		
        Robot robot = new Robot();
         
        // Click on the download link
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Release the left mouse button
         
        // Wait for the download dialog or action to start (you might need to adjust this delay)
        Thread.sleep(3000);
         
        // Simulate Ctrl + S (Save As)
        robot.keyPress(KeyEvent.VK_CONTROL); // Press the Ctrl key
        robot.keyPress(KeyEvent.VK_S); // Press the 'S' key
        robot.keyRelease(KeyEvent.VK_S); // Release the 'S' key
        robot.keyRelease(KeyEvent.VK_CONTROL); // Release the Ctrl key
         
        // Wait for the Save As dialog to appear (you might need to adjust this delay)
        Thread.sleep(3000);
         
        // Press Enter to confirm the Save As action
        robot.keyPress(KeyEvent.VK_ENTER); // Press the Enter key
        robot.keyRelease(KeyEvent.VK_ENTER); // Release the Enter key
         
        // Wait for the download to complete (you might need to adjust this delay)
        Thread.sleep(10000); // Wait for 10 seconds or adjust as needed
         
        // Take a screenshot
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_13.png");
         
        // Quit the WebDriver
        driver.quit(); // Close the WebDriver session


    }
}
