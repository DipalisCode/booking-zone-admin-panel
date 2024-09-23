package com.bookingzone.admin.FullPayment;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.EncryptedDocumentException;
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


public class TC013EditTime_CreateNewBooking extends BaseClass
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

	// User should able to Edit time and create new booking on previous time  
	@Test  (testName = "testEditTime_CreateNewBooking")
	public void testEditTime_CreateNewBooking() throws IOException, InterruptedException 
	{

		try 
		{
			//create booking
			businessHomePage.clickCalendarButton();
			extentTest.info("Click Calander button...! ");
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
			Thread.sleep(5000);


			createBkgStepperDetailsPage.enterBookingTime(driver, UtilityClass.getDataFromEs(1, "PreviousTime", "EditTime"));//5Am
			Thread.sleep(2000);
			createBkgStepperDetailsPage.enterMinutes(driver, UtilityClass.getDataFromEs(1, "Minutes", "multiple Booking creation"));
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectAM(driver, UtilityClass.getDataFromEs(1, "Select Noon", "EditBooking"));
			extentTest.info("Time entered to create booking...!");
			logger.info("Time entered to create booking...!");
			Thread.sleep(2000);

			createBkgStepperDetailsPage.clickCalendarSymbol();
			extentTest.info(" click on calander symbol...!");
			createBkgStepperDetailsPage.clickNextArrow();
			extentTest.info("Click on nextMonth Arrow on calander...!");
			createBkgStepperDetailsPage.selectDate();
			extentTest.info("Select Date for booking...!");
			logger.info("Date selected for booking...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.selectPerHourPlan();
			extentTest.info("Select Plan for booking...!");
			logger.info("Per Hour type plan selected for booking...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.select1Hour();
			extentTest.info("Select 1hour...! ");
			createBkgStepperDetailsPage.selectGuest(driver);
			Thread.sleep(3000);
			extentTest.info("Select Guest...!");
			createBkgStepperDetailsPage.selectSaleItem(driver);
			Thread.sleep(5000);
			extentTest.info("Select Item...!");
			logger.info("Hour/Guest/Items are selected...!");


			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);

			stepperPaymentPage.selectCashPaymentMethod();
			extentTest.info("Select Cash payment for booking...!");
			logger.info("Payment via cash option selected for payment...!");
			Thread.sleep(2000);

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
			Thread.sleep(3000);

			//get the booking Id
			String BookingId=stepperSummaryPage.getBookingId(driver);
			System.out.println("Booking Id is:-" + BookingId);

			//get booking status
			String Status=stepperSummaryPage.verifyBookingStatus();
			//Assertion applied to verify status of the booking after payment
			Assert.assertEquals(Status, "Successful", "Status of the booking is");
			extentTest.info(MarkupHelper.createLabel("Booking status assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Booking status assertion is passed...!");
			logger.info("Booking status assertion is passed...!");


			//get time on which booking is created
			String GetTime=stepperSummaryPage.getBookingDate();


			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit button...!");
			Thread.sleep(5000);

			extentTest.info(MarkupHelper.createLabel("Booking created sucessfully...!",
					ExtentColor.ORANGE));
			System.out.println("Booking created successfully...!");
			logger.info("Booking created successfully...!");


			//click on view reservation button
			calendarReservationPage.clickViewReservationButton();
			extentTest.info("Click View Reservation Button...!");
			logger.info("Clicked on View Reservation button...!");
			Thread.sleep(3000);

			searchBookingPage.enterBookingId(BookingId);
			Thread.sleep(3000);


			searchBookingPage.clickBookingArrow();
			extentTest.info("Click on booking arrow...!");
			logger.info("Clicked on Booking arrow...!");
			Thread.sleep(3000);


			stepperEditPage.clickEditButton();
			extentTest.info("Click Edit Button...!");
			logger.info("Clicked on edit button...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.enterBookingTime(driver, UtilityClass.getDataFromEs(1, "Edit Time", "EditTime"));//10Am
			Thread.sleep(2000);
			createBkgStepperDetailsPage.enterMinutes(driver, UtilityClass.getDataFromEs(1, "Minutes", "multiple Booking creation"));
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectAM(driver, UtilityClass.getDataFromEs(1, "Select Noon", "EditBooking"));
			extentTest.info("Select New Time To Edit...!");
			logger.info("New time selected for booking...!");
			Thread.sleep(5000);

			stepperEditPage.clickUpdateBookingButton();
			extentTest.info("Click Upbadate Booking Button...!");
			logger.info("Clicked on Update Booking Button...!");
			Thread.sleep(2000);

			stepperEditPage.clickConfirmEditButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");

			//get time from summurry screen
			String UpdatedTime=stepperSummaryPage.getBookingDate();

			//Assertion applied to verify booking updated on expected time 
			Assert.assertNotEquals(GetTime, UpdatedTime, "Updated Time of the booking is");
			extentTest.info(MarkupHelper.createLabel("Updated time verifying assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Updated time verifying assertion is passed...!");
			logger.info("Updated time verifying assertion is passed...!");

			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit Button...!");

			extentTest.info(MarkupHelper.createLabel("Booking Time updated successfully...!",
					ExtentColor.ORANGE));
			System.out.println("Booking Time updated successfully...!");
			logger.info("Booking Time updated successfully...!");

			Thread.sleep(5000);
			calendarReservationPage.clickRefreshButton(driver);
			Thread.sleep(5000);
			calendarReservationPage.clickRefreshButton(driver);

			logger.info("Clicked on refresh button...!");
			Thread.sleep(5000);

			calendarReservationPage.enterCustomerName(UtilityClass.getDataFromEs(1, "Booking Name", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter...! ");
			Thread.sleep(2000);
			calendarReservationPage.enterEmail(UtilityClass.getDataFromEs(1, "Email", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter...! ");
			logger.info("User information are entered...!");
			Thread.sleep(2000);

			calendarReservationPage.clickCreateReservationButton();
			extentTest.info("Click Create Reservation button...!");
			logger.info("Clicked on create reservation button...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.enterBookingTime(driver, UtilityClass.getDataFromEs(1, "PreviousTime", "EditTime"));//5Am
			Thread.sleep(2000);
			createBkgStepperDetailsPage.enterMinutes(driver, UtilityClass.getDataFromEs(1, "Minutes", "multiple Booking creation"));
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectAM(driver, UtilityClass.getDataFromEs(1, "Select Noon", "EditBooking"));
			extentTest.info("Select previous Time to create new booking...!");
			logger.info("Previous time selected for booking...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.selectPerHourPlan();
			extentTest.info("Select Plan for booking...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.select1Hour();
			extentTest.info("Select 1hour...!");
			createBkgStepperDetailsPage.selectGuest(driver);
			extentTest.info("Select Guest...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectSaleItem(driver);
			extentTest.info("Select Item...!");
			logger.info("Hour/Guest/Items are selected...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);

			stepperPaymentPage.selectCashPaymentMethod();
			extentTest.info("Select Cash payment for booking...!");
			logger.info("Payment via cash option selected for payment...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickTipCrossButton();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickReceivedCheckBox();
			extentTest.info("Click Payment Received checkbox...!");
			logger.info("Clicked on payment received checkbox...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickConfirmButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");
			logger.info("Cash payment done successfully...!");
			Thread.sleep(2000);

			//get previous time on which booking is created
			String PreviousTime=stepperSummaryPage.getBookingDate();

			//Assertion applied to verify booking created on expected time 
			Assert.assertEquals(GetTime, PreviousTime, "Newely created booking time is");
			extentTest.info(MarkupHelper.createLabel("Newely created booking time verifying assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Newely created booking time verifying assertion is passed...!");
			logger.info("Newely created booking time verifying assertion is passed...!");


			Thread.sleep(5000);

			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit button...!");

			extentTest.info(MarkupHelper.createLabel("Booking created on previous time successfully...!", ExtentColor.ORANGE));
			System.out.println("Booking created on previous time successfully...!");
			logger.info("Booking created on previous time successfully...!...!");


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
		extentTest.fail("testEditTime_CreateNewBooking Test case is Fail due to" + getCause);
		logger.error("testEditTime_CreateNewBooking test case is fail due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}


}
