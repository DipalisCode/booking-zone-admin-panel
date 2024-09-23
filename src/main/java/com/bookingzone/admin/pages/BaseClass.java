package com.bookingzone.admin.pages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.bookingzone.admin.utils.CreateBookingCommonActions;
import com.bookingzone.admin.utils.ExtentReportManager;
import com.bookingzone.admin.utils.UtilityClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains the base setup and teardown methods for Selenium tests along with ExtentReports integration.
 */
public class BaseClass {
	public Logger logger = LogManager.getLogger(BaseClass.class);
	public static WebDriver driver;
	public static String screenshotsSubFolderName;
	private String username;
	private String password;
	private String userType;

	private static boolean isLoggedIn = false;

	/**
	 * Initializes the browser instance and sets up the ExtentReports configuration.
	 * 
	 * @param context The TestNG context for accessing test parameters.
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 * @throws EncryptedDocumentException If there is an error with encrypted documents.
	 * @throws IOException If an I/O error occurs.
	 */
	@BeforeSuite
	public void initialiseBrowser(ITestContext context) throws InterruptedException, EncryptedDocumentException, IOException {
		// Initialize ExtentReports before anything else
		ExtentReportManager.initializeExtentReports("BZ Tests Report");

		if (driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("start-maximized");
			options.addArguments("test-type");
			options.addArguments("disable-notifications");

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			Thread.sleep(2000);

			driver.get(UtilityClass.getDataFromEs(1, "Bussiness URL", "APK_URL"));
			driver.manage().window().maximize();

			Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
			String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion().split("\\.")[0];

			String author = context.getCurrentXmlTest().getParameter("author");
			ExtentTest test = ExtentReportManager.startTest(context.getName()); 
		test.assignAuthor(author).assignDevice(device);  
		ExtentReportManager.setTest(test);  
		}

		fetchLoginData(context);
		performLoginOnce(userType);
	}


	/**
	 * Sets up the ExtentReport for each test method.
	 * 
	 * @param method The test method being executed.
	 */
	@BeforeMethod
	public void setupTest(Method method) {
		// Set meaningful names for your individual test cases here
		String testName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
		ExtentTest test = ExtentReportManager.startTest(testName);  // Start a new test with a more detailed name
		ExtentReportManager.setTest(test);  // Set this as the current test
		logger.info("Starting test: " + testName);
	}

	private void fetchLoginData(ITestContext context) throws IOException {
	     userType = context.getCurrentXmlTest().getParameter("userType");
	    
	    switch (userType) {
	        case "business":
	            username = UtilityClass.getDataFromEs(1, "Email", "Business_Data");
	            password = UtilityClass.getDataFromEs(1, "Bussiness Password", "Business_Data");
	            break;
	        case "admin":
	            username = UtilityClass.getDataFromPF("Admin_UserName");
	            password = UtilityClass.getDataFromPF("Admin_Password");
	            break;
	        // Add more cases for other roles (e.g., Manager)
	    }
	}

  private void performLoginOnce(String role) throws IOException, InterruptedException {
	    if (!isLoggedIn) {
	        CreateBookingCommonActions.performLogin(role, driver, username, password);
	        isLoggedIn = true;
	        logger.info(role + " login performed successfully.");
	    }}
	/**
	 * Checks the test result status and captures a screenshot if the test fails.
	 * 
	 * @param result The result of the test execution.
	 * @throws IOException If an I/O error occurs during screenshot capture.
	 */
	@AfterMethod
	public void checkStatusAndCaptureScreenshot(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				String screenshotPath = null;
				String localSystemValue = UtilityClass.getDataFromPF("System").trim();

				if (localSystemValue.equalsIgnoreCase("local")) {
					screenshotPath = captureScreenshot(result.getName() + ".jpg");
					logger.info("Screenshot captured successfully from local");
				} else {
					LocalDateTime now = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					screenshotsSubFolderName = now.format(formatter);
					captureScreenshot(result.getName() + ".jpg");
					logger.info("Screenshot captured successfully from Jenkins");
					screenshotPath = UtilityClass.getDataFromPF("remoteReportPath") + screenshotsSubFolderName
							+ result.getName() + ".jpg";
				}
				ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath, "Screenshot of Failure");
				logger.info("ScreenshotPath: " + screenshotPath);
				ExtentReportManager.getTest().fail(result.getThrowable());
			} catch (Exception ex) {
				ExtentReportManager.logFail("Exception during capturing screenshot: " + ex.getMessage());
			}
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentReportManager.logPass(result.getMethod().getMethodName() + " is passed");
		}
	}

	/**
	 * Captures a screenshot of the current browser window.
	 * 
	 * @param fileName The name of the screenshot file.
	 * @return The file path of the captured screenshot.
	 * @throws IOException If an I/O error occurs during screenshot capture.
	 */
	public String captureScreenshot(String fileName) throws IOException {
		if (screenshotsSubFolderName == null) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			screenshotsSubFolderName = now.format(formatter);
		}

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File("." + File.separator + "Screenshots" + File.separator + screenshotsSubFolderName
				+ fileName);

		FileUtils.copyFile(sourceFile, destFile);
		ExtentReportManager.logWarning("Screenshot saved successfully: " + destFile.getCanonicalPath().substring(2));
		return destFile.getCanonicalPath().substring(2);
	}

	/**
	 * Closes the browser after test execution and logs the closure.
	 * Added a shutdown hook to ensure that Extent Reports are flushed and the browser is closed properly.
	 */
	@AfterSuite
	public void teardownSuite() {
		System.out.println("Teardown started...");
		cleanupResources();
	}

	// Method to handle resource cleanup, called from @AfterSuite and shutdown hook
	private void cleanupResources() {
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
				ExtentReportManager.logInfo("Browser closed");
				System.out.println("Browser closed.");
			}
			// Flush ExtentReports after all tests are complete
			ExtentReportManager.flushReports();
			System.out.println("Reports flushed.");
		} catch (Exception e) {
			ExtentReportManager.logFail("Error during cleanup: " + e.getMessage()+ e);
		}
	}

	// Constructor to add a shutdown hook to ensure browser closure and report flushing in case AfterSuite is skipped
	public BaseClass() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Shutdown hook triggered.");
			cleanupResources();
		}));
	}
}
