package com.cct.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import com.cct.common.CommonDomain;

public class Tree extends CommonDomain {

	private static final long serialVersionUID = 3629524869123236630L;

	private String currentId;
	private String parentId;
	private String parentIds;

	// สำหรับเก็บลูก
	private Map<String, Object> mapChild = new LinkedHashMap<String, Object>();

	private int currentLevel;
	private int minLevel;
	private int maxLevel;

	private String label;
	
	private String description; // สำหรับแสดงรายละเอียด หรือ คำอธิบาย
	private String image; // สำหรับแสดง tree ที่มีประเภทเป็น image
	
	
	//KAM สำหรับ  ทดสอบ เก็ยค่า menu ที่ complete แล้ว
	private String chkComplete;

	public String getChkComplete() {
		return chkComplete;
	}

	public void setChkComplete(String chkComplete) {
		this.chkComplete = chkComplete;
	}

	public String getCurrentId() {
		return currentId;
	}

	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Map<String, Object> getMapChild() {
		return mapChild;
	}

	public void setMapChild(Map<String, Object> mapChild) {
		this.mapChild = mapChild;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
