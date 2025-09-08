package com.E_Commerce_Web_App.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static String projectPath = System.getProperty("user.dir");

    public static ExtentReports getInstance(String reportName) {
        if (extent == null) {
            String reportPath = projectPath + "\\src\\test\\resources\\E_Commerce_Web_App_Reports\\" + reportName;

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
