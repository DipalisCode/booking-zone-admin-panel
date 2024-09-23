package com.bookingzone.admin.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzone.admin.utils.ExtentReportManager;

public class CreateBookingStepperDetailsPage {

	private WebDriverWait wait;
	private WebDriver driver;

	@FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeMedium css-fth4mk-MuiButtonBase-root-MuiIconButton-root')])[2]")
	private WebElement calendarSymbol;

	@FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-root MuiIco')])[15]")
	private WebElement nextArrow;

	@FindBy(xpath = "//button[text()='22']")
	private WebElement selectDateButton;


	@FindBy(xpath = "//div[contains(@class,'MuiGrid-root css-2w1nhr-MuiGrid-root')]//button[1]")
	private WebElement selectReservationSlot;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root M')])[14]")
	private WebElement selectPackageButton;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root M')])[18]")
	private WebElement selectAddGuestButton;


	// lane-per person

	@FindBy(xpath = "//img[@alt='Testing_perPerson']")
	private WebElement toLanePlan;


	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-c')])[7]")
	private WebElement selectPerson;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-c')])[9]")
	private WebElement selectItem;


	@FindBy(xpath = "//button//span[contains(text(),'Proceed to Pay')]")
	private WebElement proceedToPayButton;

	@FindBy(xpath = "(//input[@id='combo-box-demo'])[1]")
	private WebElement changeTime;

	@FindBy(xpath = "(//input[@id='combo-box-demo'])[2]")
	private WebElement enterMinutes;

	@FindBy(xpath = "(//input[@id='combo-box-demo'])[3]")
	private WebElement selectAMStartTime;

	@FindBy(xpath = "//img[@alt='per_Hour']")
	private WebElement selectPerHourPlan;

	@FindBy(xpath = "//button//span[text()='1 Hours']")
	private WebElement selectHour;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-c')])[8]")
	private WebElement selectGuest;

	//per event

	@FindBy(xpath = "//img[contains(@alt,'Party Event')]")
	private WebElement selectPlan;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-outlined ')])[1]")
	private WebElement event1;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root M')])[18]")
	private WebElement selectChild;


	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-outlined ')])[2]")
	private WebElement event3;


	public CreateBookingStepperDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	// Method to click on the calendar symbol button after ensuring the page is fully loaded
	public void clickCalendarSymbol() throws InterruptedException {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

		// Wait until the calendar symbol is clickable
		wait.until(ExpectedConditions.elementToBeClickable(calendarSymbol));

		// Click the calendar symbol
		calendarSymbol.click();

		// Log the action to Extent Report
		ExtentReportManager.logInfo("Clicked on calendar symbol...!");

		// Introduce a wait for 2 seconds (if necessary)
		Thread.sleep(2000);
	}


	// Method to click on the next arrow button
	public void clickNextArrow() throws InterruptedException {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

		wait.until(ExpectedConditions.elementToBeClickable(nextArrow));
		nextArrow.click();
		ExtentReportManager.logInfo("Clicked on next month arrow on calendar...!");
		Thread.sleep(2000); // Introducing a wait for 2 seconds
	}

	// Method to select a date from the calendar
	public void selectDate(String date) throws InterruptedException {
		WebElement dateXpath=driver.findElement(By.xpath("//button[text()='" + date + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(dateXpath));
		dateXpath.click();
		ExtentReportManager.logInfo("Selected Booking Date: " + date);
		Thread.sleep(7000); // Introducing a wait for 2 seconds
	}

	// Method to select a plan from the options package
	public void selectPlan(String plan) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='css-mmwbni']")));
		WebElement	planXpath=driver.findElement(By.xpath("//div//p[text()='" + plan + "']"));
		planXpath.click();
		ExtentReportManager.logInfo("Selected Plan: " + plan);
	}


	// Method to select duration from the options
	public void selectReservationSlot() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectReservationSlot));
		selectReservationSlot.click();
		ExtentReportManager.logInfo("Reservation Slot Selected...!");
		Thread.sleep(5000); // Introducing a wait for 2 seconds
	}

	// Method to select a package from the options
	public void selectPackage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectPackageButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", selectPackageButton);
		selectPackageButton.click();
		ExtentReportManager.logInfo("Package Selected...!");
		Thread.sleep(5000); 
	}

	// Method to select add guest option
	public void selectAddGuest() throws InterruptedException {
		//wait.until(ExpectedConditions.elementToBeClickable(selectAddGuestButton));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectAddGuestButton);
		selectAddGuestButton.click();
		ExtentReportManager.logInfo("Additional Guest Selected...!");
		Thread.sleep(5000); // Introducing a wait for 2 seconds
	}



	//per person
	public void selectToLanePlan() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(toLanePlan));
		toLanePlan.click();
		Thread.sleep(2000);
	}


	public void selectPersonCount(WebDriver driver) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectPerson));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectPerson);
		selectPerson.click();
		Thread.sleep(2000);
	}

	public void selectSaleItem(WebDriver driver) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectItem));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectItem);
		selectItem.click();
		Thread.sleep(2000);
	}

	// Method to click on proceed to pay button
	public void clickProceedToPayButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(proceedToPayButton));
		proceedToPayButton.click();
		ExtentReportManager.logInfo("Proceeded to payment...!");
		Thread.sleep(5000); // Introducing a wait for 2 seconds
	}

	//per hour 

	/**
	 * Edit the time using the given time string.
	 *
	 * @param driver the WebDriver instance
	 * @param time   the time to be edited
	 */
	public void enterBookingTime(WebDriver driver, String time) {
		wait.until(ExpectedConditions.elementToBeClickable(changeTime));
		Actions actions = new Actions(driver);
		actions.click(changeTime).sendKeys(time).sendKeys(Keys.ENTER).perform();
	}


	/**
	 * Select the previous time using the given time string.
	 *
	 * @param driver the WebDriver instance
	 * @param time   the time to be selected
	 */
	public void selectPreviousTime(WebDriver driver, String time) {
		Actions actions = new Actions(driver);
		actions.click(changeTime).sendKeys(time).sendKeys(Keys.ENTER).perform();
	}

	// Enter minutes in the input field
	public void enterMinutes(WebDriver driver, String minutes) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.click(enterMinutes).perform();
		actions.sendKeys(minutes).perform();
		actions.sendKeys(Keys.ENTER).perform();
	}



	/**
	 * Select the per hour plan.
	 *
	 * @throws InterruptedException if the thread sleep is interrupted
	 */
	public void selectPerHourPlan() throws InterruptedException {
		selectPerHourPlan.click();
		Thread.sleep(2000);
	}

	/**
	 * Select 1 hour.
	 *
	 * @throws InterruptedException if the thread sleep is interrupted
	 */
	public void select1Hour() throws InterruptedException {
		selectHour.click();
		Thread.sleep(2000);
	}

	/**
	 * Select guest and scroll to view if needed.
	 *
	 * @param driver the WebDriver instance
	 * @throws InterruptedException if the thread sleep is interrupted
	 */
	public void selectGuest(WebDriver driver) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectGuest);
		selectGuest.click();
		Thread.sleep(2000);
	}

	// Select AM start time
	public void selectAM(WebDriver driver, String time) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.click(selectAMStartTime).perform();
		actions.sendKeys(time).perform();
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
	}


	// per event 


	// Method to click on the 'Select Plan' button
	//    public void selectPerEventPlan() throws InterruptedException {
	//        planName.click();
	//        Thread.sleep(2000);
	//    }

	// Method to click on 'Event 1'
	public void clickEvent1() throws InterruptedException {
		event1.click();
		Thread.sleep(2000);
	}

	// Method to scroll and select a child element
	public void selectChild(WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", selectChild);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectChild);
		selectChild.click();
	}


	// Method to click on 'Event 3'
	public void clickEvent3() throws InterruptedException {
		event3.click();
		Thread.sleep(2000);
	}


}
