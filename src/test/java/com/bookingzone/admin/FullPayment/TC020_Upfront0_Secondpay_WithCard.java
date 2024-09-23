package com.bookingzone.admin.FullPayment;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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


public class TC020_Upfront0_Secondpay_WithCard extends BaseClass
{

	// Initialise_Brouser();
	// Webdriver driver


	// create object  logger class
	Logger logger = (Logger) LogManager.getLogger("TC020_Upfront0_Secondpay_WithCard");

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

	// Create upfront 0 booking and make second pay with card
	@Test (testName = "Create upfront0 booking and make second pay with card")
	public void Create_upfront0_booking_make_secondpay_withcard() throws IOException, InterruptedException 
	{
		try
		{ 
			businessHomePage.clickCalendarButton();
			extentTest.info("Click Calander button...!");
			logger.info("Clicked on calendar button...!");
			Thread.sleep(2000);

			calendarReservationPage.selectOutlet(driver, null);
			extentTest.info("Outlet selected...!");
			logger.info("Outlet selected...!");
			Thread.sleep(2000);

			calendarReservationPage.enterCustomerName(UtilityClass.getDataFromEs(1, "Booking Name", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter...!");
			Thread.sleep(2000);
			calendarReservationPage.enterEmail(UtilityClass.getDataFromEs(1, "Email", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter...!");
			logger.info("User information are entered...!");
			Thread.sleep(2000);

			calendarReservationPage.clickCreateReservationButton();
			extentTest.info("Click Create Reservation button...!");
			logger.info("Clicked on create reservation button...!");
			Thread.sleep(3000);

			// Enter time for event type plan // 1 AM 
			createBkgStepperDetailsPage.enterBookingTime(driver, UtilityClass.getDataFromEs(1, "First  Slot booking  Time",  "multiple Booking creation"));
			Thread.sleep(2000);
			createBkgStepperDetailsPage.enterMinutes(driver, UtilityClass.getDataFromEs(1, "Minutes", "multiple Booking creation"));
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectAM(driver, UtilityClass.getDataFromEs(1, "Select Noon", "EditBooking"));
			extentTest.info("Booking time selected...!");
			logger.info("Booking time selected...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.clickCalendarSymbol();
			extentTest.info("Click on calander symbol...!");
			createBkgStepperDetailsPage.clickNextArrow();
			extentTest.info("Click on nextMonth Arrow on calander...!");
			createBkgStepperDetailsPage.selectDate();
			extentTest.info("Select Date for booking...!");
			logger.info("Date selected for booking...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.selectPlan();
			Thread.sleep(2000);
			createBkgStepperDetailsPage.select1Hour();
			extentTest.info("Plan and duration selected for booking...!");
			logger.info("Plan and duration selected for booking...!");
			Thread.sleep(2000);


			createBkgStepperDetailsPage.selectPackage(driver);
			extentTest.info("Select Package for booking...!");
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectAddGuest(driver);
			extentTest.info("Select additional Guest for booking...!");
			logger.info("Package and additional guests are selected...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);

			stepperPaymentPage.clickfullPayCheckBox();
			extentTest.info("Full payment check box unchecked...!");
			logger.info("Full payment check box unchecked...!");
			Thread.sleep(2000);


			//Assertion applied to verify upfront amount is 00
			Assert.assertEquals(driver.findElement
					(By.xpath("//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-uycaae-MuiGrid-root')][normalize-space()='$0.00']"))
					.isDisplayed(), true, "Upfront amount is");


			stepperPaymentPage.clickConfirmButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");
			Thread.sleep(3000);


			//get booking status
			String Status=stepperSummaryPage.verifyBookingStatus();
			//Assertion applied to verify status of the booking before payment
			Assert.assertEquals(Status, "Initiated", "Status of the booking is");
			extentTest.info(MarkupHelper.createLabel("Booking status assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Booking status assertion is passed...!");
			logger.info("Booking status assertion is passed...!");

			//get the booking Id
			String BookingId=stepperSummaryPage.getBookingId(driver);
			System.out.println("Booking Id is:-" +BookingId);

			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button");
			logger.info("Click on submit Button...!");
			Thread.sleep(3000);

			extentTest.info(MarkupHelper.createLabel("Initiated booking created without payment...!",
					ExtentColor.ORANGE));
			System.out.println("Initiated booking created without payment...!");
			logger.info("Initiated booking created without payment...!");

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

			stepperPaymentPage.selectCardPaymentMethod();
			extentTest.info("Select Card payment method for booking...!");
			logger.info("Pay via Card option selected for payment...!");
			Thread.sleep(5000);
			
			stepperPaymentPage.clickTipCrossButton();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			// To handle iframe element
			driver.switchTo().frame("__teConnectSecureFrame");
			stepperPaymentPage.enterCardNumber(UtilityClass.getDataFromEs(1, "Card No", "CreateBookings_CardPay"));
			extentTest.info("Enter card no...!");
			Thread.sleep(3000);
			stepperPaymentPage.enterExpiryDate(UtilityClass.getDataFromEs(1, "Expiry Date", "CreateBookings_CardPay"));
			extentTest.info("Enter Expiry Date...!");
			Thread.sleep(3000);
			stepperPaymentPage.enterCVC(UtilityClass.getDataFromEs(1, "CVC", "CreateBookings_CardPay"));
			extentTest.info("Enter CVC...!");
			logger.info("Card details entered successfully...!");
			Thread.sleep(3000);

			//To switch focus of selenium on main page
			driver.switchTo().defaultContent();
			stepperPaymentPage.clickPayButton();
			extentTest.info("Click Pay Button...!");
			logger.info("Clicked on pay button...!");
			Thread.sleep(3000);

		/*	Edit_Addon.Click_RefreshBtn();
			extentTest.info("Click Refresh Button...!");
			logger.info("Clicked on refresh button to change the status...!");
			Thread.sleep(2000);

			CashPay.Click_ReceivedCheckBox();
			logger.info("Clicked on Payment Received check box...!");
			extentTest.info("Click Payment Received check box...!");
			Thread.sleep(2000);

			CashPay.Click_ConfirmBtn();
			extentTest.info("Click Confirm button...!");
			logger.info("Clicked on confirm button...!");
			Thread.sleep(2000);*/

			//get booking status
			String StatusAfterPay=stepperSummaryPage.verifyBookingStatus();
			//Assertion applied to verify status of the booking after payment
			Assert.assertEquals(StatusAfterPay, "Successful", "Status of the booking is");
			extentTest.info(MarkupHelper.createLabel("Booking status assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Booking status assertion is passed...!");
			logger.info("Booking status assertion is passed...!");
			
			
			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Clicked on submit button...!");

			Thread.sleep(3000);

			extentTest.info(MarkupHelper.createLabel("Second payment done for upfront0 booking...!",
					ExtentColor.ORANGE));
			System.out.println("Second payment done for upfront0 booking...!");
			logger.info("Second payment done for upfront0 booking...!");


		}

		catch(Exception e) 
		{
			e.getStackTrace();
			String getCause = e.getLocalizedMessage();
			System.out.println("issue cause is :"+getCause);
			extentTest.fail("TC020_Upfront0_Secondpay_WithCard test case is fail due to" + getCause);
			logger.error("TC020_Upfront0_Secondpay_WithCard test case is fail due to " + getCause);
		}

     

	}






}
