package com.automation.pages;

import com.automation.basedriver.PageDriver;
import com.automation.utilities.Screenshots;
import com.automation.utilities.commonMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import javax.xml.xpath.XPath;
import java.io.IOException;

public class Login_page extends commonMethods {
    ExtentTest test;

    public Login_page( ExtentTest test){
        this.test=test;
    }
    //locator
    @FindBys({
           @FindBy(xpath="//input[@name='username']"),
            @FindBy(xpath="//input[@placeholder='Username']")
    })
    WebElement username;

    @FindBys({
            @FindBy(xpath="//input[@name='password']"),
            @FindBy(xpath="//input[@placeholder='Password']")
    })
    WebElement password;

    @FindBys({
            @FindBy(xpath="//button[@type='submit']")

    })
    WebElement Login_button;

    //screeshot+report
    public void passcase(String Message){
        test.pass("<b><p style='color:green;font-size:14px'>"+Message+"</P></b>");
    }
    public void passcasewithSC(String message,String screenshotname) throws IOException {
        test.pass("<b><p style='color:green;font-size:14px'>"+message+"</P></b>");
        String screeshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+screenshotname+"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }
    public void failcase(String message,String screenshotname) throws IOException {
        test.fail("<b><p style='color:green;font-size:14px'>"+message+"</P></b>");
        String screeshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+screenshotname+"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        Throwable T =new  InterruptedException("Exception");
        test.fail(T);
    }
    public void login() throws IOException {
        try {
            test.info("Please inter your username");
            if (username.isDisplayed()) {
                username.sendKeys("Admin");
                passcase("You have successfully entered your username");
                Thread.sleep(5000);

                try {
                    if (password.isDisplayed()) {
                        password.sendKeys("admin123");
                        passcase("You have successfully entered your password");
                        Thread.sleep(5000);

                        try {
                            if (Login_button.isDisplayed()){
                                Login_button.click();
                                Thread.sleep(5000);
                                passcasewithSC("You have successfully loged in","login_successfull");
                            }

                        } catch (Exception e) {
                            failcase("Login_button was not locatable.Please check the  log", "LoginButton_fail");
                        }
                    }

                } catch (Exception e) {
                    failcase("Password was not locatable.Please check the  log", "password_fail");
                }

            }

        } catch (Exception e) {
            failcase("user name was not locatable.Please check the  log","user_name_fail");
        }
    }
}
