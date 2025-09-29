package org.example;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver, String filePath) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);

        try (OutputStream out = new FileOutputStream(destFile)) {
            Files.copy(Paths.get(srcFile.getPath()), out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
