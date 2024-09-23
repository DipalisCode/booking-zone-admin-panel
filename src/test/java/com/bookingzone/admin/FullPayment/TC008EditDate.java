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

public class TC008EditDate extends BaseClass
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
		logger.info("Login credentials are entered...!");
	}


	// User should able to Edit date and update the booking on same date
	@Test (testName = "testEditDate")
	public void testEditDate() throws IOException, InterruptedException 
	{
		try {
			//create booking
			businessHomePage.clickCalendarButton();
			extentTest.info("Click Calander button...!");
			logger.info("Clicked on calendar button...!");
			Thread.sleep(2000);

			calendarReservationPage.selectOutlet(driver, null);
			extentTest.info("Outlet selected...!");
			logger.info("Outlet selected...!");
			Thread.sleep(2000);

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
			Thread.sleep(2000);

			createBkgStepperDetailsPage.clickCalendarSymbol();
			extentTest.info("Click on calander symbol ...!");
			createBkgStepperDetailsPage.clickNextArrow();
			extentTest.info("Click on nextMonth Arrow on calander...!");
			createBkgStepperDetailsPage.selectDate();
			extentTest.info("Select Date for booking...!");
			logger.info("Date selected for booking...!");
			Thread.sleep(2000);


			createBkgStepperDetailsPage.selectPlan();
			extentTest.info("Select Plan for booking...!");
			logger.info("Enable Package type plan selected for booking...!");

			Thread.sleep(2000);
			//5 to 7 AM
			createBkgStepperDetailsPage.selectReservationSlot();
			extentTest.info("Select Duration for booking...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectPackage(driver);
			extentTest.info("Select Package for booking...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectAddGuest(driver);
			extentTest.info("Select additional Guest for booking...!");
			logger.info("Package/Additional guests are selected...!");
			Thread.sleep(2000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(3000);

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
			Thread.sleep(2000);

			//	get the booking Id
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

			//get Booking date
			String bookingDate=stepperSummaryPage.getBookingDate();
			System.out.print("Booking created date is"+ bookingDate);

			Thread.sleep(5000);
			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit button...!");
			Thread.sleep(3000);

			extentTest.info(MarkupHelper.createLabel("Booking created successfully...!", ExtentColor.ORANGE));
			System.out.println("Booking created successfully...!");
			logger.info("Booking created successfully...!");
			Thread.sleep(5000);

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

			stepperEditPage.clickCalendarEdit(driver);
			extentTest.info("Click Calender to select new date...!");
			logger.info("Clicked on Calender to select new date...!");
			Thread.sleep(5000);

			stepperEditPage.editDate();//update date 
			extentTest.info("Click Date for update...!");
			logger.info("Clicked on new date...!");
			Thread.sleep(3000);


			stepperEditPage.clickUpdateBookingButton();
			extentTest.info("Click Upbadate Booking Button...!");
			logger.info("Clicked on Update Booking Button...!");
			Thread.sleep(5000);

			stepperEditPage.clickConfirmEditButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");
			Thread.sleep(2000);

			//get date updated date
			String UpdatedDate=stepperSummaryPage.getBookingDate();
			System.out.println("Updated date is" + UpdatedDate);

			//Assertion applied to verify booking updated on expected date 
			Assert.assertNotEquals(bookingDate, UpdatedDate, "Updated date of the booking is");
			extentTest.info(MarkupHelper.createLabel("Updated date verifying assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Updated date verifying assertion is passed...!");
			logger.info("Updated date verifying assertion is passed...!");


			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit Button...!");
			Thread.sleep(2000);

			extentTest.info(MarkupHelper.createLabel("Booking Date updated successfully...!", ExtentColor.ORANGE));
			System.out.println("Booking Date updated successfully...!");
			logger.info("Booking Date updated successfully...!");

			//Create new booking on previous date
			// on previous date --on 4 th slot--> 5 to 7 am
			calendarReservationPage.enterCustomerName(UtilityClass.getDataFromEs(1, "Booking Name", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter...!");
			Thread.sleep(2000);
			calendarReservationPage.enterEmail(UtilityClass.getDataFromEs(1, "Email", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter ...!");
			logger.info("User information are entered...!");
			Thread.sleep(2000);

			calendarReservationPage.clickCreateReservationButton();
			extentTest.info("Click Create Reservation button...!");
			logger.info("Clicked on create reservation button...!");

			createBkgStepperDetailsPage.clickCalendarSymbol() ;
			extentTest.info("Click on calander symbol...! ");
			createBkgStepperDetailsPage.selectDate();
			extentTest.info("Select Date for booking...!");
			logger.info("Date selected for booking...!");
			Thread.sleep(3000);


			createBkgStepperDetailsPage.selectPlan();
			extentTest.info("Select Plan for booking...!");
			logger.info("Enable Package type plan selected for booking...!");

			Thread.sleep(2000);

			createBkgStepperDetailsPage.selectReservationSlot();
			extentTest.info("Select Duration for booking...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectPackage(driver);
			extentTest.info("Select Package for booking...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectAddGuest(driver);
			extentTest.info("Select additional Guest for booking...!");
			logger.info("Package/Additional guests are selected...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(3000);

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
			Thread.sleep(3000);

			stepperPaymentPage.clickConfirmButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");
			logger.info("Cash payment done successfully...!");
			Thread.sleep(5000);

			//get date
			String NewbkngDate=stepperSummaryPage.getBookingDate();

			//Assertion applied to verify booking updated on expected date 
			Assert.assertEquals(NewbkngDate, bookingDate, "Newely created booking date is");
			extentTest.info(MarkupHelper.createLabel("Newely created booking date verifying assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Newely created booking date verifying assertion is passed...!");
			logger.info("Newely created booking date verifying assertion is passed...!");

			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Click on submit button...!");

			extentTest.info(MarkupHelper.createLabel("Booking created successfully on existing date...!", ExtentColor.ORANGE));
			System.out.println("Booking created successfully on existing date...!");
			logger.info("Booking created successfully on existing date...!");

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
		extentTest.fail("testEditDate test case is fail due to" + getCause);
		logger.error("testEditDate test case is fail due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
