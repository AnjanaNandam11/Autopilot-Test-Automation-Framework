package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class ScenarioTwo {
    WebDriver driver;
    private String email;
    private String password;
    String userId;
    public ScenarioTwo(String e, String u, String p) {
    	this.email=e;
    	this.userId = u;
    	this.password=p;
    }
    public static void addEvent(WebDriver driver, String title, String date, String details, String time
    ) {

        WebElement titleInput = driver.findElement(By.id("planner_note_title"));
        WebElement dateInput = driver.findElement(By.id("planner_note_date"));
        WebElement timeInput = driver.findElement(By.id("planner_note_time"));

        WebElement detailsInput = driver.findElement(By.id("details_textarea"));

        titleInput.sendKeys(title);
        dateInput.click();
        dateInput.sendKeys(date);

        detailsInput.sendKeys(details);
        timeInput.sendKeys(time);
    }

    @Test
    public void launch() throws IOException, InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://northeastern.instructure.com");
        driver.manage().window().maximize();


        String screenshotPath = "Screenshots/Two/";
        // Get env variables for security:
       

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));

        WebElement userNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
        userNameInput.sendKeys(email);

        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_1.png");
        //click
        driver.findElement(By.id("idSIButton9")).click();


        //wait and enter password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
        passwordInput.sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(By.id("idSIButton9")).click();


        WebElement duoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trust-browser-button")));
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_2.png");
        duoElement.click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_3.png");
//        stay signed in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9"))).click();
        ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_4.png");

        // Calender actions
        WebElement calendar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='global_nav_calendar_link']")));
        calendar.click();

        //read csv
        String eventsCSVPath = "src/main/resources/events.csv";
        String delimiter = ",";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(eventsCSVPath))) {
            int step = 0;
            while ((line = br.readLine()) != null) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_new_event_link"))).click();
                WebElement todoTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"edit_event_tabs\"]/ul/li[2]")));
                todoTab.click();
                String[] row = line.split(delimiter);
                String title = row[0];
                String time = row[1];
                String date = row[2];
                String details = row[3];
                addEvent(driver, title, date, details, time);
                ScreenshotUtil.takeScreenshot(driver, screenshotPath + step + "Step_5.png");
                step++;

                //submit
                WebElement submit = driver.findElement(By.xpath("//*[@id=\"edit_planner_note_form_holder\"]/form/div[2]/button"));
                submit.click();
                ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_6.png");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();

        }
    }
}
