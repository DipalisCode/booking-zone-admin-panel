package com.bookingzone.admin.utils;

import com.bookingzone.admin.pages.BusinessHomePage;
import com.bookingzone.admin.pages.CalendarReservationPage;
import com.bookingzone.admin.pages.CreateBookingStepperDetailsPage;
import com.bookingzone.admin.pages.SearchBookingPage;

public class BookingActions {

	private BusinessHomePage businessHomePage;
	private CalendarReservationPage calendarReservationPage;
	private CreateBookingStepperDetailsPage createBkgStepperDetailsPage;
	SearchBookingPage searchBookingPage;


	public BookingActions(BusinessHomePage businessHomePage, CalendarReservationPage calendarReservationPage,
			CreateBookingStepperDetailsPage createBkgStepperDetailsPage, SearchBookingPage searchBookingPage) {
		this.businessHomePage = businessHomePage;
		this.calendarReservationPage = calendarReservationPage;
		this.createBkgStepperDetailsPage = createBkgStepperDetailsPage;
		this.searchBookingPage= searchBookingPage;
	}

	public void configure_EnterBookingDetails(String outletName, String bookingName, String email) throws InterruptedException
	{
		businessHomePage.clickCalendarButton();
		calendarReservationPage.selectOutlet(outletName);
		calendarReservationPage.enterCustomerName(bookingName);
		calendarReservationPage.enterEmail(email);
		calendarReservationPage.clickCreateReservationButton();

	}



	public void proceedWithBookingDetails(String planName, String bookingDate) throws InterruptedException {
		createBkgStepperDetailsPage.clickCalendarSymbol();
		createBkgStepperDetailsPage.clickNextArrow();
		createBkgStepperDetailsPage.selectDate(bookingDate);
		createBkgStepperDetailsPage.selectPlan(planName);
		createBkgStepperDetailsPage.selectReservationSlot();
		createBkgStepperDetailsPage.selectPackage();
		createBkgStepperDetailsPage.selectAddGuest();
		createBkgStepperDetailsPage.clickProceedToPayButton();

	}

	public void searchBooking(String BookingId) throws InterruptedException 
	{
		calendarReservationPage.clickViewReservationButton();
		searchBookingPage.enterBookingId(BookingId);
		searchBookingPage.clickBookingArrow();
	}

}
