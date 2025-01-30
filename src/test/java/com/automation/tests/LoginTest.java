package com.automation.tests;

import com.automation.basedriver.PageDriver;
import com.automation.pages.Login_page;
import com.automation.utilities.ExtentFactory;
import com.automation.utilities.commonMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends commonMethods {
    ExtentReports report;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void openURL() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        sleep();
        report= ExtentFactory.getInstance();
        parentTest=report.createTest("<p style=\"color:green; font-size:14px\"><b>Orange HRM</b></P>").assignAuthor("Tester koli").assignDevice("windows");
    }
    @Test
    public void orangeHRM_login() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:green; font-size:14px\"><b>Login</b></P>");
        Login_page loginpage = new Login_page(childTest);
        loginpage.login();
    }
    @AfterClass
    public void report(){
        report.flush();
    }
}
