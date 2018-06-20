package com.vehicle.test.vehicle_info;

public class FileDetails {

	private final String fileName;
	private final String mimeType;
	private final Long size;
	private final String extension;

	public FileDetails(String fileName, String mimeType, Long size, String extension) {
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.size = size;
		this.extension = extension;
	}
	
	
	public String getFileName() {
		return fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public Long getSize() {
		return size;
	}

	public String getExtension() {
		return extension;
	}

	@Override
	public String toString() {
		return fileName + " " + mimeType + " " + size + " " + extension;
	}
}
