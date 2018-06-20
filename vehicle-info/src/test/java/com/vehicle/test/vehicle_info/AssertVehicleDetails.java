package com.vehicle.test.vehicle_info;

import static com.vehicle.test.vehicle_info.VehicleDetailsPageTest.xpathForColor;
import static com.vehicle.test.vehicle_info.VehicleDetailsPageTest.xpathForMake;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.openqa.selenium.By.xpath;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Abstract class which does the actual assertions.
 * 
 * @author chandrans1
 *
 */
public abstract class AssertVehicleDetails {

	/** Subclass needs to override the behaviour based on the file type.
	 * 
	 * @param driver
	 * @param file
	 */
	public abstract void verify(WebDriver driver, File file);
	
	/** Common assertion method for vehicles
	 * @param driver
	 * @param inputVehicleRegNumber
	 * @param expectedVehicleMake
	 * @param expectedVehicleColor
	 */
	public void assertVehicle(WebDriver driver, String inputVehicleRegNumber, String expectedVehicleMake, String expectedVehicleColor){
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Vrm")));
		driver.findElement(By.id("Vrm")).sendKeys(inputVehicleRegNumber);
		driver.findElement(By.name("Continue")).submit();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='pr3']/div/ul/li[2]/span[2]/strong")));
		String make = driver.findElement(xpath(xpathForMake)).getText();
		String color = driver.findElement(xpath(xpathForColor)).getText();
		assertThat(make, is(expectedVehicleMake));
		assertThat(color, is(expectedVehicleColor));
		driver.findElement(By.id("Correct_False")).click();
		driver.findElement(By.name("Continue")).submit();
	}

}
