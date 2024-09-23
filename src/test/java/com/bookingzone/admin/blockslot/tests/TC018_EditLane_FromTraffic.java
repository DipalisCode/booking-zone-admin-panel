package com.bookingzone.admin.blockslot.tests;

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
import com.bookingzone.admin.pages.A_Base_Class;
import com.bookingzone.admin.pages.Add_Service;
import com.bookingzone.admin.pages.Add_VenueDetails;
import com.bookingzone.admin.pages.Admin_LogOut;
import com.bookingzone.admin.pages.Block_slots;
import com.bookingzone.admin.pages.Booking_CashPayment;
import com.bookingzone.admin.pages.Bussiness_Login1;
import com.bookingzone.admin.pages.Create_Booking1_CardPay;
import com.bookingzone.admin.pages.Create_Bookings_traffic;
import com.bookingzone.admin.pages.Edit_Addon;
import com.bookingzone.admin.pages.Edit_Date;
import com.bookingzone.admin.pages.Edit_Lane;
import com.bookingzone.admin.pages.Edit_Time;
import com.bookingzone.admin.pages.Update_Booking_FromTraffic;
import com.bookingzone.admin.pages.UtilityClass;

public class TC018_EditLane_FromTraffic extends A_Base_Class
{
	// Initialise_Brouser();
	// Webdriver driver

	// create object logger class
	Logger logger = (Logger) LogManager.getLogger("TC018_EditLane_FromTraffic");

	Bussiness_Login1 Blogin;
	Create_Booking1_CardPay BookingCreation;
	Booking_CashPayment CashPayment;
	Edit_Date EditDate;
	Edit_Lane Edit_Lane;
	Edit_Time  EditTime;
	Create_Bookings_traffic Create_Multi_Bookings;
	Update_Booking_FromTraffic Update_Booking_Traffic;
	Booking_CashPayment CashPay;
	Edit_Addon Edit_Addon;
	Block_slots BlockSlot;

	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		Initialise_Browser(Context);
		logger.info("Browser opened...!");

		//Initialise_Brouser();

		// object of pom classes
		Blogin=new Bussiness_Login1(driver);
		BookingCreation=new Create_Booking1_CardPay(driver);
		CashPayment=new Booking_CashPayment(driver);
		EditDate=new Edit_Date(driver);
		Edit_Lane=new Edit_Lane(driver);
		EditTime=new Edit_Time(driver);
		Create_Multi_Bookings=new Create_Bookings_traffic(driver);
		Update_Booking_Traffic=new Update_Booking_FromTraffic(driver);
		CashPay=new Booking_CashPayment(driver);
		Edit_Addon=new Edit_Addon(driver);
		BlockSlot=new Block_slots(driver);
	}

	@BeforeMethod
	public void Login() throws InterruptedException, IOException 
	{

		Blogin.Click_Radio();
		Blogin.Enter_UN(UtilityClass.getdatafromPF("UN_Business"));
		Blogin.Enter_PWS(UtilityClass.getdatafromPF("PWS_Business"));
		Blogin.Click_Login();
		Thread.sleep(7000);
		logger.info("Login credentials are entered...!");

	}

	// User should able to Edit lane and create new booking on previous time  
	@Test  (testName = "Update_Lane_From_Traffic")
	public void Update_Lane_From_Traffic() throws IOException, InterruptedException 
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

			// select date on where booking traffic are created
			Update_Booking_Traffic.Click_CalanderSymbol_Homepage();
			extentTest.info("Click on calander symbol ");
			extentTest.info("Click on nextMonth Arrow on calander");
			BlockSlot.Select_Date();
			extentTest.info("Select Date for booking");
			logger.info("Date selected for booking...!");
			Thread.sleep(5000);


			//click created booking for update lane i.e, from lane 1--> lane 4
			Update_Booking_Traffic.Click_Booking_ToEdit_Lane();
			extentTest.info("Click Created booking for Edit");
			logger.info("Clicked on Created booking to edit time");
			Thread.sleep(5000);

			EditDate.Click_EditButton();
			extentTest.info("Click Edit Button");
			logger.info("Clicked on edit button...!");
			Thread.sleep(5000);


			Edit_Lane.Click_DefaultLane();//lane1
			extentTest.info("UnClick Default selected lane ");
			logger.info("Default selected lane unClicked...!");
			Thread.sleep(3000);

			Edit_Lane.selectNew_Lane();//lane2
			extentTest.info("Click New Lane ");
			logger.info("Select updated lane...!");
			Thread.sleep(3000);

			EditDate.Click_UpdateBooking();
			extentTest.info("Click Upbadate Booking Button");
			logger.info("Clicked on Update Booking Button...!");
			Thread.sleep(5000);

			EditDate.Click_ConfirmEdit();
			extentTest.info("Click Confirm Button");
			logger.info("Clicked on Confirm Button...!");

			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']"))
					.isDisplayed(), true);

			EditDate.Click_Submit();
			extentTest.info("Click Submit Button");
			logger.info("Click on submit Button...!");
			Thread.sleep(5000);

			extentTest.info(MarkupHelper.createLabel("Booking Lane updated successfully...!", ExtentColor.ORANGE));
			System.out.println("Booking Lane updated successfully...!");
			logger.info("Booking Lane updated successfully...!");


			//Create new booking on same time from which we have update the booking..

			BookingCreation.Customer_Name(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info(" Customer Name Enter ");
			Thread.sleep(2000);
			BookingCreation.EnterEmail(UtilityClass.getdatafromEs(1, "", "CreateBookings_CardPay"));
			extentTest.info("Customer Mail Enter ");
			logger.info("User information are entered...!");
			Thread.sleep(2000);

			BookingCreation.Click_CreateReservation();
			extentTest.info("Click Create Reservation button");
			logger.info("Clicked on create reservation button...!");
			Thread.sleep(2000);

			EditTime.Edit_Time(driver, UtilityClass.getdatafromEs(2, "", "Slot Blocking"));
			Create_Multi_Bookings.Enter_Minutes(driver, UtilityClass.getdatafromEs(1, "", "Slot Blocking"));
			Create_Multi_Bookings.SelectNoon_StartTime(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			Thread.sleep(2000);

			// select per hour slot plan
			Create_Multi_Bookings.Select_Per_Hour_Slot();
			extentTest.info("Per hour/slot plan selected");
			logger.info("Per hour/slot plan selected...!");
			Thread.sleep(2000);

			Create_Multi_Bookings.Select_Two_Hour();
			extentTest.info("Select 2hour ");
			logger.info("2 hour duration selected...!");
			Thread.sleep(2000);

			EditTime.Select_Guest(driver); 
			extentTest.info("Slot selected");
			logger.info("Slot selected...!");
			Thread.sleep(2000);


			BookingCreation.Click_ProceedToPay();
			extentTest.info("Click on Proceed to pay button");
			logger.info("Clicked on proceed to pay button...!");
			Thread.sleep(5000);

			BookingCreation.Select_CardPymentMethod();
			extentTest.info("Select Card payment method for booking");
			logger.info("Pay via Card option selected for payment...!");
			Thread.sleep(5000);
			
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

			// click on refresh button
			// click payment received checkbox
			// click conferm
			Edit_Addon.Click_RefreshBtn();
			extentTest.info("Click Refresh Button");
			logger.info("Clicked on refresh button to change the status...!");

			CashPay.Click_ReceivedCheckBox();
			extentTest.info("Click Payment Received checkbox");
			Thread.sleep(3000);
			logger.info("Clicked on Payment Received checkbox...!");

			CashPay.Click_ConfirmBtn();
			extentTest.info("Click Confirm button");
			logger.info("Clicked on confirm button...!");

			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']"))
					.isDisplayed(), true);

			Thread.sleep(3000);

			BookingCreation.Click_SubmitButton();
			extentTest.info("Click Submit Button");
			logger.info("Clicked on submit button...!");


			extentTest.info(MarkupHelper.createLabel("Booking created on previous lane successfully...!", ExtentColor.ORANGE));
			System.out.println("Booking created on previous lane successfully...!");
			logger.info("Booking created on previous lane successfully...!...!");


		}
		catch(Exception e) 
		{
			e.getStackTrace();
			String getCause = e.getLocalizedMessage();
			System.out.println("issue cause is :"+getCause);
			extentTest.fail("TC018_EditLane_FromTraffic test case is fail due to" + getCause);
			logger.error("TC018_EditLane_FromTraffic test case is fail due to " + getCause);
		}

		String  ActualResult=driver.findElement(By.xpath("//div[@id='notistack-snackbar']")).getText();
		String ExpectedResult="Booking status updated successfully";
		Assert.assertEquals(ActualResult, ExpectedResult);

	}





}
