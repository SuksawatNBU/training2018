package com.cct.trn.core.config.parameter.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.annotation.XmlTransient;

import com.cct.domain.Language;

public class Application implements Serializable {

	private static final long serialVersionUID = -8328972062264715804L;

	private String title;
	private String supportLocaleString;
	private String applicationLocaleString;
	private String datetimeLocaleString;
	private String databaseLocaleString;

	private String lppString;
	private Integer maxExceed;
	private String theme;

	private String defaultLpp;

	// config for popup
	private Integer lppPopup;
	private Integer maxExceedPopup;
	private Integer maxExceedReport;
	private Integer maxSearchLogHistory;

	private List<Language> supportLanguageList;
	private Locale applicationLocale;
	private Locale databaseLocale;
	private Locale datetimeLocale;
	private String[] lpp;

	private String fillAtLeast;
	private String sqlPath;

	private String mailConfigPath;
	
	private String urlCallCp;
	
	private String urlCallUcp;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupportLocaleString() {
		return supportLocaleString;
	}

	public void setSupportLocaleString(String supportLocaleString) {
		this.supportLocaleString = supportLocaleString;
	}

	public String getApplicationLocaleString() {
		return applicationLocaleString;
	}

	public void setApplicationLocaleString(String applicationLocaleString) {
		this.applicationLocaleString = applicationLocaleString;
	}

	public String getDatetimeLocaleString() {
		return datetimeLocaleString;
	}

	public void setDatetimeLocaleString(String datetimeLocaleString) {
		this.datetimeLocaleString = datetimeLocaleString;
	}

	public String getDatabaseLocaleString() {
		return databaseLocaleString;
	}

	public void setDatabaseLocaleString(String databaseLocaleString) {
		this.databaseLocaleString = databaseLocaleString;
	}

	public String getLppString() {
		return lppString;
	}

	public void setLppString(String lppString) {
		this.lppString = lppString;
	}

	public Integer getMaxExceed() {
		return maxExceed;
	}

	public void setMaxExceed(Integer maxExceed) {
		this.maxExceed = maxExceed;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Integer getLppPopup() {
		return lppPopup;
	}

	public void setLppPopup(Integer lppPopup) {
		this.lppPopup = lppPopup;
	}

	public Integer getMaxExceedPopup() {
		return maxExceedPopup;
	}

	public void setMaxExceedPopup(Integer maxExceedPopup) {
		this.maxExceedPopup = maxExceedPopup;
	}

	@XmlTransient
	public List<Language> getSupportLanguageList() {
		if ((supportLanguageList == null) && (getSupportLocaleString() != null)) {
			supportLanguageList = new ArrayList<Language>();
			String[] supportLocaleString = null;
			if (getSupportLocaleString().indexOf(",") > -1) {
				supportLocaleString = getSupportLocaleString().split(",");
			} else {
				supportLocaleString = new String[1];
				supportLocaleString[0] = getSupportLocaleString();
			}

			for (int i = 0; i < supportLocaleString.length; i++) {
				Language language = new Language();
				language.setLocale(new Locale(supportLocaleString[i].toLowerCase(), supportLocaleString[i].toUpperCase()));
				language.setId(String.valueOf(i + 1));
				language.setCode(language.getLocale().getLanguage());
				language.setDesc(language.getLocale().getDisplayLanguage());
				supportLanguageList.add(language);
			}
		}
		return supportLanguageList;
	}

	@XmlTransient
	public Locale getApplicationLocale() {
		if (applicationLocale == null) {
			applicationLocale = new Locale(getApplicationLocaleString(), getApplicationLocaleString());
		}
		return applicationLocale;
	}

	@XmlTransient
	public Locale getDatabaseLocale() {
		if (databaseLocale == null) {
			databaseLocale = new Locale(getDatabaseLocaleString().toLowerCase(), getDatabaseLocaleString().toUpperCase());
		}
		return databaseLocale;
	}

	@XmlTransient
	public Locale getDatetimeLocale() {
		if (datetimeLocale == null) {
			datetimeLocale = new Locale(getDatetimeLocaleString().toLowerCase(), getDatetimeLocaleString().toUpperCase());
		}
		return datetimeLocale;
	}

	@XmlTransient
	public String[] getLpp() {
		if (lpp == null) {
			lpp = getLppString().split(",");
		}
		return lpp;
	}

	public void setSupportLanguageList(List<Language> supportLanguageList) {
		this.supportLanguageList = supportLanguageList;
	}

	public Integer getMaxExceedReport() {
		return maxExceedReport;
	}

	public void setMaxExceedReport(Integer maxExceedReport) {
		this.maxExceedReport = maxExceedReport;
	}

	public String getFillAtLeast() {
		return fillAtLeast;
	}

	public void setFillAtLeast(String fillAtLeast) {
		this.fillAtLeast = fillAtLeast;
	}

	public String getSqlPath() {
		return sqlPath;
	}

	public void setSqlPath(String sqlPath) {
		this.sqlPath = sqlPath;
	}

	public String getDefaultLpp() {
		return defaultLpp;
	}

	public void setDefaultLpp(String defaultLpp) {
		this.defaultLpp = defaultLpp;
	}

	public String getMailConfigPath() {
		return mailConfigPath;
	}

	public void setMailConfigPath(String mailConfigPath) {
		this.mailConfigPath = mailConfigPath;
	}

	public Integer getMaxSearchLogHistory() {
		return maxSearchLogHistory;
	}

	public void setMaxSearchLogHistory(Integer maxSearchLogHistory) {
		this.maxSearchLogHistory = maxSearchLogHistory;
	}

	public String getUrlCallCp() {
		return urlCallCp;
	}

	public void setUrlCallCp(String urlCallCp) {
		this.urlCallCp = urlCallCp;
	}

	public String getUrlCallUcp() {
		return urlCallUcp;
	}

	public void setUrlCallUcp(String urlCallUcp) {
		this.urlCallUcp = urlCallUcp;
	}

}