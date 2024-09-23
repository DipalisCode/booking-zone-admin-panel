package com.bookingzone.admin.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.bookingzone.admin.utils.ExtentReportManager;

public class YopMailPage {

    @FindBy(name = "login")
    private WebElement enterLoginName;

    @FindBy(xpath = "//button[@class='md']")
    private WebElement mailArrowButton;

    @FindBy(xpath = "(//button[@class='lm'])[1]")
    private WebElement clickMailButton;

    @FindBy(xpath = "//a[text()='Link']")
    private WebElement linkInMailButton;

    /**
     * Initializes the web elements using the PageFactory.
     * @param driver The WebDriver instance to use
     */
    public YopMailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the YopMail website for checking emails.
     * @param driver The WebDriver instance to use
     * @throws InterruptedException when interrupted while waiting
     */
    public void navigateToYopMail(WebDriver driver) throws InterruptedException {
        // Open a new tab using JavaScript Executor
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

        // Switch to the newly opened tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Navigate to the desired URL in the new tab
        driver.navigate().to("https://yopmail.com/");

        // Add implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        // Log info to the report
        ExtentReportManager.logInfo("Navigated to yopmail.com in a new tab...!");

        // Sleep to simulate a wait if needed
        Thread.sleep(5000);
    }


    /**
     * Enters the login name in the email login field.
     * @param name The name to enter
     * @throws InterruptedException when interrupted while waiting
     */
    public void enterLoginEmail(String name) throws InterruptedException {
        enterLoginName.sendKeys(name);
        ExtentReportManager.logInfo("Entered Login Email Name...!");
        Thread.sleep(3000);
    }

    /**
     * Clicks the mail arrow button to access the email inbox.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickMailArrow() throws InterruptedException {
        mailArrowButton.click();
        ExtentReportManager.logInfo("Logged in into yop mail account...!");
        Thread.sleep(5000);
    }

    /**
     * Clicks the email in the inbox for viewing the content.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickMail() throws InterruptedException {
        clickMailButton.click();
        Thread.sleep(3000);
    }

    /**
     * Clicks the link inside the email for proceeding with the payment.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickLinkInMail() throws InterruptedException {
        linkInMailButton.click();
        Thread.sleep(3000);
    }
}
