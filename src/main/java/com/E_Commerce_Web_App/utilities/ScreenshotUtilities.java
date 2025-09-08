package com.E_Commerce_Web_App.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtilities {
    private static String projectPath = System.getProperty("user.dir");
    private static String screenshotDir = projectPath + "\\src\\test\\resources\\E_Commerce_Web_App_Screenshots\\";

    public static String capturescreen(WebDriver driver, String testName) throws IOException {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String screenPath = screenshotDir + testName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(screenPath);

        // Ensure directory exists
        dest.getParentFile().mkdirs();
        
        FileUtils.copyFile(src, dest);

        return screenPath;
    }
}
