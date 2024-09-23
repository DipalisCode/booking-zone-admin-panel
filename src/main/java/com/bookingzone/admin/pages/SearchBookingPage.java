package com.bookingzone.admin.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzone.admin.utils.ExtentReportManager;

public class SearchBookingPage 
{
	private WebDriverWait wait;
	
	@FindBy(xpath = "(//div//input[contains(@class, 'MuiOutlinedInput-input MuiInputBase-input Mui')])[2]")
	private WebElement textBookingIdInput;

	@FindBy(xpath="(//button//span[contains(@class,'MuiIconB')])[8]")
	private WebElement BookingArrow;

	public SearchBookingPage (WebDriver driver)
	{
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}


	// Method to enter booking ID into the input field
	public void enterBookingId(String id) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(textBookingIdInput));
		textBookingIdInput.sendKeys(id);
		ExtentReportManager.logInfo("Booking Id Entered...!");
		Thread.sleep(3000);
	}	 

	public  void clickBookingArrow() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(BookingArrow));
		BookingArrow.click();
		ExtentReportManager.logInfo("Clicked On Booking Arrow...!");
		Thread.sleep(5000);
	}


}
