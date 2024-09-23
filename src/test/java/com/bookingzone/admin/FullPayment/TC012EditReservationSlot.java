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


public class TC012EditReservationSlot extends BaseClass
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

	// Edit the Reservation slot and make additional payment
	@Test (testName = "Edit_the_ReservationSlot")
	public void Edit_the_ReservationSlot() throws IOException, InterruptedException 
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
			Thread.sleep(2000);

			createBkgStepperDetailsPage.clickCalendarSymbol();
			extentTest.info("Click on calander symbol...!");
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
			// 5 to 7 am--> on 5th slot
			createBkgStepperDetailsPage.selectReservationSlot();
			extentTest.info("Select Duration for booking...!");
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectPackage(driver);
			extentTest.info("Select Package for booking...!");
			Thread.sleep(2000);
			createBkgStepperDetailsPage.selectAddGuest(driver);
			extentTest.info("Select additional Guest for booking...!");
			logger.info("Package/Additional guests are selected...!");
			Thread.sleep(2000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
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
			Thread.sleep(2000);
			stepperPaymentPage.enterExpiryDate(UtilityClass.getDataFromEs(1, "Expiry Date", "CreateBookings_CardPay"));
			extentTest.info("Enter Expiry Date...!");
			Thread.sleep(2000);
			stepperPaymentPage.enterCVC(UtilityClass.getDataFromEs(1, "CVC", "CreateBookings_CardPay"));
			extentTest.info("Enter CVC...!");
			logger.info("Card details entered successfully...!");
			Thread.sleep(2000);

			//To switch focus of selenium on main page
			driver.switchTo().defaultContent();
			stepperPaymentPage.clickPayButton();
			extentTest.info("Click Pay Button...!");
			logger.info("Clicked on pay button...!");
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

			//get reservation slot of the booking
			String ReservationSlot=stepperSummaryPage.getBookingDate();

			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Clicked on submit button...!");
			Thread.sleep(2000);

			extentTest.info(MarkupHelper.createLabel("Booking created successfully...!", ExtentColor.ORANGE));
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
			Thread.sleep(8000);

			stepperEditPage.clickNewReservationSlot();
			extentTest.info("Click New Reservation Slot...!");
			logger.info("Clicked on new reservation slot...!");
			Thread.sleep(2000);

			stepperEditPage.clickUpdateBookingButton();
			extentTest.info("Click Update Booking Button...!");
			logger.info("Clicked on update booking button...!");

			stepperEditPage.clickConfirmEditButton();// shifted to 9 to 11 am
			extentTest.info("Click Confirm Button...!");
			logger.info("Clicked on confirm button...!");
			Thread.sleep(3000);

			if (driver.getPageSource() != "Choose a Payment Method") 
			{
				stepperPaymentPage.selectCashPaymentMethod();
				Thread.sleep(2000);
				extentTest.info("Select Cash Paymnet Method...!");
				logger.info("Cash payment method selected...!");


				stepperPaymentPage.clickTipCrossButton();
				extentTest.info("Click on tip cross button...!");
				logger.info("Clicked on tip cross button...!");
				Thread.sleep(2000);

				stepperPaymentPage.clickReceivedCheckBox();
				logger.info("Clicked on payment received check box...!");
				Thread.sleep(2000);
				extentTest.info("Click Payment Recieved CheckBox...!");

				stepperPaymentPage.clickConfirmButton();
				extentTest.info("Click Conferm Button...!");
				logger.info("Clicked on conferm button...!");
			}
			else 
			{
				System.out.println("Payment Not required...!");
			}

			Thread.sleep(5000);

			//get updated slot from summurry screen
			String UpdatedSlot=stepperSummaryPage.getBookingDate();

			//Assertion applied to verify booking updated on expected slot 
			Assert.assertNotEquals(ReservationSlot, UpdatedSlot, "Updated slot of the booking is");
			extentTest.info(MarkupHelper.createLabel("Updated slot verifying assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Updated slot verifying assertion is passed...!");
			logger.info("Updated slot verifying assertion is passed...!");


			stepperSummaryPage.clickSubmitButton();
			extentTest.info("Click Submit Button...!");
			logger.info("Clicked on submit button...!");

			extentTest.info(MarkupHelper.createLabel("Reservation Slot Updated successfully", ExtentColor.ORANGE));
			System.out.println("Reservation Slot Updated successfully...!");
			logger.info("Reservation Slot Updated successfully...!");
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
		extentTest.fail(" Edit_the_ReservationSlot test case is fail due to" + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}

}
