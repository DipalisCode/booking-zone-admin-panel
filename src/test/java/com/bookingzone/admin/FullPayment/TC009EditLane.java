package com.bookingzone.admin.FullPayment;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bookingzone.admin.pages.BaseClass;
import com.bookingzone.admin.pages.BusinessHomePage;
import com.bookingzone.admin.pages.CalendarReservationPage;
import com.bookingzone.admin.pages.CreateBookingStepperDetailsPage;
import com.bookingzone.admin.pages.LogInPage;
import com.bookingzone.admin.pages.SearchBookingPage;
import com.bookingzone.admin.pages.StepperEditPage;
import com.bookingzone.admin.pages.StepperPaymentPage;
import com.bookingzone.admin.pages.StepperSummaryPage;
import com.bookingzone.admin.utils.UtilityClass;


public class TC009EditLane extends BaseClass
{
	Logger logger = (Logger) LogManager.getLogger(this.getClass());

	LogInPage login;
	BusinessHomePage businessHomePage;
	CalendarReservationPage calendarReservationPage;
	CreateBookingStepperDetailsPage createBkgStepperDetailsPage;
	StepperPaymentPage stepperPaymentPage;
	StepperSummaryPage stepperSummaryPage;
	SearchBookingPage searchBookingPage;
	StepperEditPage stepperEditPage;


	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		initialiseBrowser(Context);
		logger.info("Browser opened...!");

		//Initialise_Brouser();

		// object of pom classes
		login = new LogInPage(driver);
		businessHomePage = new BusinessHomePage();
		calendarReservationPage = new CalendarReservationPage();
		createBkgStepperDetailsPage = new CreateBookingStepperDetailsPage();
		stepperPaymentPage = new StepperPaymentPage();
		stepperSummaryPage = new StepperSummaryPage();
		searchBookingPage=new SearchBookingPage(driver);
		stepperEditPage=new StepperEditPage(driver);

	}

	@BeforeMethod
	public void Login() throws InterruptedException, IOException 
	{

		login.clickBusinessRadioButton();
		login.enterUsername(UtilityClass.getDataFromEs(1, "Email", "Business_Data"));
		login.enterPassword(UtilityClass.getDataFromEs(1, "Bussiness Password", "Business_Data"));
		login.clickLoginButton();
		Thread.sleep(5000);
		logger.info("Login credentials are entered...!");
	}

	// User should able to Edit Lane and update the same slot  
	@Test  (testName = "testEditLane")
	public void testEditLane() throws IOException, InterruptedException 
	{
		try 
		{
			//create booking
			// 9 am --> 2nd slot
			businessHomePage.clickCalendarButton();
			extentTest.info("Click Calander button...!");
			logger.info("Clicked on calendar button...!");
			Thread.sleep(2000);

			calendarReservationPage.selectOutlet(driver, null);
			extentTest.info("Outlet selected...!");
			logger.info("Outlet selected...!");
			Thread.sleep(2000);

			calendarReservationPage.enterCustomerName(UtilityClass.getDataFromEs(1, "Booking Name", "CreateBookings_CardPay"));
			extentTest.info("Customer Name Enter...!");
			Thread.sleep(2000);
			calendarReservationPage.enterEmail(UtilityClass.getDataFromEs(1, "Email", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter...! ");
			logger.info("User information are entered...!");
			Thread.sleep(3000);

			calendarReservationPage.scrollFor9AM(driver);
			calendarReservationPage.clickCalendarCell(); //This method use to click cell on calendar
			extentTest.info("Cell click  on calander...!");
			logger.info("Clicked cell on calander...!");
			Thread.sleep(2000);

			createBkgStepperDetailsPage.clickCalendarSymbol();
			extentTest.info("click on calander symbol...!");
			createBkgStepperDetailsPage.clickNextArrow();
			extentTest.info("Click on nextMonth Arrow on calander...!");
			createBkgStepperDetailsPage.selectDate();
			extentTest.info("Select Date for booking...!");
			logger.info("Date selected for booking...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.selectToLanePlan();// select plan which have only two lanes
			extentTest.info("Select Plan for booking...!");
			logger.info("Per Person type plan selected for booking...!");

			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectPersonCount(driver);
			extentTest.info("Select Person/guest...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.selectSaleItem(driver);
			extentTest.info("Select Item...!");
			logger.info("Person/Item are selected...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);

			stepperPaymentPage.selectCashPaymentMethod();
			extentTest.info("Select Cash payment for booking...!");
			logger.info("Payment via cash option selected for payment...!");
			Thread.sleep(3000);

			stepperPaymentPage.clickTipCrossButton();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickReceivedCheckBox();
			extentTest.info("Click Payment Received checkbox...! ");
			logger.info("Clicked on payment received checkbox...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickConfirmButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");

			logger.info("Cash payment done successfully...!");

			//			get the booking Id
			String BookingId=stepperSummaryPage.getBookingId(driver);
			System.out.println("Booking Id is:-" +BookingId);


			//get booking status
			String Status=stepperSummaryPage.verifyBookingStatus();
			//Assertion applied to verify status of the booking after payment
			Assert.assertEquals(Status, "Successful", "Status of the booking is");
			extentTest.info(MarkupHelper.createLabel("Booking status assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Booking status assertion is passed...!");
			logger.info("Booking status assertion is passed...!");

			//get selected lane
			String selectedlane=stepperSummaryPage.getSelectedLane();


			Thread.sleep(5000);
			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit button...!");
			Thread.sleep(5000);

			extentTest.info(MarkupHelper.createLabel("Booking created successfully...!", ExtentColor.ORANGE));
			System.out.println("Booking created successfully...!");
			logger.info("Booking created successfully...!");

			//click created booking for update lane
			//			Edit_Lane.Click_Created_Booking(driver);
			//			extentTest.info("Click Created booking to edit lane");
			//			logger.info("Clicked on created booking to update the lane...!");
			//			Thread.sleep(5000);

			//click on view reservation button
			calendarReservationPage.clickViewReservationButton();
			extentTest.info("Click View Reservation Button...!");
			logger.info("Clicked on View Reservation button...!");
			Thread.sleep(3000);

			searchBookingPage.enterBookingId(BookingId);
			System.out.println("Booking Id entered successfully...!");
			Thread.sleep(3000);

			searchBookingPage.clickBookingArrow();
			extentTest.info("Click on booking arrow...!");
			logger.info("Clicked on Booking arrow...!");
			Thread.sleep(3000);

			stepperEditPage.clickEditButton();
			extentTest.info("Click Edit Button...!");
			logger.info("Clicked on edit button...!");
			Thread.sleep(5000);

			stepperEditPage.unClickDefaultLane();//lane1
			extentTest.info("UnClick Default selected lane...! ");
			logger.info("Default selected lane unClicked...!");
			Thread.sleep(5000);

			stepperEditPage.selectNewLane();//lane2
			extentTest.info("Click New Lane...! ");
			logger.info("Select updated lane...!");
			Thread.sleep(5000);

			stepperEditPage.clickUpdateBookingButton();
			extentTest.info("Click Update Booking Button...!");
			logger.info("Clicked on Update Booking Button...!");
			Thread.sleep(5000);

			stepperEditPage.clickConfirmEditButton();
			extentTest.info("Click Conferm Button...!");
			logger.info("Clicked on Conferm Button...!");
			Thread.sleep(3000);
			//get selected lane
			String updatedLane=stepperSummaryPage.getSelectedLane();

			//Assertion applied to verify updated lane of the booking
			Assert.assertEquals(updatedLane, "GameZone-2", "Selected lane of the booking is");
			extentTest.info(MarkupHelper.createLabel("Updated lane assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Updated lane assertion is passed...!");
			logger.info("Updated lane assertion is passed...!");

			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit Button...!");

			extentTest.info(MarkupHelper.createLabel("Booking Lane updated successfully...!", ExtentColor.ORANGE));
			System.out.println("Booking Lane updated successfully...!");
			logger.info("Booking Lane updated successfully...!");

			//click on view reservation button
			calendarReservationPage.clickViewReservationButton();
			extentTest.info("Click View Reservation Button...!");
			logger.info("Clicked on View Reservation button...!");
			Thread.sleep(3000);

			searchBookingPage.enterBookingId(BookingId);
			System.out.println("Booking Id entered successfully...!");
			Thread.sleep(3000);

			searchBookingPage.clickBookingArrow();
			extentTest.info("Click on booking arrow...!");
			logger.info("Clicked on Booking arrow...!");
			Thread.sleep(3000);

			stepperEditPage.clickEditButton();
			extentTest.info("Click Edit Button...!");
			logger.info("Click on edit button...!");
			Thread.sleep(7000);

			createBkgStepperDetailsPage.selectPersonCount(driver);
			extentTest.info("Increase person count...!");
			logger.info("Increase person count...!");
			Thread.sleep(1000);

			System.out.println("Both the lanes are selected as person count increased...!");

			stepperEditPage.clickUpdateBookingButton();
			extentTest.info("Click Update Booking Button...!");
			logger.info("Click Update Booking Button...!");
			Thread.sleep(3000);

			stepperEditPage.clickConfirmEditButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");
			Thread.sleep(3000);

			stepperPaymentPage.selectCashPaymentMethod();
			extentTest.info("Select Cash payment method for booking...!");
			logger.info("Pay via Cash option selected for payment...!");
			Thread.sleep(3000);

			stepperPaymentPage.clickTipCrossButton();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickReceivedCheckBox();
			extentTest.info("Click Payment Recieved checkbox...! ");
			logger.info("Clicked on payment received checkbox...!");
			Thread.sleep(3000);

			stepperPaymentPage.clickConfirmButton();
			extentTest.info("Click Conferm Button...!");
			logger.info("Click on confirm Button...!");


			Assert.assertEquals(driver.findElement(By.xpath("(//div//span[@class='MuiChip-label MuiChip-labelMedium css-6od3lo-MuiChip-label'])[2]"))
					.isDisplayed(), true);

			Assert.assertEquals(driver.findElement(By.xpath("(//div//span[@class='MuiChip-label MuiChip-labelMedium css-6od3lo-MuiChip-label'])"))
					.isDisplayed(), true);

			extentTest.info(MarkupHelper.createLabel("Updated lane assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Updated lane assertion is passed...!");
			logger.info("Updated lane assertion is passed...!");
			Thread.sleep(5000);

			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on Submit Button...!");

			extentTest.info(MarkupHelper.createLabel("Free lane updated successfully...!", ExtentColor.ORANGE));
			System.out.println("Free lane updated successfully...!");
			logger.info("Free lane updated successfully...!");

		}

		catch(Exception e) 
		{
			handleException(e);
		}

	}


	// Method to handle exceptions and log error messages
	public void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("issue cause is :"+getCause);
		extentTest.fail("testEditLane test case is Fail due to" + getCause);
		logger.error("testEditLane test case is fail due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}



}
