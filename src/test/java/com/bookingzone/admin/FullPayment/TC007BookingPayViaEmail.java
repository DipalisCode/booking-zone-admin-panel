package com.bookingzone.admin.FullPayment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import com.bookingzone.admin.pages.OrderSummaryPage;
import com.bookingzone.admin.pages.StepperPaymentPage;
import com.bookingzone.admin.pages.StepperSummaryPage;
import com.bookingzone.admin.pages.YopMailPage;
import com.bookingzone.admin.pages.PaymentPage;
import com.bookingzone.admin.utils.UtilityClass;

public class TC007BookingPayViaEmail extends BaseClass
{
	Logger logger=(Logger) LogManager.getLogger(this.getClass());


	LogInPage login;
	BusinessHomePage businessHomePage;
	CalendarReservationPage calendarReservationPage;
	CreateBookingStepperDetailsPage createBkgStepperDetailsPage;
	StepperPaymentPage stepperPaymentPage;
	StepperSummaryPage stepperSummaryPage;
	YopMailPage yopmail;
	OrderSummaryPage orderSummaryPage;
	PaymentPage  paymentPage ;


	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		initialiseBrowser(Context);
		logger.info("Browser Opened...!");

		// Initialize page objects
		login = new LogInPage(driver);
		businessHomePage = new BusinessHomePage();
		calendarReservationPage = new CalendarReservationPage();
		createBkgStepperDetailsPage = new CreateBookingStepperDetailsPage();
		stepperPaymentPage = new StepperPaymentPage();
		stepperSummaryPage = new StepperSummaryPage();
		yopmail=new YopMailPage(driver);
		orderSummaryPage=new OrderSummaryPage(driver);
		paymentPage=new PaymentPage(driver);
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

	// Create booking pay via Email 
	@Test (testName = "testCreateBookingPayVaiEmail")
	public void testCreateBookingPayVaiEmail() throws IOException, InterruptedException 
	{

		try
		{
			businessHomePage.clickCalendarButton();
			extentTest.info("Click Calendar button...!");
			logger.info("Clicked on calendar button...!");
			Thread.sleep(000);

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

			createBkgStepperDetailsPage.selectDate();
			extentTest.info("Select Plan for booking...!");
			logger.info("Enable Package type plan selected for booking...!");
			Thread.sleep(3000);

			// 5 to 7 AM
			createBkgStepperDetailsPage.selectReservationSlot();
			extentTest.info("Select Duration for booking...!");
			Thread.sleep(5000);
			createBkgStepperDetailsPage.selectPackage(driver);
			extentTest.info("Select Package for booking...!");
			Thread.sleep(3000);
			createBkgStepperDetailsPage.selectAddGuest(driver);
			extentTest.info("Select additional Guest for booking...!");
			logger.info("Package and additional guests are selected...!");
			Thread.sleep(3000);

			createBkgStepperDetailsPage.clickProceedToPayButton();
			extentTest.info("Click on Proceed to pay button...!");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);


			//Get text of upfront amount on payment page
			String TotalAmount=stepperPaymentPage.getTotalAmountOnPaymentPage();
			Thread.sleep(2000);

			stepperPaymentPage.selectEmailPaymentMethod();
			extentTest.info("Select Email payment method for booking...!");
			logger.info("Pay via email option selected for payment...!");
			Thread.sleep(3000);

			stepperPaymentPage.clickTipCrossButton();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			stepperPaymentPage.clickSendLink(driver);
			extentTest.info("Link has been send successfully...!");
			logger.info("Link has been send sucessfully...!");
			Thread.sleep(5000);

			//Assertion applied to verify the link is sended successfully 
			String  ActualResult=driver.findElement(By.xpath("//div[@id='notistack-snackbar']")).getText();
			String ExpectedResult="Payment Details send successfully.";
			Assert.assertEquals(ActualResult, ExpectedResult);
			extentTest.info(MarkupHelper.createLabel("Email link sended assetion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Email link sended assetion is passed...!");
			logger.info("Email link sended assetion is passed...!");


			yopmail.navigateToYopMail(driver);
			extentTest.info("Navigated to yopmail.com...!");
			logger.info("Navigated to yopmail.com...!");
			Thread.sleep(5000);

			yopmail.enterLoginEmail(UtilityClass.getDataFromEs(1, "Enter Yopmail user name", "CreateBookings_CardPay"));
			extentTest.info("Enter UN for yopmail...!");
			Thread.sleep(5000);
			yopmail.clickMailArrow();
			extentTest.info("Click on next arrow on yopmail...!");
			logger.info("Logged in into yop mail account...!");
			Thread.sleep(10000);


			// To handle iframe element
			driver.switchTo().frame("ifmail");

			yopmail.clickLinkInMail();// click link inside mail
			extentTest.info("Click Link Given in mail...!");
			logger.info("Clicked on link which navigates to payment page...!");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// wait



			//To switch focus of selenium on main page
			driver.switchTo().defaultContent();
			Thread.sleep(5000);

			// need to handle window pop-up
			Set<String>  AllIds=driver.getWindowHandles();
			ArrayList<String> ar=new  ArrayList<String>(AllIds);
			String WindowPopId=ar.get(1);
			// To switch focus window pop-up
			driver.switchTo().window(WindowPopId);

			//get text of balance amount on order summarry
			String PayableAmount=orderSummaryPage.getPayableAmountOnOrderSummaryPage();

			//apply assertion to verify upfront amount(of pay page)== balance amount()
			Assert.assertEquals(TotalAmount, PayableAmount, "Payable amount is");
			extentTest.info(MarkupHelper.createLabel("Assertion of payable amount is passed...!",
					ExtentColor.GREEN));
			System.out.println("Assertion of payable amount is passed...!");
			logger.info("Assertion of payable amount is passed...!");


			orderSummaryPage.clickPayButton();
			logger.info("Clicked on pay button...!");
			Thread.sleep(3000);


			// need to handle iframe element
			driver.switchTo().frame("__teConnectSecureFrame");
			stepperPaymentPage.enterCardNumber(UtilityClass.getDataFromEs(1, "Card No", "CreateBookings_CardPay"));
			extentTest.info("Enter Card No....!");
			Thread.sleep(3000);
			stepperPaymentPage.enterExpiryDate(UtilityClass.getDataFromEs(1, "Expiry Date", "CreateBookings_CardPay"));
			extentTest.info("Enter Expiry Date...!");
			Thread.sleep(3000);
			stepperPaymentPage.enterCVC(UtilityClass.getDataFromEs(1, "CVC", "CreateBookings_CardPay"));
			extentTest.info("Enter CVC...!");
			logger.info("Card details entered successfully...!");

			//To switch focus of selenium on main page
			driver.switchTo().defaultContent();
			Thread.sleep(5000);


			paymentPage.clickPayOnPaymentPage();
			extentTest.info("Click pay on Payment Page...!");
			logger.info("Clicked on final pay button...!");
			Thread.sleep(5000);

			String  ActualResult1=driver.findElement(By.xpath("//p[contains(@class,'p-0 BookingStatus_bill__status__njOUp BookingStatus_success__19dxa')]")).getText();
			String ExpectedResult1="Your Party Is Booked!";
			Assert.assertEquals(ActualResult1, ExpectedResult1);
			extentTest.info(MarkupHelper.createLabel("Booking status assertion is passed...!",
					ExtentColor.GREEN));
			System.out.println("Booking status assertion is passed...!");
			logger.info("Booking status assertion is passed...!");



			System.out.println("Your Party is Booked by paying via email...!");
			extentTest.info(MarkupHelper.createLabel("Your Party is Booked by paying via email...!" ,
					ExtentColor.ORANGE));
			logger.info("Your Party is Booked by paying via email...!");

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
		extentTest.fail("testCreateBookingPayVaiEmail test case is fail due to" + getCause);
		logger.error("testCreateBookingPayVaiEmail test case is fail due to " + getCause);
		
		Assert.fail("Test case failed due to exception: " + getCause);
	}


}
