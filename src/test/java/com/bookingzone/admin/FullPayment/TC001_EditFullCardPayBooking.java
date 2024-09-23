package com.bookingzone.admin.FullPayment;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzone.admin.pages.BaseClass;
import com.bookingzone.admin.pages.BusinessHomePage;
import com.bookingzone.admin.pages.CalendarReservationPage;
import com.bookingzone.admin.pages.CreateBookingStepperDetailsPage;
import com.bookingzone.admin.pages.SearchBookingPage;
import com.bookingzone.admin.pages.StepperEditPage;
import com.bookingzone.admin.pages.StepperPaymentPage;
import com.bookingzone.admin.pages.StepperSummaryPage;
import com.bookingzone.admin.utils.BookingActions;
import com.bookingzone.admin.utils.BookingIdStore;
import com.bookingzone.admin.utils.ExtentReportManager;
import com.bookingzone.admin.utils.PaymentActions;
import com.bookingzone.admin.utils.UtilityClass;

public class TC001_EditFullCardPayBooking extends BaseClass {

	BookingActions bookingActions;
	PaymentActions paymentActions;
	StepperSummaryPage stepperSummaryPage;
	SearchBookingPage searchBookingPage;
	StepperPaymentPage stepperPaymentPage;
	StepperEditPage stepperEditPage;
	CreateBookingStepperDetailsPage createBookingStepperDetailsPage;

	// Test Data
	private String statusInProgress;
	private String statusSuccess;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, IOException {
		bookingActions = new BookingActions(
				new BusinessHomePage(driver), 
				new CalendarReservationPage(driver),
				new CreateBookingStepperDetailsPage(driver), 
				new SearchBookingPage(driver)
				);

		paymentActions = new PaymentActions(
				new StepperPaymentPage(driver),
				new StepperSummaryPage(driver)
				);

		stepperSummaryPage = new StepperSummaryPage(driver);
		stepperEditPage=new StepperEditPage(driver);
		createBookingStepperDetailsPage=new CreateBookingStepperDetailsPage(driver);

		fetchTestData();
	}

	private void fetchTestData() throws IOException {

		statusInProgress = UtilityClass.getDataFromEs(1, "Status_In Progress", "EditBooking");
		statusSuccess = UtilityClass.getDataFromEs(1, "Status_Successful", "EditBooking");
	}

	@Test(testName = "testChangeBookingStatusToInProgress", priority = 2, dependsOnMethods = "com.bookingzone.admin.FullPayment.TC001FullCardPayment.testCreateBookingWithCardPayment")
	public void testChangeBookingStatusToInProgress() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				performChangeBookingStatus(bookingId, statusInProgress);
			}
		} catch (Exception e) {
			handleException(e, "testChangeBookingStatusToInProgress");
		}
	}

	@Test(testName = "testAddPackage_AdditionalGuest", priority = 3, dependsOnMethods = "com.bookingzone.admin.FullPayment.TC001FullCardPayment.testCreateBookingWithCardPayment")
	public void testAddPackage_AdditionalGuest() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				performAddPackageAndGuest(bookingId);
			}
		} catch (Exception e) {
			handleException(e, "testAddPackage_AdditionalGuest");
		}
	}

	private void performChangeBookingStatus(String bookingId, String status) throws IOException, InterruptedException {
		bookingActions.searchBooking(bookingId);
		stepperSummaryPage.changeStatus(status);
		stepperSummaryPage.clickSubmitButton();
		stepperSummaryPage.clickstatusConfirmationButton();
		ExtentReportManager.logInfo("Booking status changed to: " + status);
	}

	private void performAddPackageAndGuest(String bookingId) throws IOException, InterruptedException, TimeoutException {
		bookingActions.searchBooking(bookingId);
		verifyBookingStatus(statusInProgress);

		editBookingToAddGuest();
		stepperEditPage.clickUpdateBookingButton();
		stepperEditPage.clickConfirmEditButton();
		
		// Verify payment and finalize
		paymentActions.verifyTotal_SubTotalAmount();
		
		paymentActions.completePayment(driver, null, null, null, null);
		verifyBookingStatus(statusSuccess);
		submitBooking();
	}

	private void editBookingToAddGuest() throws InterruptedException {
		stepperEditPage.clickEditButton();
		createBookingStepperDetailsPage.selectPackage();
		createBookingStepperDetailsPage.selectAddGuest();
	}

	private void verifyBookingStatus(String status) {
		stepperSummaryPage.verifyBookingStatus(status);
	}

	private void submitBooking() throws InterruptedException {
		stepperSummaryPage.clickSubmitButton();
		ExtentReportManager.logInfo("Booking updated successfully...!");
	}



	private boolean isValidBookingId(String bookingId) {
		if (bookingId == null || bookingId.isEmpty()) {
			ExtentReportManager.logInfo("Booking ID not found.");
			Assert.fail("Booking ID not found.");
			return false;
		}
		return true;
	}
	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
