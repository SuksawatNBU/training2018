package com.cct.trn.core.config.parameter.domain;

import java.io.Serializable;

import resources.sql.dialog.DialogSQL;
import resources.sql.log.LOGSQL;
import resources.sql.mail.MAILSQL;
import resources.sql.security.SecuritySQL;
import resources.sql.selectitem.SelectItemSQL;
import resources.sql.tutorial.employee.EmployeeSQL;
import resources.sql.tutorial.project.ProjectSQL;

public enum SQLPath implements Serializable {
	/**
	 * @Description: Class enum for data base lookup
	 */
	SELECT_ITEM_SQL(SelectItemSQL.class, "resources/sql/selectitem/SelectItem.sql")
	, LOGIN_SQL(SecuritySQL.class, "resources/sql/security/Login.sql")
	, CHANGE_PWD_SQL(SecuritySQL.class, "resources/sql/security/ChangePassword.sql")
	, VIEW_USER_PRO_SQL(SecuritySQL.class, "resources/sql/security/ViewUserProfile.sql")
	, MAIL_SQL(MAILSQL.class, "resources/sql/mail/Mail.sql")
	, LOG_SQL(LOGSQL.class, "resources/sql/log/Log.sql")
	, SECURITY_GROUP(SecuritySQL.class, "resources/sql/security/Group.sql")
	
	// dialog
	, DIALOG_SQL(DialogSQL.class, "resources/sql/dialog/dialog.sql")
	, PROJECT_SQL(ProjectSQL.class, "resources/sql/tutorial/project/Project.sql")
	, SYS_DIALOG_SQL(DialogSQL.class, "resources/sql/tutorial/dialog/SystemDialog.sql")
	, EMPLOYEE_SQL (EmployeeSQL.class, "resources/sql/tutorial/employee/Employee.sql")
	;

	private String path;
	private Class<?> className;

	private SQLPath(Class<?> className, String path) {
		this.path = path;
		this.className = className;
	}

	public String getPath() {
		return path;
	}

	public Class<?> getClassName() {
		return className;
	}
}