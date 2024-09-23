package com.bookingzone.admin.FullPayment;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzone.admin.pages.BaseClass;
import com.bookingzone.admin.pages.BusinessHomePage;
import com.bookingzone.admin.pages.CalendarReservationPage;
import com.bookingzone.admin.pages.CreateBookingStepperDetailsPage;
import com.bookingzone.admin.pages.StepperPaymentPage;
import com.bookingzone.admin.pages.StepperSummaryPage;
import com.bookingzone.admin.utils.BookingActions;
import com.bookingzone.admin.utils.BookingIdStore;
import com.bookingzone.admin.utils.ExtentReportManager;
import com.bookingzone.admin.utils.PaymentActions;
import com.bookingzone.admin.utils.UtilityClass;

public class TC001FullCardPayment extends BaseClass {


	BookingActions bookingActions;
	PaymentActions paymentActions;
	StepperSummaryPage stepperSummaryPage;

	// Test data variables
	private String outletName;
	private String bookingName;
	private String customerEmail;
	private String planName;
	private String bookingDate;
	private String paymentMethod;
	private String cardNumber;
	private String expiryDate;
	private String cvc;
	private String status;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, IOException {
		bookingActions = new BookingActions(
				new BusinessHomePage(driver), 
				new CalendarReservationPage(driver),
				new CreateBookingStepperDetailsPage(driver), 
				null
				);

		paymentActions = new PaymentActions(
				new StepperPaymentPage(driver),
				new StepperSummaryPage(driver)
				);

		stepperSummaryPage = new StepperSummaryPage(driver);

		fetchBookingAndPaymentData();
	}

	private void fetchBookingAndPaymentData() throws IOException {
		outletName = UtilityClass.getDataFromEs(1, "Outlet Name", "Create_Booking");
		bookingName = UtilityClass.getDataFromEs(1, "Booking Name", "Create_Booking");
		customerEmail = UtilityClass.getDataFromEs(1, "Email", "Create_Booking");
		planName = UtilityClass.getDataFromEs(1, "Plan Name", "Create_Plans");
		bookingDate = UtilityClass.getDataFromEs(1, "Booking Date", "Create_Booking");

		paymentMethod = UtilityClass.getDataFromEs(1, "Payment Method", "Create_Booking");
		cardNumber = UtilityClass.getDataFromEs(1, "Card No", "Create_Booking");
		expiryDate = UtilityClass.getDataFromEs(1, "Expiry Date", "Create_Booking");
		cvc = UtilityClass.getDataFromEs(1, "CVC", "Create_Booking");
		status=UtilityClass.getDataFromEs(1, "Status_Successful", "EditBooking");
	}

	@Test(testName = "testCreateBookingWithCardPayment", priority = 1)
	public void testCreateBookingWithCardPayment() throws IOException, InterruptedException {
		try {

			bookingActions.configure_EnterBookingDetails(outletName, bookingName, customerEmail);
			bookingActions.proceedWithBookingDetails(planName, bookingDate);
			paymentActions.verifyTotal_SubTotalAmount();
			paymentActions.completePayment(driver, paymentMethod, cardNumber, expiryDate, cvc);

			String bookingId = paymentActions.getBookingId();
			// Store the bookingId using the Singleton class
			BookingIdStore.setBookingId(bookingId);
			ExtentReportManager.logInfo("Booking created successfully with ID: " + bookingId);

			verifyBookingSuccess();
		} catch (Exception e) {
			handleException(e, "testCreateBookingWithCardPayment");
		}
	}

	private void verifyBookingSuccess() throws IOException, InterruptedException {
		if (driver.getPageSource().contains("There is some error in receiver's bank or outlet is not taking online payment.")) {
			ExtentReportManager.logFail("There is some error in receiver's bank or outlet is not taking online payment.");
			Assert.fail("There is some error in receiver's bank or outlet is not taking online payment.");
		} else {
			stepperSummaryPage.verifyBookingStatus(status);
			stepperSummaryPage.clickSubmitButton();
			ExtentReportManager.logInfo("Booking created successfully by Card payment.");
		}
	}

	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
