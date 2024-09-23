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


public class TC015EditEvent extends BaseClass
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

	// Edit the Event  
	@Test (testName = "testEditEvent")
	public void testEditEvent() throws IOException, InterruptedException 
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
			extentTest.info(" Customer Name Enter...! ");
			Thread.sleep(2000);
			calendarReservationPage.enterEmail(UtilityClass.getDataFromEs(1, "Email", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter ...!");
			logger.info("User information are entered...!");
			Thread.sleep(2000);

			calendarReservationPage.clickCreateReservationButton();
			extentTest.info("Click Create Reservation butto...!");
			logger.info("Clicked on create reservation button...!");
			Thread.sleep(3000);

			// Enter time for event type plan // 1 AM 
			createBkgStepperDetailsPage.enterBookingTime(driver, UtilityClass.getDataFromEs(1, "First  Slot booking  Time",  "multiple Booking creation"));
			Thread.sleep(2000);
			createBkgStepperDetailsPage.enterMinutes(driver, UtilityClass.getDataFromEs(1, "Minutes", "multiple Booking creation"));
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectAM(driver, UtilityClass.getDataFromEs(1, "Select Noon", "EditBooking"));
			extentTest.info("Time entered to create booking...!");
			logger.info("Time entered to create booking...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.clickCalendarSymbol();
			extentTest.info("Click on calander symbol...!");
			createBkgStepperDetailsPage.clickNextArrow();
			extentTest.info("Click on nextMonth Arrow on calander...!");
			createBkgStepperDetailsPage.selectDate();
			extentTest.info("Select Date for booking...!");
			logger.info("Date selected for booking...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.selectPerEventPlan();
			extentTest.info("Select Plan for booking...!");
			logger.info("Event type plan selected for booking...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.clickEvent1();
			extentTest.info("Select Event1...!");
			logger.info("Event1 selected...!");
			Thread.sleep(3000);


			createBkgStepperDetailsPage.selectChild(driver);
			extentTest.info("Select Child for booking...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectSaleItem(driver);
			extentTest.info("Select Itemfor booking...!");
			logger.info("Child/Item are selected...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(10000);

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
			extentTest.info("Enter card no....!");
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
			Thread.sleep(2000);


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


			//get event on which booking is created
			String GetEvent=stepperSummaryPage.getBookingDate();


			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Clicked on submit button...!");

			Thread.sleep(5000);

			extentTest.info(MarkupHelper.createLabel("Booking created successfully...!",
					ExtentColor.ORANGE));
			System.out.println("Booking created successfully...!");
			logger.info("Booking created successfully...!");
			Thread.sleep(3000);

			//click on view reservation button
			calendarReservationPage.clickViewReservationButton();
			extentTest.info("Click View Reservation Button...!");
			logger.info("Clicked on View Reservation button...!");
			Thread.sleep(2000);

			searchBookingPage.enterBookingId(BookingId);
			Thread.sleep(2000);


			searchBookingPage.clickBookingArrow();
			extentTest.info("Click on booking arrow...!");
			logger.info("Clicked on Booking arrow...!");
			Thread.sleep(2000);

			stepperEditPage.clickEditButton();
			extentTest.info("Click Edit Button...!");
			logger.info("Clicked on edit button...!");
			Thread.sleep(5000);

			createBkgStepperDetailsPage.clickEvent3();
			extentTest.info("Edit Event1 to Event3...!");
			logger.info("Event1 edited to Event3...!");
			Thread.sleep(3000);

			stepperEditPage.clickUpdateBookingButton();
			extentTest.info("Click Update Booking Button...!");
			logger.info("Clicked on update booking button...!");
			Thread.sleep(2000);

			stepperEditPage.clickConfirmEditButton();
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on Confirm Button...!");
			Thread.sleep(3000);

			stepperPaymentPage.selectCardPaymentMethod();
			extentTest.info("Select Card payment method for booking...!");
			logger.info("Card payment method selected for payment...!");
			Thread.sleep(3000);
			
			stepperPaymentPage.clickTipCrossButton();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickPayViaSavedCard();
			extentTest.info("Click Pay Via Save Card Option...!");
			logger.info("Clicked on Pay Via Save Card Option...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickPayButton();
			extentTest.info("Click pay Button...!");
			logger.info("Clicked on Pay button...!");
			Thread.sleep(3000);


			//get event from summurry screen
			String UpdatedEvent=stepperSummaryPage.getBookingDate();

			//Assertion applied to verify booking updated on expected event 
			Assert.assertNotEquals(GetEvent, UpdatedEvent, "Updated event of the booking is");
			extentTest.info(MarkupHelper.createLabel("Updated event verifying assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Updated event verifying assertion is passed...!");
			logger.info("Updated event verifying assertion is passed...!");


			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Clicked on submit button...!");

			extentTest.info(MarkupHelper.createLabel("Event updated successfully", 
					ExtentColor.ORANGE));
			System.out.println("Event updated successfully...!");
			logger.info("Event updated successfully...!");
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
		extentTest.fail("testEditEvent test case is fail due to" + getCause);
		logger.error("testEditEvent test case is fail due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}





}
