package com.bookingzone.admin.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bookingzone.admin.utils.BookingIdStore;
import com.bookingzone.admin.utils.ExtentReportManager;
import com.bookingzone.admin.utils.UtilityClass;

public class StepperSummaryPage {

	 private WebDriver driver;
	 private WebDriverWait wait;
   
    @FindBy(xpath = "//button//span[text()='Submit']")
    private WebElement submitButton;


    @FindBy(name = "id")
    private WebElement bookingIdInput;

    @FindBy(xpath = "//span[normalize-space()='Summary']")
    private WebElement scroll;
    
    @FindBy(xpath = "//select[@name='status']")
    private WebElement statusDropdown;
    
    @FindBy(xpath = "//button//span[text()='Yes']")
    private WebElement statusConfirmationButton;
    

    // WebElement for getting the  date of the booking
    @FindBy(xpath = "(//div//p[contains(@class,'MuiTypography-root MuiTypography-body2 css-1im')])[2]")
    private WebElement bookingDate;
    
    @FindBy(xpath = "//div//span[@class='MuiChip-label MuiChip-labelMedium css-6od3lo-MuiChip-label']")
    private WebElement getSelectedLane;


    public StepperSummaryPage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to click on the submit button
    public void clickSubmitButton() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        ExtentReportManager.logInfo("Clicked On Submit Button...!");
        Thread.sleep(2000); // Introducing a wait for 2 seconds
    }

    // Method to get the booking ID
    public String getBookingId() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String id = (String) js.executeScript("return arguments[0].value", bookingIdInput);
        ExtentReportManager.logInfo("Booking Id Is- " + id);
        return id;
    }

    /**
     * Method to verify the booking status.
     */
    public void verifyBookingStatus(String status) {
    	wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        Select select = new Select(statusDropdown);
        WebElement selectedOption = select.getFirstSelectedOption();
        String bookingStatus = selectedOption.getText();
        System.out.println("Booking Status is: " + bookingStatus);
        ExtentReportManager.logInfo("Expected Status Is- " + status);
        ExtentReportManager.logInfo("Actual Status Is- " + bookingStatus);
        
      //Assertion applied to verify status of the booking after payment
        Assert.assertEquals(bookingStatus, status, "Status of the booking is");  
        ExtentReportManager.logPass("Verify Booking Status Assertion Is Passed...!");
    }
    
    /**
     * Method to get the edited date.
     * @return The edited date as a string.
     */
    public String getBookingDate() {
        String editedDateText = bookingDate.getText();
        System.out.println("Booking date after edit is: " + editedDateText);
        return editedDateText;
    }
    

    public String getSelectedLane() {
        String selectedLane = getSelectedLane.getText();
        System.out.println("Selected lane is-" + selectedLane);
        return selectedLane;
    }
    
    public void changeStatus(String status) throws InterruptedException 
    {
    	wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
    	Select s1=new Select(statusDropdown);
    	s1.selectByVisibleText(status);
    	ExtentReportManager.logInfo("Booking status updated to 'In Progress' for ID: " + BookingIdStore.getBookingId());
    	Thread.sleep(2000);
    }
    
    public void clickstatusConfirmationButton() throws InterruptedException 
    {
    	statusConfirmationButton.click();
     	ExtentReportManager.logInfo("Clicked on Yes Button To Change The Status...!");
     	Thread.sleep(2000);
    }
    
    
}
