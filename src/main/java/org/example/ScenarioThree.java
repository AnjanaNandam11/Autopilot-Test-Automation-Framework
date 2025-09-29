package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScenarioThree {

    private static WebDriver driver;

    @Test
    public void launch() {
        // Configure Browser for download file:
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "C:/Users/Urmi/Desktop");

        driver = new ChromeDriver(options);
        String screenshotPath = "Screenshots/Three/";
        try {

            driver.get("https://service.northeastern.edu/tech?id=classrooms");
            driver.manage().window().maximize();

            String originalWindowHandle = driver.getWindowHandle();

            Thread.sleep(5000);
            ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_1.png");
            
            WebElement classroomLink = driver.findElement(By.xpath("//a[contains(@href, 'classroom_details') and contains(text(), '310 Behrakis Health Sciences Center')]"));
            classroomLink.click();
            
            Thread.sleep(5000);
            ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_2.png");

            WebElement nuflexLink = driver.findElement(By.xpath("//a[text()='NUflex Standard Push Button Classroom ']"));
            nuflexLink.click();

            Thread.sleep(3000);
            ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_3.png");
            Set<String> windowHandles = driver.getWindowHandles();

            for (String windowHandle : windowHandles) {
                if (!windowHandle.contains(originalWindowHandle)) {

                    driver.switchTo().window(windowHandle);
                    if (driver.getCurrentUrl().equals(
                            "nuflex.northeastern.edu/wp-content/uploads/2021/09/PUSH-BUTTON-CLASSROOM-QUICK-GUIDE.pdf")) {
                        break;
                    }
                }
            }

         // Your existing code
          
         // Assuming you're targeting Windows and attempting to simulate Ctrl + S (Save As)

            Thread.sleep(3000);
            ScreenshotUtil.takeScreenshot(driver, screenshotPath + "Step_4.png");
            Robot robot = new Robot();
            robot.delay(600);
            
	        robot.keyPress(KeyEvent.VK_CONTROL); // Press Ctrl key
	        robot.delay(100); // Adjust delay if necessary
	        robot.keyPress(KeyEvent.VK_S); // Press 'S' key
	        robot.delay(100); // Adjust delay if necessary
            robot.keyRelease(KeyEvent.VK_S); // Release 'S' key
            robot.delay(100); // Adjust delay if necessary
            robot.keyRelease(KeyEvent.VK_CONTROL); // Release Ctrl key          
            Thread.sleep(3000);          
            robot.keyPress(KeyEvent.VK_ENTER); // Press Enter key
            robot.keyRelease(KeyEvent.VK_ENTER); // Release Enter key
            Thread.sleep(2000);       
            driver.quit();
            
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
