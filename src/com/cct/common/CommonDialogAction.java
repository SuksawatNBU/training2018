package com.cct.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;
import util.web.SessionUtil;

import com.cct.common.CommonAction.MessageType;
import com.cct.domain.SearchCriteria;
import com.cct.exception.MaxExceedException;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommonDialogAction extends ActionSupport implements ModelDriven<CommonModel> {

	private static final long serialVersionUID = -1487536780341881690L;

	private Logger log = loggerInititial();

	private String functionCodePopup;

	protected Logger loggerInititial() {
		return LogUtil.COMMON;
	}

	/**
	 * @Description: Class enum for message Code
	 */
	public enum MessageCode {
		MSG_30004("30004"), MSG_30013("30013"), MSG_30051("30051");

		private String type;

		private MessageCode(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	public SearchCriteria initSearchCriteria() {
		return null;
	}

	public List<CommonDomain> initForSearchListById(CCTConnection conn, String id) throws Exception {
		return new ArrayList<CommonDomain>();
	}

	public List<CommonDomain> search(CCTConnection conn) throws Exception {
		return new ArrayList<CommonDomain>();
	}

	public List<CommonDomain> searchById(CCTConnection conn, String id) throws Exception {
		return new ArrayList<CommonDomain>();
	}

	public CommonDomain searchData(CCTConnection conn, SearchCriteria criteriaPopup, String id) throws Exception {
		return new CommonDomain();
	}

	public CommonDomain searchDetailById(CCTConnection conn) throws Exception {
		return new CommonDomain();
	}

	public String init() {
		log.debug("init");
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			SearchCriteria criteria = initSearchCriteria();
			manageInitialPopup(criteria);
			criteria.setDefaultHeaderSorts();
			getModel().setCriteriaPopup(criteria);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			getComboForSearch(conn);
			CCTConnectionUtil.close(conn);
		}

		return "initialDialog";
	}

	public String initForSearchListById() {
		log.debug("initForSearchListById");

		List<CommonDomain> listResult = new ArrayList<CommonDomain>();
		CCTConnection conn = null;
		try {
			String ids = "";
			if (ServletActionContext.getRequest().getParameter("id") != null) {
				ids = ServletActionContext.getRequest().getParameter("id");
			}

			SearchCriteria criteria = initSearchCriteria();
			manageInitialPopup(criteria);
			criteria.setDefaultHeaderSorts();
			getModel().setCriteriaPopup(criteria);
			getModel().setReferenceDialogId(ids);

			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			// Add Event
			if (getFunctionCodePopup() != null) {
//				LogManager logManager = new LogManager(conn, log);
//				logManager.addEvent(conn, getFunctionCodePopup(), getUser().getUserId(), getModel());
			}

			listResult = initForSearchListById(conn, ids);
			
		} catch (Exception e) {
			log.error("", e);
			setMessagePopup(conn, e, getFunctionCodePopup(), this, getModel(), null, e.toString());
		} finally {
			CCTConnectionUtil.close(conn);
			getModel().setLstResult(listResult);
		}

		return "initialDialog";
	}

	public String searchList() {
		log.debug("searchList");

		List<CommonDomain> listResult = new ArrayList<CommonDomain>();
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			// Add Event
			if (getFunctionCodePopup() != null) {
//				LogManager logManager = new LogManager(conn, log);
//				logManager.addEvent(conn, getFunctionCodePopup(), getUser().getUserId(), getModel());
			}

			listResult = search(conn);
			manageSearchResultPopup(getModel(), getModel().getCriteriaPopup(), listResult);
		} catch (MaxExceedException e) {
			manageExceedExceptionPopup(getModel(), getModel().getCriteriaPopup());
		} catch (Exception e) {
			log.error("", e);
			setMessagePopup(conn, e, getFunctionCodePopup(), this, getModel(), null, e.toString());
		} finally {
			CCTConnectionUtil.close(conn);
			getModel().setLstResult(listResult);
		}
		return "searchList";
	}

	public String searchListById() {
		log.debug("searchListById");

		List<CommonDomain> listResult = new ArrayList<CommonDomain>();
		CCTConnection conn = null;
		try {
			String id = null;
			if (ServletActionContext.getRequest().getParameter("id") != null) {
				id = ServletActionContext.getRequest().getParameter("id");
			}

			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			// Add Event
			if (getFunctionCodePopup() != null) {
//				LogManager logManager = new LogManager(conn, log);
//				logManager.addEvent(conn, getFunctionCodePopup(), getUser().getUserId(), getModel());
			}

			listResult = searchById(conn, id);

			manageSearchResultPopup(getModel(), getModel().getCriteriaPopup(), listResult);
			
		} catch (Exception e) {
			setMessagePopup(conn, e, getFunctionCodePopup(), this, getModel(), null, e.toString());
			log.error("", e);
		} finally {
			CCTConnectionUtil.close(conn);
			getModel().setLstResult(listResult);
		}
		return "searchListById";
	}

	public String searchDetailById() {
		log.debug("searchDetail");

		CommonDomain common = null;

		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			// Add Event
			if (getFunctionCodePopup() != null) {
//				LogManager logManager = new LogManager(conn, log);
//				logManager.addEvent(conn, getFunctionCodePopup(), getUser().getUserId(), getModel());
			}

			common = searchDetailById(conn);
		} catch (Exception e) {
			setMessagePopup(conn, e, getFunctionCodePopup(), this, getModel(), null, e.toString());
			log.error("", e);
		} finally {
			CCTConnectionUtil.close(conn);
		}

		getModel().setObjectPopup(common);
		return "searchDetail";
	}

	public void manageInitialPopup(SearchCriteria criteria) throws Exception {
		try {
			criteria.setStart(1);
			criteria.setLinePerPage(ParameterConfig.getParameter().getApplication().getLppPopup());
			criteria.setTotalResult(0);

		} catch (Exception e) {
			log.error("", e);
			throw e;
		}
	}

	public void manageSearchResultPopup(CommonModel model, SearchCriteria criteria, List<?> listResultPopup) throws Exception {
		try {
			if (listResultPopup == null || listResultPopup.size() == 0) {
				setMessagePopup(null, null, null, null, model, MessageCode.MSG_30004, "");

				criteria.setStart(1);
				criteria.setTotalResult(0);
			} else {

			}
		} catch (Exception e) {
			log.error("", e);
			throw e;
		}
	}

	public void manageExceedExceptionPopup(CommonModel model, SearchCriteria criteria) {
		if (criteria.getNavigatePopup().equals("true")) {
			setMessagePopup(null, null, null, null, model, MessageCode.MSG_30013, String.valueOf(criteria.getTotalResult()));
		} else {
			setMessagePopup(null, null, null, null, model, MessageCode.MSG_30051, "");
		}
	}

	public void manageExceedExceptionPopup(CommonModel model, SearchCriteria criteria, String siteId) {

		if (criteria.getNavigatePopup().equals("true")) {
			setMessagePopup(null, null, null, null, model, MessageCode.MSG_30013, String.valueOf(criteria.getTotalResult()));
		} else {
			setMessagePopup(null, null, null, null, model, MessageCode.MSG_30051, String.valueOf(criteria.getTotalResult()));
		}

	}

	public void setMessagePopup(CommonModel model, MessageCode msgCode, String msgDecs) {
		if (msgCode != null) {
			// กรณีมีค่า MSG_CODE
			switch (msgCode) {
			case MSG_30004:
				// ไม่พบข้อมูลที่ต้องกาาร
				model.setMessagePopup(MessageType.WARING + "::" + getText("30004"));
				break;
			case MSG_30013:
				// จำนวนข้อมูลที่ค้นพบ = xxx รายการ ต้องการแสดงข้อมูลหรือไม่ ?
				model.setMessagePopup(getText("30013").replace("xxx", msgDecs));
				break;
			case MSG_30051:
				// จำนวนข้อมูลที่ค้นพบมีจำนวนมาก กรุณาระบุเงื่อนไขในการค้นหา
				model.setMessagePopup(MessageType.WARING + "::" + getText("30051"));
				break;
			default:
				break;
			}
		} else {
			// กรณี Exception
			model.setMessagePopup(MessageType.ERROR + "::" + getText("30002") + "::" + msgDecs);
		}
	}

	/**
	 *
	 * @param conn
	 * @param e
	 * @param operatorId
	 * @param className
	 * @param model
	 * @param msgCode
	 * @param msgDecs
	 */
	public void setMessagePopup(CCTConnection conn, Exception e, String operatorId, Object className, CommonModel model, MessageCode msgCode, String msgDecs) {
		if (msgCode != null) {
			// กรณีมีค่า MSG_CODE
			switch (msgCode) {
			case MSG_30004:
				// ไม่พบข้อมูลที่ต้องกาาร
				model.setMessagePopup(MessageType.WARING + "::" + getText("30004"));
				break;
			case MSG_30013:
				// จำนวนข้อมูลที่ค้นพบ = xxx รายการ ต้องการแสดงข้อมูลหรือไม่ ?
				model.setMessagePopup(getText("30013").replace("xxx", msgDecs));
				break;
			case MSG_30051:
				// จำนวนข้อมูลที่ค้นพบมีจำนวนมาก กรุณาระบุเงื่อนไขในการค้นหา
				model.setMessagePopup(MessageType.WARING + "::" + getText("30051"));
				break;
			default:
				break;
			}
		} else {

			if (conn != null && getFunctionCodePopup() != null) {
//				LogManager logManager = new LogManager(conn, log);
//				logManager.addError(conn, operatorId, getUser().getUserId(), className, e);
			}

			// กรณี Exception
			model.setMessagePopup(MessageType.ERROR + "::" + getText("30002") + "::" + msgDecs);
		}
	}

	public void getComboForSearch(CCTConnection conn) {
	}

	/**
	 * ดึง user จาก session
	 *
	 * @return
	 */
	public CommonUser getUser() {
		return (CommonUser) SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
	}

	@Override
	public CommonModel getModel() {
		return null;
	}

	public String getFunctionCodePopup() {
		return functionCodePopup;
	}

	public void setFunctionCodePopup(String functionCodePopup) {
		this.functionCodePopup = functionCodePopup;
	}

}
