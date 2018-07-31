package com.cct.trn.core.config.parameter.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import util.type.DatabaseType.DbType;

public class Database implements Serializable {

	private static final long serialVersionUID = -2642679205798150121L;

	private String lookup;
	private boolean jndi;
	private String schemas;
	private String databaseType;

	private String driver;
	private String url;
	private String user;
	private String password;

	private String maxActive;
	private String maxIdle;
	private String maxWait;

	private String index;
	private Map<String, String> schemasMap;
	private DbType databaseTypeEnum;

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getSchemas() {
		return schemas;
	}

	public void setSchemas(String schemas) {
		this.schemas = schemas;
	}

	public boolean isJndi() {
		return jndi;
	}

	public void setJndi(boolean jndi) {
		this.jndi = jndi;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}

	public String getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(String maxWait) {
		this.maxWait = maxWait;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@XmlTransient
	public Map<String, String> getSchemasMap() {
		if (schemasMap == null) {
			schemasMap = new HashMap<String, String>();

			if (getSchemas() != null) {
				String[] schemaArr = getSchemas().split(",");
				for (String schemaStr : schemaArr) {
					String[] schemaVal = schemaStr.split(":");
					schemasMap.put(schemaVal[0], schemaVal[1]);
				}
			}
		}

		return schemasMap;
	}

	@XmlTransient
	public DbType getDatabaseTypeEnum() {
		if (databaseTypeEnum == null) {
			if (getDatabaseType().equalsIgnoreCase("mysql")) {
				databaseTypeEnum = DbType.MYSQL;
			} else if (getDatabaseType().equalsIgnoreCase("oracle")) {
				databaseTypeEnum = DbType.ORA;
			} else if (getDatabaseType().equalsIgnoreCase("db2")) {
				databaseTypeEnum = DbType.DB2;
			}
		}
		return databaseTypeEnum;
	}
}
