package com.cct.trn.core.config.parameter.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Parameter implements Serializable {

	private static final long serialVersionUID = -578325849007499211L;

	// @XmlTransient
	// @XmlAttribute
	// @XmlElement
	private Application application;
	private Database[] database;
	private Map<String, Database> databaseMap;
	private DateFormat dateFormat;
	private AttachFile attachFile;
	private Report report;

	public Application getApplication() {
		return application;
	}

	@XmlElement
	public void setApplication(Application application) {
		this.application = application;
	}

	@XmlTransient
	public Map<String, Database> getDatabaseMap() {
		if (getDatabase() == null) {
			return databaseMap;
		}

		if (databaseMap == null) {
			databaseMap = new LinkedHashMap<String, Database>();
			for (Database db : getDatabase()) {
				databaseMap.put(db.getIndex(), db);
			}
		}
		return databaseMap;
	}

	public Database[] getDatabase() {
		return database;
	}

	@XmlElement
	public void setDatabase(Database[] database) {
		this.database = database;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	@XmlElement
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public AttachFile getAttachFile() {
		return attachFile;
	}

	@XmlElement
	public void setAttachFile(AttachFile attachFile) {
		this.attachFile = attachFile;
	}

	public Report getReport() {
		return report;
	}

	@XmlElement
	public void setReport(Report report) {
		this.report = report;
	}

}
