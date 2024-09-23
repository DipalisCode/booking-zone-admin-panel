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
import com.bookingzone.admin.pages.Edit_Date;
import com.bookingzone.admin.pages.Edit_Time;
import com.bookingzone.admin.pages.Mail_Paymentgateway;
import com.bookingzone.admin.pages.Update_Booking_FromTraffic;
import com.bookingzone.admin.pages.UtilityClass;

public class TC016_Block_MultipleSlots_1_By_1 extends A_Base_Class
{
	// Initialise_Brouser();
	// Webdriver driver

	//Create object of logger class

	Logger logger=(Logger) LogManager.getLogger("TC016_Block_MultipleSlots_1_By_1");

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

	// Block multiple slots 
	@Test (testName = "Block Multiple Slots")
	public void Block_MultipleSlots() throws IOException, InterruptedException 
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

			// block First slots
			BlockSlot.Click_ViewBlockSlots();
			extentTest.info("Click on view blocked slot option");
			logger.info("Clicked on view blocked slot option...!");
			Thread.sleep(2000);

			BlockSlot.Select_BlockMultipleSlots();
			extentTest.info("Click on block multiple slots radio button");
			logger.info("Clicked on block multiple slots radio button...!");
			Thread.sleep(2000);

			//Block first slot
			BlockSlot.Select_Service_slot1();
			extentTest.info("Service selected for slot1...");
			logger.info("Service selected for slot1...!");
			Thread.sleep(2000);
			BlockSlot.Select_Resource_Slot1();
			extentTest.info("Resource selected for slot1...");
			logger.info("Resource selected for slot1...!");
			Thread.sleep(2000);
			BlockSlot.Select_ResourceFrom_Slot1();
			Thread.sleep(2000);
			BlockSlot.Select_ResourceTo_Slot1();
			extentTest.info("Slot1 selected for blocking...");
			logger.info("Slot1 selected for blocking...!");
			Thread.sleep(2000);
			BlockSlot.Click_Calandersymbol_Slot1();
			Thread.sleep(2000);
			BlockSlot.Click_NextMonth();
			Thread.sleep(2000);
			BlockSlot.Select_Date();
			extentTest.info("Date selected for blocking slot1...!");
			logger.info("Date selected for blocking slot1...!");
			Thread.sleep(2000);

			//select start time
			EditTime.Edit_Time(driver, UtilityClass.getdatafromEs(1, "",  "Slot Blocking"));
			Thread.sleep(2000);
			Create_Multi_Bookings.Enter_Minutes(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation"));
			Thread.sleep(2000);
			Create_Multi_Bookings.SelectNoon_StartTime(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			Thread.sleep(2000);
			extentTest.info("Start time selected for slot1...");
			logger.info("Start time selected for slot1...!");

			//select end time
			BlockSlot.Enter_EndTime_Slot1(driver, UtilityClass.getdatafromEs(1, "",  "Slot Blocking"));
			Thread.sleep(2000);
			BlockSlot.EnterMinutes_EntTime_Slot1(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation"));
			Thread.sleep(2000);
			BlockSlot.Select_AMEndTime_Slot1(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			extentTest.info("End time selected for slot1...");
			logger.info("End time selected for slot1...!");

			Thread.sleep(2000);

			BlockSlot.Click_AddMore();
			extentTest.info("Clicked on add more button to block slot2...");
			logger.info("Clicked on add more button to block slot2...!");

			Thread.sleep(2000);

			//Block second slot
			BlockSlot.Select_Service_slot2();
			extentTest.info("Service selected for slot2...");
			logger.info("Service selected for slot2...!");
			Thread.sleep(2000);
			BlockSlot.Select_Resource_Slot2();
			extentTest.info("Resource selected for slot2...");
			logger.info("Resource selected for slot2...!");
			Thread.sleep(2000);
			BlockSlot.Select_ResourceFrom_Slot2();
			Thread.sleep(2000);
			BlockSlot.Select_ResourceTo_Slot2();
			extentTest.info("Slot2 selected for blocking...");
			logger.info("Slot2 selected for blocking...!");
			Thread.sleep(2000);
			BlockSlot.Click_Calandersymbol_Slot2();
			Thread.sleep(2000);
			BlockSlot.Click_NextMonth();
			Thread.sleep(2000);
			BlockSlot.Select_Date();
			extentTest.info("Date selected for blocking slot2...!");
			logger.info("Date selected for blocking slot2...!");
			Thread.sleep(2000);

			//Start time
			BlockSlot.Enter_StartTime_Slot2(driver, UtilityClass.getdatafromEs(1, "",  "Slot Blocking"));
			Thread.sleep(3000);
			BlockSlot.EnterMinutes_StartTime_Slot2(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation") );
			Thread.sleep(2000);
			BlockSlot.SelectAM_StartTime_Slot2(driver, UtilityClass.getdatafromEs(1, "", "EditBooking") );
			Thread.sleep(2000);
			extentTest.info("Start time selected for slot2...");
			logger.info("Start time selected for slot2...!");

			//End time
			BlockSlot.Enter_EndTime_Slot2(driver,UtilityClass.getdatafromEs(1, "",  "Slot Blocking") );
			Thread.sleep(2000);
			BlockSlot.EnterMinutes_EntTime_Slot2(driver, UtilityClass.getdatafromEs(1, "", "multiple Booking creation"));
			Thread.sleep(2000);
			BlockSlot.SelectAM_EndTime_Slot2(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			extentTest.info("End time selected for slot2...");
			logger.info("End time selected for slot2...!");
			Thread.sleep(2000);

			BlockSlot.Click_AddMore();
			Thread.sleep(2000);
			extentTest.info("Clicked on add more button to block slot3...");
			logger.info("Clicked on add more button to block slot3...!");

			//Block third slot
			BlockSlot.Select_Service_slot3();
			extentTest.info("Service selected for slot3...");
			logger.info("Service selected for slot3...!");
			Thread.sleep(2000);
			BlockSlot.Select_Resource_Slot3();
			extentTest.info("Resource selected for slot3...");
			logger.info("Resource selected for slot3...!");
			Thread.sleep(2000);
			BlockSlot.Select_ResourceFrom_Slot3();
			Thread.sleep(2000);
			BlockSlot.Select_ResourceTo_Slot3();
			extentTest.info("Slot3 selected for blocking...");
			logger.info("Slot3 selected for blocking...!");
			Thread.sleep(2000);
			BlockSlot.Click_Calandersymbol_Slot3();
			Thread.sleep(2000);
			BlockSlot.Click_NextMonth();
			Thread.sleep(2000);
			BlockSlot.Select_Date();
			extentTest.info("Date selected for blocking slot3...!");
			logger.info("Date selected for blocking slot3...!");
			Thread.sleep(2000);

			//Start time
			BlockSlot.Enter_StartTime_Slot3(driver, UtilityClass.getdatafromEs(2, "",  "Slot Blocking"));
			Thread.sleep(2000);
			BlockSlot.EnterMinutes_StartTime_Slot3(driver, UtilityClass.getdatafromEs(1, "", "Slot Blocking") );
			Thread.sleep(2000);
			BlockSlot.SelectAM_StartTime_Slot3(driver, UtilityClass.getdatafromEs(1, "", "EditBooking") );
			Thread.sleep(2000);
			extentTest.info("Start time selected for slot3...");
			logger.info("Start time selected for slot3...!");

			//End time
			BlockSlot.Enter_EndTime_Slot3(driver,UtilityClass.getdatafromEs(2, "",  "Slot Blocking") );
			Thread.sleep(2000);
			BlockSlot.EnterMinutes_EntTime_Slot3(driver, UtilityClass.getdatafromEs(1, "", "Slot Blocking"));
			Thread.sleep(2000);
			BlockSlot.SelectAM_EndTime_Slot3(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			extentTest.info("End time selected for slot3...");
			logger.info("End time selected for slot3...!");
			Thread.sleep(2000);

			BlockSlot.Click_AddMore();
			Thread.sleep(2000);
			extentTest.info("Clicked on add more button to block slot4...");
			logger.info("Clicked on add more button to block slot4...!");

			//Block fourth slot

			BlockSlot.Select_Service_slot4();
			extentTest.info("Service selected for slot4...");
			logger.info("Service selected for slot4...!");
			Thread.sleep(2000);
			BlockSlot.Select_Resource_Slot4();
			extentTest.info("Resource selected for slot4...");
			logger.info("Resource selected for slot1...4");
			Thread.sleep(2000);
			BlockSlot.Select_ResourceFrom_Slot4();
			Thread.sleep(2000);
			BlockSlot.Select_ResourceTo_Slot4();
			extentTest.info("Slot4 selected for blocking...");
			logger.info("Slot4 selected for blocking...!");
			Thread.sleep(2000);
			BlockSlot.Click_Calandersymbol_Slot4();
			Thread.sleep(2000);
			BlockSlot.Click_NextMonth();
			Thread.sleep(2000);
			BlockSlot.Select_Date();
			extentTest.info("Date selected for blocking slot4...!");
			logger.info("Date selected for blocking slot4...!");
			Thread.sleep(2000);

			//Start time
			BlockSlot.Enter_StartTime_Slot4(driver, UtilityClass.getdatafromEs(2, "",  "Slot Blocking"));
			Thread.sleep(2000);
			BlockSlot.EnterMinutes_StartTimeSlot4(driver, UtilityClass.getdatafromEs("", 3, "Slot Blocking") );
			Thread.sleep(2000);
			BlockSlot.SelectAMStartTimeSlot4(driver, UtilityClass.getdatafromEs(1, 2, "EditBooking") );
			Thread.sleep(2000);
			extentTest.info("Start time selected for slot4...");
			logger.info("Start time selected for slot4...!");

			//End time
			BlockSlot.Enter_EndTime_Slot4(driver,UtilityClass.getdatafromEs(2, "",  "Slot Blocking") );
			Thread.sleep(2000);
			BlockSlot.EnterMinutes_EntTime_Slot4(driver, UtilityClass.getdatafromEs(1, "", "Slot Blocking"));
			Thread.sleep(2000);
			BlockSlot.SelectAM_EndTime_Slot4(driver, UtilityClass.getdatafromEs(1, "", "EditBooking"));
			Thread.sleep(2000);
			extentTest.info("End time selected for slot4...");
			logger.info("End time selected for slot4...!");
			Thread.sleep(3000);

			BlockSlot.Click_SaveButton();
			Thread.sleep(5000);
			extentTest.info("Clicked on save button...");
			logger.info("Clicked on save button...!");

			Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']"))
					.isDisplayed(), true);

			BlockSlot.Click_CrossSign(driver);
			Thread.sleep(2000);
			extentTest.info("clicked on cross sign...");
			logger.info("clicked on cross sign...!");

			extentTest.info(MarkupHelper.createLabel("Multiple slots blocked successfully...!", ExtentColor.ORANGE));
			System.out.println("Multiple slots blocked successfully...!");
			logger.info("Multiple slots blocked successfully...!");



		}
		catch(Exception e) 
		{
			e.getStackTrace();
			String getCause = e.getLocalizedMessage();
			System.out.println("issue cause is :"+getCause);
			extentTest.fail("TC016_Block_MultipleSlots_1_By_1 test case is fail due to" + getCause);
			logger.error("TC016_Block_MultipleSlots_1_By_1 test case is fail due to " + getCause);
		}

		String  ActualResult=driver.findElement(By.xpath("//div[@id='notistack-snackbar']")).getText();
		String ExpectedResult="Resources Blocked";
		Assert.assertEquals(ActualResult, ExpectedResult);





	}


}
