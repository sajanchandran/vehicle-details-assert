package com.vehicle.test.vehicle_info;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;

/** Vehicle details are read from excel and csv file from input folder location 
 * 
 * @author chandrans1
 *
 */
public class VehicleDetailsFileReader {

	/**
	 * Folder Location Variable
	 */
	private final String folderLocation;

	public VehicleDetailsFileReader(String folderLocation) {
		this.folderLocation = folderLocation;
	}

	private List<FileDetails> scanFolder() throws IOException {
		List<FileDetails> fileDetails = new ArrayList<>();
		try (Stream<Path> paths = Files.walk(Paths.get(folderLocation))) {
			paths.forEach(path -> {
				try {
					String fileName = path.getFileName().toString();
					fileDetails.add(new FileDetails(fileName, Files.probeContentType(path), Files.size(path),
							FilenameUtils.getExtension(fileName)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			return fileDetails;
		}
	}

	/** Read all excel or csv files from input folder location
	 * 
	 * @return Stream<FileDetails>
	 * @throws IOException
	 */
	public Stream<FileDetails> csv() throws IOException {
		List<FileDetails> fileDetails = scanFolder();
		return fileDetails.stream().filter(file -> ("text/csv".equals(file.getMimeType())));
	}

	/** Read all excel or csv files from input folder location
	 * 
	 * @return Stream<FileDetails>
	 * @throws IOException
	 */
	public Stream<FileDetails> excel() throws IOException {
		List<FileDetails> fileDetails = scanFolder();
		return fileDetails.stream().filter(file -> ("application/vnd.ms-excel".equals(file.getMimeType())));
	}
}
