package com.bookingzone.admin.blockslot.tests;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bookingzone.admin.pages.A_Base_Class;
import com.bookingzone.admin.pages.Add_Service;
import com.bookingzone.admin.pages.Add_VenueDetails;
import com.bookingzone.admin.pages.Admin_LogOut;
import com.bookingzone.admin.pages.Block_slots;
import com.bookingzone.admin.pages.Booking_CashPayment;
import com.bookingzone.admin.pages.Booking_PaymentVia_Email;
import com.bookingzone.admin.pages.Bussiness_Login1;
import com.bookingzone.admin.pages.Create_Booking1_CardPay;
import com.bookingzone.admin.pages.Create_Bookings_traffic;
import com.bookingzone.admin.pages.Edit_Addon;
import com.bookingzone.admin.pages.Edit_Date;
import com.bookingzone.admin.pages.Edit_Time;
import com.bookingzone.admin.pages.Mail_Paymentgateway;
import com.bookingzone.admin.pages.Update_Booking_FromTraffic;
import com.bookingzone.admin.pages.UtilityClass;

public class TC017_CreateBooking_Traffic extends A_Base_Class
{
	// Initialise_Brouser();
	// Webdriver driver

	//Create object of logger class

	Logger logger=(Logger) LogManager.getLogger("TC017_CreateBooking_Traffic");

	Bussiness_Login1 Blogin;
	Create_Booking1_CardPay BookingCreation;
	Booking_CashPayment CashPayment;
	Edit_Time  EditTime;
	Create_Bookings_traffic Create_Multi_Bookings;
	Edit_Date EditDate;
	Update_Booking_FromTraffic Update_Booking_Traffic;
	Block_slots BlockSlot;
	Mail_Paymentgateway Paymentgetway;
	Booking_PaymentVia_Email PayviaEmail;
	Edit_Addon Edit_Addon;
	Booking_CashPayment CashPay;

	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		Initialise_Browser(Context);
		logger.info("Browser Opened...!");

		//Initialise_Brouser();

		// object of pom classes
		Blogin=new Bussiness_Login1(driver);
		BookingCreation=new Create_Booking1_CardPay(driver);
		CashPayment=new Booking_CashPayment(driver);
		EditTime=new Edit_Time(driver);
		Create_Multi_Bookings=new Create_Bookings_traffic(driver);
		EditDate=new Edit_Date(driver);
		Update_Booking_Traffic=new Update_Booking_FromTraffic(driver);
		BlockSlot=new Block_slots(driver);
		Paymentgetway=new Mail_Paymentgateway(driver);
		PayviaEmail=new Booking_PaymentVia_Email(driver); 
		Edit_Addon=new Edit_Addon(driver);
		CashPay=new Booking_CashPayment(driver);
	}

	@BeforeMethod
	public void Login() throws InterruptedException, IOException 
	{

		Blogin.Click_Radio();
		Blogin.Enter_UN(UtilityClass.getdatafromPF("UN_Business"));
		Blogin.Enter_PWS(UtilityClass.getdatafromPF("PWS_Business"));
		Blogin.Click_Login();
		Thread.sleep(5000);
		logger.info("Login credentials are entered...!");

	}

	// create Multiple booking on calendar or create traffic
	@Test (testName = "Create_Multiple_Bookings")
	public void Create_Multiple_Bookings() throws IOException, InterruptedException 
	{
		try
		{ 

			BookingCreation.ClickCalenderbtn();
			extentTest.info("Click Calander button ");
			logger.info("Clicked on calendar button...!");
			Thread.sleep(2000);

			BookingCreation.select_Outlet(driver);
			extentTest.info("Outlet selected");
			logger.info("Outlet selected...!");
			Thread.sleep(2000);

			// create booking on 1 to 3 am 
			BookingCreation.Customer_Name(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter ");
			Thread.sleep(2000);
			BookingCreation.EnterEmail(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter ");
			logger.info("User information are entered...!");

			BookingCreation.Click_CreateReservation();
			extentTest.info("Click Create Reservation button");
			logger.info("Clicked on create reservation button...!");

			Create_Multi_Bookings.Click_Calendersymbol();
			extentTest.info("Click on calander symbol ");
			Create_Multi_Bookings.Click_nextArrow();
			extentTest.info("Click on nextMonth Arrow on calander");
			BlockSlot.Select_Date();
			extentTest.info("Select Date for booking");
			logger.info("Date selected for booking...!");
			Thread.sleep(3000);

			EditTime.Edit_Time(driver, UtilityClass.getdatafromEs(1, "",  "multiple Booking creation"));
			Create_Multi_Bookings.Enter_Minutes(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation"));
			Create_Multi_Bookings.SelectNoon_StartTime(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			extentTest.info("Enter time to create First bookings ");
			logger.info("Time entered for creating First booking...!");
			Thread.sleep(2000);

			Create_Multi_Bookings.Select_BirthDayPackagePlan();
			extentTest.info("Birthday package plan selected");
			logger.info("Birthday package selected...!");
			Thread.sleep(2000);

			Create_Multi_Bookings.Select_Two_Hour();
			extentTest.info("Select 2hour ");
			logger.info("2 hour duration selected...!");
			Thread.sleep(3000);

			Create_Multi_Bookings.SelectPackage(driver);
			extentTest.info("Select Package for booking");
			Thread.sleep(2000);
			Create_Multi_Bookings.SelectaddGuest(driver);
			extentTest.info("Select additional Guest for booking");
			logger.info("Package and additional guests are selected...!");
			Thread.sleep(2000);

			BookingCreation.Click_ProceedToPay();
			extentTest.info("Click on Proceed to pay button");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);

			BookingCreation.Select_CardPymentMethod();
			extentTest.info("Select Card payment method for booking");
			logger.info("Pay via Card option selected for payment...!");
			Thread.sleep(3000);
			
			BookingCreation.Click_Tipcrossbtn();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			// To handle iframe element
			driver.switchTo().frame("__teConnectSecureFrame");
			BookingCreation.EnterCardNo(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Enter card no.");
			Thread.sleep(2000);
			BookingCreation.EnterExpiryDate(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Enter Expiry Date");
			Thread.sleep(2000);
			BookingCreation.Entercvc(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Enter CVC");
			logger.info("Card details entered successfully...!");
			Thread.sleep(2000);

			//To switch focus of selenium on main page
			driver.switchTo().defaultContent();
			BookingCreation.ClickPayBtn();
			extentTest.info("Click Pay Button");
			logger.info("Clicked on pay button...!");
			Thread.sleep(2000);

			// click on refresh button
			// click payment received checkbox
			// click conferm
			Edit_Addon.Click_RefreshBtn();
			extentTest.info("Click Refresh Button");
			logger.info("Clicked on refresh button to change the status...!");
			Thread.sleep(2000);

			CashPay.Click_ReceivedCheckBox();
			extentTest.info("Click Payment Received checkbox");
			Thread.sleep(2000);
			logger.info("Clicked on Payment Received checkbox...!");

			CashPay.Click_ConfirmBtn();
			extentTest.info("Click Confirm button");
			logger.info("Clicked on confirm button...!");

			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']"))
					.isDisplayed(), true);

			BookingCreation.Click_SubmitButton();
			extentTest.info("Click Submit Button");
			logger.info("Clicked on submit button...!");
			Thread.sleep(5000);

			extentTest.info(MarkupHelper.createLabel("First booking successfully created...!", ExtentColor.ORANGE));
			System.out.println("First booking successfully created...!");
			logger.info("First booking successfully created...!");

			//create booking on 5:30 to 7:30
			BookingCreation.Customer_Name(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter ");
			Thread.sleep(2000);
			BookingCreation.EnterEmail(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter ");
			logger.info("User information are entered...!");

			BookingCreation.Click_CreateReservation();
			extentTest.info("Click Create Reservation button");
			logger.info("Clicked on create reservation button...!");

			EditTime.Edit_Time(driver, UtilityClass.getdatafromEs(2, "",  "Slot Blocking"));
			Create_Multi_Bookings.Enter_Minutes(driver, UtilityClass.getdatafromEs(1, "", "Slot Blocking"));
			Create_Multi_Bookings.SelectNoon_StartTime(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			extentTest.info("Enter time to create Second bookings");
			logger.info("Time entered for creating Second booking...!");
			Thread.sleep(2000);

			Create_Multi_Bookings.Select_BirthDayPackagePlan();
			extentTest.info("Birthday package plan selected");
			logger.info("Birthday package selected...!");

			Create_Multi_Bookings.Select_Two_Hour();
			extentTest.info("Select 2hour ");
			logger.info("2 hour duration selected...!");

			Create_Multi_Bookings.SelectPackage(driver);
			extentTest.info("Select Package for booking");
			Thread.sleep(3000);
			Create_Multi_Bookings.SelectaddGuest(driver);
			extentTest.info("Select additional Guest for booking");
			logger.info("Package and additional guests are selected...!");

			BookingCreation.Click_ProceedToPay();
			extentTest.info("Click on Proceed to pay button");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);

			CashPayment.Select_CashPymentMethod();
			extentTest.info("Select Cash payment method for booking");
			logger.info("Pay via Cash option selected for payment...!");
			Thread.sleep(2000);
			
			BookingCreation.Click_Tipcrossbtn();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			CashPayment.Click_ReceivedCheckBox();
			extentTest.info("Click payment Received Check Box");
			logger.info("Clicked on payment Received check box...!");
			Thread.sleep(2000);
			CashPayment.Click_ConfirmBtn();
			extentTest.info("Click Confirm Button");
			logger.info("Clicked on Confirm Button...!");
			Thread.sleep(5000);

			EditDate.Click_Submit();
			extentTest.info("Click Submit Button");
			logger.info("Click on submit button...!");
			Thread.sleep(2000);

			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']"))
					.isDisplayed(), true);


			extentTest.info(MarkupHelper.createLabel("Second booking successfully created...!", ExtentColor.ORANGE));
			System.out.println("Second booking successfully created...!");
			logger.info("Second booking successfully created...!");



			// Create bookings on second slots 
			BookingCreation.Customer_Name(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter ");
			Thread.sleep(2000);
			BookingCreation.EnterEmail(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter ");
			logger.info("User information are entered...!");

			BookingCreation.Click_CreateReservation();
			extentTest.info("Click Create Reservation button");
			logger.info("Clicked on create reservation button...!");

			//Enter time for  booking/ booking second slot i.e. 04  AM
			EditTime.Edit_Time(driver, UtilityClass.getdatafromEs(1, "",  "multiple Booking creation"));
			Create_Multi_Bookings.Enter_Minutes(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation"));
			Create_Multi_Bookings.SelectNoon_StartTime(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			extentTest.info("Enter time to create Third booking");
			logger.info("Time entered for creating Third booking...!");
			Thread.sleep(2000);


			// select per hour slot plan
			Create_Multi_Bookings.Select_Per_Hour_Slot();
			extentTest.info("Per hour/slot plan selected");
			logger.info("Per hour/slot plan selected...!");

			Update_Booking_Traffic.Select_One_Hour();
			extentTest.info("Select 1 hour ");
			logger.info("1 hour duration selected...!");

			for(int i=1; i<=10;  i++) 
			{
				EditTime.Select_Guest(driver); // select 10 slots

			}

			extentTest.info("10 Slots selected for booking");
			logger.info("10 Slots selected for booking...!");
			Thread.sleep(2000);


			BookingCreation.Click_ProceedToPay();
			extentTest.info("Click on Proceed to pay button");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(2000);

			CashPayment.Select_CashPymentMethod();
			extentTest.info("Select Cash payment method for booking");
			logger.info("Pay via Cash option selected for payment...!");
			Thread.sleep(2000);
			
			BookingCreation.Click_Tipcrossbtn();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			CashPayment.Click_ReceivedCheckBox();
			extentTest.info("Click payment Received Check Box");
			logger.info("Clicked on payment Received check box...!");
			Thread.sleep(2000);
			CashPayment.Click_ConfirmBtn();
			extentTest.info("Click Confirm Button");
			logger.info("Clicked on Confirm Button...!");
			Thread.sleep(5000);

			EditDate.Click_Submit();
			extentTest.info("Click Submit Button");
			logger.info("Click on submit button...!");
			Thread.sleep(2000);

			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']"))
					.isDisplayed(), true);


			extentTest.info(MarkupHelper.createLabel("Booking successfully created on Third slot slot...!", ExtentColor.ORANGE));
			System.out.println("Booking successfully created on third slot...!");
			logger.info("Booking successfully created on third slot...!");

			// create bookings on third slots

			BookingCreation.Customer_Name(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter ");
			Thread.sleep(2000);
			BookingCreation.EnterEmail(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter ");
			logger.info("User information are entered...!");

			BookingCreation.Click_CreateReservation();
			extentTest.info("Click Create Reservation button");
			logger.info("Clicked on create reservation button...!");

			//Enter time for booking/ booking Third slot i.e. 08 to 10 AM 
			EditTime.Edit_Time(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation"));
			Create_Multi_Bookings.Enter_Minutes(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation"));
			Create_Multi_Bookings.SelectNoon_StartTime(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			extentTest.info("Enter time to create Fourth bookings");
			logger.info("Time entered for creating Fourth booking ...!");
			Thread.sleep(2000);


			// select per hour slot plan
			Create_Multi_Bookings.Select_Per_Hour_Slot();
			extentTest.info("Per hour/slot plan selected");
			logger.info("Per hour/slot plan selected...!");

			Create_Multi_Bookings.Select_Two_Hour();
			extentTest.info("Select 2hour ");
			logger.info("2 hour duration selected...!");
			

			for(int i=1; i<=10;  i++) 
			{
				EditTime.Select_Guest(driver); // select 10 slots

			}

			extentTest.info("10 Slots selected for booking");
			logger.info("10 Slots selected for booking...!");
			Thread.sleep(2000);


			BookingCreation.Click_ProceedToPay();
			extentTest.info("Click on Proceed to pay button");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(2000);

			PayviaEmail.Select_EmailPymentMethod();
			extentTest.info("Select Email payment method for booking");
			logger.info("Pay via email option selected for payment...!");
			Thread.sleep(2000);
			
			BookingCreation.Click_Tipcrossbtn();
			extentTest.info("Click on tip cross button...!");
			logger.info("Clicked on tip cross button...!");
			Thread.sleep(2000);

			PayviaEmail.Click_SendLink(driver);
			extentTest.info("Link has been send successfully");
			logger.info("Link has been send successfully...!");
			Thread.sleep(3000);

			PayviaEmail.NavigateTO_YopMail(driver);
			extentTest.info("Navigated to yopmail.com");
			logger.info("Navigated to yopmail.com...!");
			Thread.sleep(3000);

			PayviaEmail.Entername_Login("dip");//hardcoded
			extentTest.info("Enter UN for yopmail");
			Thread.sleep(3000);
			PayviaEmail.Click_mailArrow();
			extentTest.info("Click on next arrow on yopmail");
			logger.info("Logged in into yop mail account...!");
			Thread.sleep(5000);


			// To handle iframe element
			driver.switchTo().frame("ifmail");

			PayviaEmail.LinkinMail();// click link inside mail
			extentTest.info("Click Link Given in mail");
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
			Paymentgetway.Click_Paybutton();
			logger.info("Clicked on pay button...!");
			Thread.sleep(3000);


			// need to handle iframe element
			driver.switchTo().frame("__teConnectSecureFrame");
			BookingCreation.EnterCardNo(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Enter Card No.");
			Thread.sleep(2000);
			BookingCreation.EnterExpiryDate(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Enter Expiry Date");
			Thread.sleep(2000);
			BookingCreation.Entercvc(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Enter CVC");
			logger.info("Card details entered successfully...!");
			//To switch focus of selenium on main page
			driver.switchTo().defaultContent();
			Thread.sleep(5000);
			
			Paymentgetway.Click_payon_PaymentPage();
			extentTest.info("Click pay on Payment Page");
			logger.info("Clicked on final pay button...!");

			extentTest.info(MarkupHelper.createLabel("Booking successfully created on fourth slot...!", ExtentColor.ORANGE));
			System.out.println("Booking successfully created on fourth slot...!");
			logger.info("Booking successfully created on fourth slot...!");

		}
		catch(Exception e) 
		{
			e.getStackTrace();
			String getCause = e.getLocalizedMessage();
			System.out.println("issue cause is :"+getCause);
			extentTest.fail(" TC017_CreateBooking_Traffic test case is Fail due to" + getCause);
			logger.error("TC017_CreateBooking_Traffic test case is fail due to " + getCause);
		}

		String  ActualResult=driver.findElement(By.xpath("//div[contains(@class,'BookingStatus_bill__status__nj')]")).getText();
		String ExpectedResult="Booking Successful";
		Assert.assertEquals(ActualResult, ExpectedResult);


		System.out.println("Booking traffic created on calander section...!");
		extentTest.info("Booking traffic created on calander section...!");
		logger.info("Booking traffic created on calander section...!");



	}


}
