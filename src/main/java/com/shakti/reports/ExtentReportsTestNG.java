package com.shakti.reports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsTestNG {

	public static ExtentReports config() {

		File file = new File(System.getProperty("user.dir") + "/report/index.html");
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(file);
		extentSparkReporter.config().setDocumentTitle("Test Results");
		extentSparkReporter.config().setReportName("Retail Test Result");

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Devloper", "Shakti");

		return extentReports;

	}
}
