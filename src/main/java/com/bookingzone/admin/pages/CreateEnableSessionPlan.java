package com.bookingzone.admin.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzone.admin.utils.ExtentReportManager;

public class CreateEnableSessionPlan {
	
	private WebDriverWait wait;

    // WebElements declaration using @FindBy annotation with proper naming conventions
	
	@FindBy(xpath="//input[@name='sessionStatus']")
    private WebElement enableSessionCheckBox;
	
    @FindBy(xpath = "//input[@name='schedules.0.perPerson.visible']")
    private WebElement pricePerPerson;
    
    @FindBy(xpath = "//input[@name='schedules.0.reservationSlots.0.pricePerPerson.0.timeFrame.0.price']")
    private WebElement priceInputFeild;

    public CreateEnableSessionPlan(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
    }

    // Method to click the enable package checkbox
    public void clickEnableSessionCheckBox() {
    	enableSessionCheckBox.click();
    	ExtentReportManager.logInfo("Enable Session Check Box Selected...!");
    }
   
    // Method to click the price per person checkbox 
    public void clickPricePerPersonCheckBox() {
    	pricePerPerson.click();
    	ExtentReportManager.logInfo("Price Per Person Check Box Selected...!");
    }

    
    public void enterPerPersonPrice(String price) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(priceInputFeild));
		priceInputFeild.click();
		priceInputFeild.sendKeys(price);
		ExtentReportManager.logInfo("Per Person Price Entered...!");
	}
    
   
}
