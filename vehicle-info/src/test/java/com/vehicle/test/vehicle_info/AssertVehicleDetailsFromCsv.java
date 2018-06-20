package com.vehicle.test.vehicle_info;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.WebDriver;

/** Assert vehicle details from csv file using commons csv
 * 
 * @author chandrans1
 *
 */
public class AssertVehicleDetailsFromCsv extends AssertVehicleDetails{
	
	private final static Logger logger = Logger.getLogger(AssertVehicleDetailsFromCsv.class.getName());

	public void verify(WebDriver driver, File file){
		try (Reader reader = new FileReader(file);
				CSVParser records = CSVFormat.EXCEL.parse(reader)) {
				for (CSVRecord record : records) {
					String vehicleRegNumber = record.get(0);
					String vehicleMake = record.get(1);
					System.out.println(vehicleRegNumber + " " + vehicleMake);
					String vehicleColor = record.get(2);
					assertVehicle(driver, vehicleRegNumber, vehicleMake, vehicleColor);
				}

			} catch (FileNotFoundException e) {
				logger.log(Level.SEVERE, e.toString(), e);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.toString(), e);
			}
	}
}
