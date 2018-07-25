package com.cct.trn.core.config.parameter.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class Report implements Serializable {

	private static final long serialVersionUID = 1061894440878031750L;

	private String font;
	private String pathfile;
	private String passwordReport;
	private Integer maxStatisticReportDay;
	private Integer maxReportDay;
	private String pathLicense;

	public String getFont() {
		return font;
	}

	@XmlElement
	public void setFont(String font) {
		this.font = font;
	}

	public String getPathfile() {
		return pathfile;
	}

	@XmlElement
	public void setPathfile(String pathfile) {
		this.pathfile = pathfile;
	}

	public String getPasswordReport() {
		return passwordReport;
	}

	@XmlElement
	public void setPasswordReport(String passwordReport) {
		this.passwordReport = passwordReport;
	}

	public Integer getMaxStatisticReportDay() {
		return maxStatisticReportDay;
	}

	@XmlElement
	public void setMaxStatisticReportDay(Integer maxStatisticReportDay) {
		this.maxStatisticReportDay = maxStatisticReportDay;
	}

	public Integer getMaxReportDay() {
		return maxReportDay;
	}

	@XmlElement
	public void setMaxReportDay(Integer maxReportDay) {
		this.maxReportDay = maxReportDay;
	}

	public String getPathLicense() {
		return pathLicense;
	}

	@XmlElement
	public void setPathLicense(String pathLicense) {
		this.pathLicense = pathLicense;
	}

}
