package test.component;

import com.cct.common.CommonModel;

public class ComponentCriteria extends CommonModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2556505138336448310L;
	private String userType;
	private String carrierType;
	private String userStatus;
	private String loginType;
	private String carrierStatus;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getCarrierStatus() {
		return carrierStatus;
	}
	public void setCarrierStatus(String carrierStatus) {
		this.carrierStatus = carrierStatus;
	}
}
