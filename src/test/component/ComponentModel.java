package test.component;

import java.util.ArrayList;
import java.util.List;

import com.cct.common.CommonModel;
import com.cct.common.CommonSelectItem;

public class ComponentModel extends CommonModel {

	private static final long serialVersionUID = 3215049985057215334L;

	private List<CommonSelectItem> lstCarrierTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstUserTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstLoginTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstCarrierUserStatusSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstUserStatusSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstMovementTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstTravellerTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstDataSourceSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstJourneyTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstDeleteStatusSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstCarrierStatusSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstFlightStatus = new ArrayList<>();
	private List<CommonSelectItem> lstScheduleTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstDocumentTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstBoardStatusSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstCheckInTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstGenderTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstManifestTypeSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstTypeOfArrivalSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstTypeOfDepartureSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstActiveStatusSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstDateUnitSelectItem = new ArrayList<>();
	private List<CommonSelectItem> lstCarrierStatusNoProvisionalSelectItem = new ArrayList<>();

	private String carrierCode;

	private String jsonSuggestDeparturePort;
	private String jsonSuggestArrivalPort;

	public List<CommonSelectItem> getLstCarrierTypeSelectItem() {
		return lstCarrierTypeSelectItem;
	}

	public void setLstCarrierTypeSelectItem(List<CommonSelectItem> lstCarrierTypeSelectItem) {
		this.lstCarrierTypeSelectItem = lstCarrierTypeSelectItem;
	}

	public List<CommonSelectItem> getLstUserTypeSelectItem() {
		return lstUserTypeSelectItem;
	}

	public void setLstUserTypeSelectItem(List<CommonSelectItem> lstUserTypeSelectItem) {
		this.lstUserTypeSelectItem = lstUserTypeSelectItem;
	}

	public List<CommonSelectItem> getLstLoginTypeSelectItem() {
		return lstLoginTypeSelectItem;
	}

	public void setLstLoginTypeSelectItem(List<CommonSelectItem> lstLoginTypeSelectItem) {
		this.lstLoginTypeSelectItem = lstLoginTypeSelectItem;
	}

	public List<CommonSelectItem> getLstCarrierUserStatusSelectItem() {
		return lstCarrierUserStatusSelectItem;
	}

	public void setLstCarrierUserStatusSelectItem(List<CommonSelectItem> lstCarrierUserStatusSelectItem) {
		this.lstCarrierUserStatusSelectItem = lstCarrierUserStatusSelectItem;
	}

	public List<CommonSelectItem> getLstUserStatusSelectItem() {
		return lstUserStatusSelectItem;
	}

	public void setLstUserStatusSelectItem(List<CommonSelectItem> lstUserStatusSelectItem) {
		this.lstUserStatusSelectItem = lstUserStatusSelectItem;
	}

	public List<CommonSelectItem> getLstMovementTypeSelectItem() {
		return lstMovementTypeSelectItem;
	}

	public void setLstMovementTypeSelectItem(List<CommonSelectItem> lstMovementTypeSelectItem) {
		this.lstMovementTypeSelectItem = lstMovementTypeSelectItem;
	}

	public List<CommonSelectItem> getLstTravellerTypeSelectItem() {
		return lstTravellerTypeSelectItem;
	}

	public void setLstTravellerTypeSelectItem(List<CommonSelectItem> lstTravellerTypeSelectItem) {
		this.lstTravellerTypeSelectItem = lstTravellerTypeSelectItem;
	}

	public List<CommonSelectItem> getLstDataSourceSelectItem() {
		return lstDataSourceSelectItem;
	}

	public void setLstDataSourceSelectItem(List<CommonSelectItem> lstDataSourceSelectItem) {
		this.lstDataSourceSelectItem = lstDataSourceSelectItem;
	}

	public List<CommonSelectItem> getLstJourneyTypeSelectItem() {
		return lstJourneyTypeSelectItem;
	}

	public void setLstJourneyTypeSelectItem(List<CommonSelectItem> lstJourneyTypeSelectItem) {
		this.lstJourneyTypeSelectItem = lstJourneyTypeSelectItem;
	}

	public List<CommonSelectItem> getLstDeleteStatusSelectItem() {
		return lstDeleteStatusSelectItem;
	}

	public void setLstDeleteStatusSelectItem(List<CommonSelectItem> lstDeleteStatusSelectItem) {
		this.lstDeleteStatusSelectItem = lstDeleteStatusSelectItem;
	}

	public List<CommonSelectItem> getLstCarrierStatusSelectItem() {
		return lstCarrierStatusSelectItem;
	}

	public void setLstCarrierStatusSelectItem(List<CommonSelectItem> lstCarrierStatusSelectItem) {
		this.lstCarrierStatusSelectItem = lstCarrierStatusSelectItem;
	}

	public List<CommonSelectItem> getLstFlightStatus() {
		return lstFlightStatus;
	}

	public void setLstFlightStatus(List<CommonSelectItem> lstFlightStatus) {
		this.lstFlightStatus = lstFlightStatus;
	}

	public List<CommonSelectItem> getLstScheduleTypeSelectItem() {
		return lstScheduleTypeSelectItem;
	}

	public void setLstScheduleTypeSelectItem(List<CommonSelectItem> lstScheduleTypeSelectItem) {
		this.lstScheduleTypeSelectItem = lstScheduleTypeSelectItem;
	}

	public List<CommonSelectItem> getLstDocumentTypeSelectItem() {
		return lstDocumentTypeSelectItem;
	}

	public void setLstDocumentTypeSelectItem(List<CommonSelectItem> lstDocumentTypeSelectItem) {
		this.lstDocumentTypeSelectItem = lstDocumentTypeSelectItem;
	}

	public List<CommonSelectItem> getLstBoardStatusSelectItem() {
		return lstBoardStatusSelectItem;
	}

	public void setLstBoardStatusSelectItem(List<CommonSelectItem> lstBoardStatusSelectItem) {
		this.lstBoardStatusSelectItem = lstBoardStatusSelectItem;
	}

	public List<CommonSelectItem> getLstCheckInTypeSelectItem() {
		return lstCheckInTypeSelectItem;
	}

	public void setLstCheckInTypeSelectItem(List<CommonSelectItem> lstCheckInTypeSelectItem) {
		this.lstCheckInTypeSelectItem = lstCheckInTypeSelectItem;
	}

	public List<CommonSelectItem> getLstGenderTypeSelectItem() {
		return lstGenderTypeSelectItem;
	}

	public void setLstGenderTypeSelectItem(List<CommonSelectItem> lstGenderTypeSelectItem) {
		this.lstGenderTypeSelectItem = lstGenderTypeSelectItem;
	}

	public List<CommonSelectItem> getLstManifestTypeSelectItem() {
		return lstManifestTypeSelectItem;
	}

	public void setLstManifestTypeSelectItem(List<CommonSelectItem> lstManifestTypeSelectItem) {
		this.lstManifestTypeSelectItem = lstManifestTypeSelectItem;
	}

	public List<CommonSelectItem> getLstTypeOfArrivalSelectItem() {
		return lstTypeOfArrivalSelectItem;
	}

	public void setLstTypeOfArrivalSelectItem(List<CommonSelectItem> lstTypeOfArrivalSelectItem) {
		this.lstTypeOfArrivalSelectItem = lstTypeOfArrivalSelectItem;
	}

	public List<CommonSelectItem> getLstTypeOfDepartureSelectItem() {
		return lstTypeOfDepartureSelectItem;
	}

	public void setLstTypeOfDepartureSelectItem(List<CommonSelectItem> lstTypeOfDepartureSelectItem) {
		this.lstTypeOfDepartureSelectItem = lstTypeOfDepartureSelectItem;
	}

	public List<CommonSelectItem> getLstActiveStatusSelectItem() {
		return lstActiveStatusSelectItem;
	}

	public void setLstActiveStatusSelectItem(List<CommonSelectItem> lstActiveStatusSelectItem) {
		this.lstActiveStatusSelectItem = lstActiveStatusSelectItem;
	}

	public List<CommonSelectItem> getLstDateUnitSelectItem() {
		return lstDateUnitSelectItem;
	}

	public void setLstDateUnitSelectItem(List<CommonSelectItem> lstDateUnitSelectItem) {
		this.lstDateUnitSelectItem = lstDateUnitSelectItem;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getJsonSuggestDeparturePort() {
		return jsonSuggestDeparturePort;
	}

	public void setJsonSuggestDeparturePort(String jsonSuggestDeparturePort) {
		this.jsonSuggestDeparturePort = jsonSuggestDeparturePort;
	}

	public String getJsonSuggestArrivalPort() {
		return jsonSuggestArrivalPort;
	}

	public void setJsonSuggestArrivalPort(String jsonSuggestArrivalPort) {
		this.jsonSuggestArrivalPort = jsonSuggestArrivalPort;
	}

	public List<CommonSelectItem> getLstCarrierStatusNoProvisionalSelectItem() {
		return lstCarrierStatusNoProvisionalSelectItem;
	}

	public void setLstCarrierStatusNoProvisionalSelectItem(
			List<CommonSelectItem> lstCarrierStatusNoProvisionalSelectItem) {
		this.lstCarrierStatusNoProvisionalSelectItem = lstCarrierStatusNoProvisionalSelectItem;
	}

}
