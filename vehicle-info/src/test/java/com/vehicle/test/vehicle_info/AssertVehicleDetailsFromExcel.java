package com.vehicle.test.vehicle_info;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;

/** Assert vehicle details from excel using poi 
 * 
 * @author chandrans1
 *
 */
public class AssertVehicleDetailsFromExcel extends AssertVehicleDetails{
	
	private final static Logger logger = Logger.getLogger(AssertVehicleDetailsFromExcel.class.getName());
	
	/* (non-Javadoc)
	 * @see com.vehicle.test.vehicle_info.AssertVehicleDetails#verify(org.openqa.selenium.WebDriver, java.io.File)
	 */
	public void verify(WebDriver driver, File file){
		try(FileInputStream fis = new FileInputStream(file);
				Workbook wb = new HSSFWorkbook(fis);
				){
			Sheet sheet = wb.getSheetAt(0);
		    for(Row row : sheet) {
		    	String vehicleRegNumber = row.getCell(0).getStringCellValue();
		    	String vehicleMake = row.getCell(1).getStringCellValue();
		    	String vehicleColor = row.getCell(2).getStringCellValue();
		    	assertVehicle(driver, vehicleRegNumber, vehicleMake, vehicleColor);
		    }
		} catch (FileNotFoundException e1) {
			logger.log(Level.SEVERE, e1.toString(), e1);
		} catch (IOException e1) {
			logger.log(Level.SEVERE, e1.toString(), e1);
		}		
	}
}
