package com.cct.common;

import java.io.Serializable;

import com.cct.domain.Active;
import com.cct.domain.Language;
import com.cct.domain.Transaction;

public class CommonDomain implements Serializable {

	private static final long serialVersionUID = 5281141596896595011L;

	private String id; // pk ของ data นั้นๆ
	private Language language = new Language(); // ภาษาของ data นั้นๆ
	private Active active = new Active(); // สถานะ ของ data นั้นๆ
	private Transaction transaction = new Transaction(); // transaction การบันทึกข้อมูลแสดงที่ หน้าแก้ไข/แสดง
	private String deleteFlag; // Flag สำหรับเก็บค่าการลบ ในแถวของตารางแบบเพิ่มลบตารางได้

	/** nanthaporn.p 2014-08-07 */
	private String idPopup; // pk ของ data นั้นๆ ที่หน้า Popup
	private String rownum;
	private boolean edited; //สำหรับ tableAddDeleteRowInForm
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Active getActive() {
		return active;
	}

	public void setActive(Active active) {
		this.active = active;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getIdPopup() {
		return idPopup;
	}

	public void setIdPopup(String idPopup) {
		this.idPopup = idPopup;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

}