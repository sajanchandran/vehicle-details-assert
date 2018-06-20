package com.vehicle.test.vehicle_info;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;

public class VehicleDetailsFileReaderTest {

	@Test
	public void getCsvFiles() throws IOException{
		VehicleDetailsFileReader reader = new VehicleDetailsFileReader("src/test/resources");
		MatcherAssert.assertThat(reader.csv().count(), Is.is(0L));
	}
	

	@Test
	public void getExcelFiles() throws IOException{
		VehicleDetailsFileReader reader = new VehicleDetailsFileReader("src/test/resources");
		MatcherAssert.assertThat(reader.excel().count(), Is.is(1L));
	}	
}
