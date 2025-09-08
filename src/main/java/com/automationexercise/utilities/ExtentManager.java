package com.automationexercise.utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setDocumentTitle("based on imapct test case  Report");
        htmlReporter.config().setReportName("Based on imapct ");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

	public static ExtentReports getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}

