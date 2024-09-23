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
import com.bookingzone.admin.pages.StepperPaymentPage;
import com.bookingzone.admin.pages.StepperSummaryPage;
import com.bookingzone.admin.utils.UtilityClass;


public class TC006BookingpaymentViaCash extends BaseClass {
 Logger logger = (Logger) LogManager.getLogger(this.getClass());

 LogInPage login;
 BusinessHomePage businessHomePage;
 CalendarReservationPage calendarReservationPage;
 CreateBookingStepperDetailsPage createBkgStepperDetailsPage;
 StepperPaymentPage stepperPaymentPage;
 StepperSummaryPage stepperSummaryPage;

 @BeforeClass
 public void initializeTest(ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException {
     initialiseBrowser(Context);
     logger.info("Browser Opened...!");

     // Initialize page objects
     login = new LogInPage(driver);
     businessHomePage = new BusinessHomePage(driver);
     calendarReservationPage = new CalendarReservationPage();
     createBkgStepperDetailsPage = new CreateBookingStepperDetailsPage();
     stepperPaymentPage = new StepperPaymentPage();
     stepperSummaryPage = new StepperSummaryPage();
 }

 @BeforeMethod
 public void Login() throws InterruptedException, IOException {
     // Perform login with valid credentials
     login.clickBusinessRadioButton();
     login.enterUsername(UtilityClass.getDataFromEs(1, "Email", "Business_Data"));
		login.enterPassword(UtilityClass.getDataFromEs(1, "Bussiness Password", "Business_Data"));
     login.clickLoginButton();
     Thread.sleep(5000);
     logger.info("Login credentials are entered...!");
 }

 // Create booking and pay via cash
 @Test(testName = "testCreateBookingPayVaiCash")
 public void testCreateBookingPayVaiCash() throws IOException, InterruptedException {
     try {
         // Navigate to calendar view
         businessHomePage.clickCalendarButton();
         extentTest.info("Click Calendar button...!");
         logger.info("Clicked on calendar button...!");
         Thread.sleep(2000);

         // Select outlet
         calendarReservationPage.selectOutlet(driver, null);
         extentTest.info("Outlet selected...!");
         logger.info("Outlet selected...!");
         Thread.sleep(2000);

         // Enter customer details
         calendarReservationPage.enterCustomerName(UtilityClass.getDataFromEs(1, "Booking Name", "CreateBookings_CardPay"));
         extentTest.info("Customer Name Enter...!");
         Thread.sleep(2000);
         calendarReservationPage.enterEmail(UtilityClass.getDataFromEs(1, "Email", "CreateBookings_CardPay"));
         extentTest.info("Customer Mail Enter...!");
         logger.info("User information entered...!");
         Thread.sleep(2000);

         // Create reservation
         calendarReservationPage.clickCreateReservationButton();
         extentTest.info("Click Create Reservation button...!");
         logger.info("Clicked on create reservation button...!");
         Thread.sleep(2000);

         // Proceed with booking details
         createBkgStepperDetailsPage.clickCalendarSymbol();
         extentTest.info("Click on calendar symbol...!");
         createBkgStepperDetailsPage.clickNextArrow();
         extentTest.info("Click on nextMonth Arrow on calendar...!");
         createBkgStepperDetailsPage.selectDate();
         extentTest.info("Select Date for booking...!");
         logger.info("Date selected for booking...!");
         Thread.sleep(3000);

         createBkgStepperDetailsPage.selectPlan();
         extentTest.info("Select Plan for booking...!");
         logger.info("Package type plan selected for booking...!");
         Thread.sleep(2000);

         createBkgStepperDetailsPage.selectReservationSlot();
         extentTest.info("Select Duration for booking...!");
         Thread.sleep(5000);
         createBkgStepperDetailsPage.selectPackage(driver);
         extentTest.info("Select Package for booking...!");
         Thread.sleep(3000);
         createBkgStepperDetailsPage.selectAddGuest(driver);
         extentTest.info("Select additional Guest for booking...!");
         logger.info("Package and additional guests selected...!");
         Thread.sleep(3000);

         createBkgStepperDetailsPage.clickProceedToPayButton();
         extentTest.info("Click on Proceed to pay button...!");
         logger.info("Clicked on proceed to pay button...!");
         Thread.sleep(3000);

         // Payment via cash
         stepperPaymentPage.selectCashPaymentMethod();
         extentTest.info("Select Cash payment method for booking...!");
         logger.info("Pay via Cash option selected for payment...!");
         Thread.sleep(3000);

         stepperPaymentPage.clickTipCrossButton();
         extentTest.info("Click on tip cross button...!");
         logger.info("Clicked on tip cross button...!");
         Thread.sleep(2000);

         stepperPaymentPage.clickReceivedCheckBox();
         extentTest.info("Click payment Received Check Box...!");
         logger.info("Clicked on payment Received check box...!");
         Thread.sleep(2000);

         stepperPaymentPage.clickConfirmButton();
         extentTest.info("Click Confirm Button...!");
         logger.info("Clicked on Confirm Button...!");
         Thread.sleep(3000);

         // Get booking status and assert
         String status = stepperSummaryPage.verifyBookingStatus();
         Assert.assertEquals(status, "Successful", "Status of the booking is");
         extentTest.info(MarkupHelper.createLabel("Booking status assertion is passed...!",
                 ExtentColor.GREEN));
         System.out.println("Booking status assertion is passed...!");
         logger.info("Booking status assertion is passed...!");

         stepperSummaryPage.clickSubmitButton();
         extentTest.info("Click Submit Button...!");
         logger.info("Clicked on submit button...!");

         System.out.println("Booking is successful by cash payment...!");
         extentTest.info(MarkupHelper.createLabel("Booking is successful by cash payment", ExtentColor.ORANGE));
         logger.info("Booking is successful by cash payment...!");

     } catch (Exception e) {
         handleException(e);
     }
 }

 // Method to handle exceptions and log error messages
 public void handleException(Exception e) {
     e.printStackTrace();
     String getCause = e.getLocalizedMessage();
     System.out.println("Issue cause is :" + getCause);
     extentTest.fail(" testCreateBookingPayVaiCash test case is fail due to" + getCause);
     logger.error("testCreateBookingPayVaiCash test case is fail due to " + getCause);
     
     Assert.fail("Test case failed due to exception: " + getCause);
 }
}


