package com.cct.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.cct.domain.FileMeta;
import com.cct.domain.Message;
import com.cct.domain.SearchCriteria;
import com.cct.domain.Transaction;

@XmlRootElement
public class CommonModel implements Serializable {

	private static final long serialVersionUID = 3541361251724984558L;

	private String language;

	private Transaction transaction = new Transaction();

	private PageType page;

	private List<?> lstResult = new ArrayList<CommonObject>();

	private SearchCriteria criteria;

	/** for CommonDialogAction */
	private String referenceDialogId;
	private SearchCriteria criteriaPopup;
	private CommonDomain objectPopup;
	/** for CommonDialogAction */

	private FileMeta fileMeta = new FileMeta();

	private String messagePopup; // [messageType(success/warning/error)::msg]
	private Message messageAjax = new Message(); // [messageType(success/warning/error)::msg]

	private String urlEdit;

	/** Anusorn.l 2015-06-19 ใช้สำหรับ datatable **/
	private int orderableCount = 0;
	private long recordsTotal = 0;
	private long recordsFiltered = 0;
	private List<?> result = new ArrayList<CommonObject>();

	// For Progress bar;
	private String fileName;
	private String timeProg;

	public enum PageType {
		SEARCH("search")
		, ADD("add")
		, EDIT("edit")
		, VIEW("view")
		, PRINT("print")
		, REPORT("report")
		, SEARCH_DIALOG("search_dialog")
		, CHOOSE_DIALOG("choose_dialog")
		, CONFIRM_ADD("confirm_add")
		, CONFIRM_EDIT("confirm_edit")
		;

		private String page;

		private PageType(String page) {
			this.page = page;
		}

		public String getPage() {
			return page;
		}
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public PageType getPage() {
		return page;
	}

	public void setPage(PageType page) {
		this.page = page;
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteriaPopup(SearchCriteria criteriaPopup) {
		this.criteriaPopup = criteriaPopup;
	}

	public SearchCriteria getCriteriaPopup() {
		return criteriaPopup;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	public List<?> getLstResult() {
		return lstResult;
	}

	public void setLstResult(List<?> lstResult) {
		this.lstResult = lstResult;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public FileMeta getFileMeta() {
		return fileMeta;
	}

	public void setFileMeta(FileMeta fileMeta) {
		this.fileMeta = fileMeta;
	}

	public String getMessagePopup() {
		return messagePopup;
	}

	public void setMessagePopup(String messagePopup) {
		this.messagePopup = messagePopup;
	}

	public CommonDomain getObjectPopup() {
		return objectPopup;
	}

	public void setObjectPopup(CommonDomain objectPopup) {
		this.objectPopup = objectPopup;
	}

	public String getUrlEdit() {
		return urlEdit;
	}

	public void setUrlEdit(String urlEdit) {
		this.urlEdit = urlEdit;
	}

	public int getOrderableCount() {
		return orderableCount;
	}

	public void setOrderableCount(int orderableCount) {
		this.orderableCount = orderableCount;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public Message getMessageAjax() {
		return messageAjax;
	}

	public void setMessageAjax(Message messageAjax) {
		this.messageAjax = messageAjax;
	}

	public String getReferenceDialogId() {
		return referenceDialogId;
	}

	public void setReferenceDialogId(String referenceDialogId) {
		this.referenceDialogId = referenceDialogId;
	}

	public String getTimeProg() {
		return timeProg;
	}

	public void setTimeProg(String timeProg) {
		this.timeProg = timeProg;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
