package com.cct.trn.core.config.parameter.domain;

import java.io.Serializable;

public class AttachFile implements Serializable {

	private static final long serialVersionUID = 8940963552725725017L;

	private String tmpPath;
	private String serverPath;
	private String imageDefault;
	private String fileExtension;
	private String fileExtensionForDisplay;
	private String maximumFileSizeMb;

	public String getTmpPath() {
		return tmpPath;
	}

	public void setTmpPath(String tmpPath) {
		this.tmpPath = tmpPath;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getImageDefault() {
		return imageDefault;
	}

	public void setImageDefault(String imageDefault) {
		this.imageDefault = imageDefault;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileExtensionForDisplay() {
		return fileExtensionForDisplay;
	}

	public void setFileExtensionForDisplay(String fileExtensionForDisplay) {
		this.fileExtensionForDisplay = fileExtensionForDisplay;
	}

	public String getMaximumFileSizeMb() {
		return maximumFileSizeMb;
	}

	public void setMaximumFileSizeMb(String maximumFileSizeMb) {
		this.maximumFileSizeMb = maximumFileSizeMb;
	}
	
}
