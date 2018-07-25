package com.cct.trn.core.selectitem.domain;

import com.cct.common.CommonSelectItem;

public class CarrierCodeSelectItem extends CommonSelectItem {

	private static final long serialVersionUID = -3989088885307196087L;

	private String carrierType;
	private String carrierName;

	public String getCarrierType() {
		return carrierType;
	}

	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

}
