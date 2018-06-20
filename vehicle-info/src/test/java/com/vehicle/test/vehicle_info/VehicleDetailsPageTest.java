package com.vehicle.test.vehicle_info;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/** Test class which invokes the url and asserts that make and color of the vehicles matches the expected 
 * 
 * @author chandrans1
 *
 */
public class VehicleDetailsPageTest {
	
	private final static Logger logger = Logger.getLogger(VehicleDetailsPageTest.class.getName());

	/**
	 * Source folder location for vehicles to test.
	 */
	private final String sourceFolderLocation = "C:\\temp\\vehicle-details";
	
	/**
	 * Firefox driver location
	 */
	private final String geckoDriverLocation = "D:\\Downloads\\geckodriver-v0.21.0-win64\\geckodriver.exe";
	
	/**
	 * Actual url to test.
	 */
	private final String urlLocation = "https://www.gov.uk/get-vehicle-information-from-dvla";
	
	/**
	 * Xpath used to get make information for the vehicle.
	 */
	public static final String xpathForMake = "//*[@id='pr3']/div/ul/li[2]/span[2]/strong";
	
	/**
	 * Xpath used to get color information for the vehicle.
	 */
	public static final String xpathForColor = "//*[@id='pr3']/div/ul/li[3]/span[2]/strong";

	/**
	 * For asserting details from csv files
	 */
	private AssertVehicleDetailsFromCsv assertCsv;

	/**
	 * For asserting details from excel files
	 */
	private AssertVehicleDetailsFromExcel assertExcel;
	
	@Before
	public void beforeEachTest(){
		assertCsv = new AssertVehicleDetailsFromCsv();
		assertExcel = new AssertVehicleDetailsFromExcel();
	}

	@Test
	public void vehicleDetailsAreCorrect() throws IOException {
		System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
		WebDriver driver = new FirefoxDriver();
		driver.get(urlLocation);
		driver.findElement(By.id("get-started")).findElement(By.tagName("a")).click();
		VehicleDetailsFileReader fileReader = new VehicleDetailsFileReader(sourceFolderLocation);
		
		fileReader.csv().forEach(fileDetail -> {
			File file = new File(
					sourceFolderLocation + File.separator + fileDetail.getFileName());
			logger.log(Level.FINE, fileDetail.getFileName());
			assertCsv.verify(driver, file);
		});
		
		fileReader.excel().forEach(fileDetail -> {
			File file = new File(
					sourceFolderLocation + File.separator + fileDetail.getFileName());
			logger.log(Level.FINE, fileDetail.getFileName());
			assertExcel.verify(driver, file);
		});		
		driver.quit();
	}
}

