package test.component;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.common.CommonSelectItem;
import com.cct.domain.GlobalType;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.selectitem.service.SelectItemManager;
import com.opensymphony.xwork2.ModelDriven;

public class ComponentAction extends CommonAction implements ModelDriven<ComponentModel> {

	private static final long serialVersionUID = 5582268301684967635L;

	private ComponentModel model = new ComponentModel();

	public ComponentAction() {

	}

	public String init() {
		String result = ReturnType.INIT.getResult();

		try {

		} catch (Exception e) {
			LogUtil.LOGIN.error("", e);
		}
		return result;
	}

	public String gotoPage() {
		String comp = ReturnType.INIT.getResult();
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			comp = ServletActionContext.getRequest().getParameter("comp").toString();
			LogUtil.LOGIN.debug("COMPONENT : " + comp);

			// getcombo conGlobal
			getComboConGlobal(conn);

			// getcombo Another
			getComboAnother(conn);

			// suggestion
			getSuggestion(conn);

			ServletActionContext.getRequest().setAttribute("ATH.search", "true");
			ServletActionContext.getRequest().setAttribute("ATH.add", "true");
			ServletActionContext.getRequest().setAttribute("ATH.edit", "true");
			ServletActionContext.getRequest().setAttribute("ATH.print", "true");
			ServletActionContext.getRequest().setAttribute("ATH.delete", "true");

			ATH.setSearch(true);
			ATH.setAdd(true);
			ATH.setEdit(true);
			ATH.setPrint(true);
			ATH.setDelete(true);
		} catch (Exception e) {
			LogUtil.LOGIN.error("", e);
		} finally {
			CCTConnectionUtil.close(conn);
		}
		return comp;
	}

	public void getComboConGlobal(CCTConnection conn) throws Exception {

		try {
			model.setLstCarrierTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_TYPE.getValue()));
			model.setLstUserTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.USER_TYPE.getValue()));
			model.setLstLoginTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.LOGIN_TYPE.getValue()));
			model.setLstCarrierUserStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_USER_STATUS.getValue()));
			model.setLstUserStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.USER_STATUS.getValue()));
			model.setLstMovementTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.MOVEMENT_TYPE.getValue()));
			model.setLstTravellerTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.TRAVELLER_TYPE.getValue()));
			model.setLstDataSourceSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DATA_SOURCE.getValue()));
			model.setLstJourneyTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.JOURNEY_TYPE.getValue()));
			model.setLstDeleteStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DELETE_STATUS.getValue()));
			model.setLstCarrierStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_STATUS.getValue()));
			model.setLstFlightStatus(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.FLIGHT_STATUS.getValue()));
			model.setLstScheduleTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.SCHEDULE_TYPE.getValue()));
			model.setLstDocumentTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DOCUMENT_TYPE.getValue()));
			model.setLstBoardStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.BOARD_STATUS.getValue()));
			model.setLstCheckInTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CHECK_IN_TYPE.getValue()));
			model.setLstGenderTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.GENDER_TYPE.getValue()));
			model.setLstManifestTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.MANIFEST_TYPE.getValue()));
			model.setLstTypeOfArrivalSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.TYPE_OF_ARRIVAL.getValue()));
			model.setLstTypeOfDepartureSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.TYPE_OF_DEPARTURE.getValue()));
			model.setLstActiveStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.ACTIVE_STATUS.getValue()));
			model.setLstDateUnitSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DATE_UNIT.getValue()));

			// SelectItemManager selectItemManager = new SelectItemManager(conn,
			// getUser(), getLocale());
		} catch (Exception e) {
			throw e;
		}

	}

	public void getComboAnother(CCTConnection conn) throws Exception {

		try {
			// model.setLstUserStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.USER_STATUS.getValue()));
			model.setLstCarrierTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_TYPE.getValue()));
			model.setLstUserTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.USER_TYPE.getValue()));
			model.setLstLoginTypeSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.LOGIN_TYPE.getValue()));
			model.setLstCarrierStatusSelectItem(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_STATUS.getValue()));
			
			// SelectItemManager selectItemManager = new SelectItemManager(conn,
			// getUser(), getLocale());
		} catch (Exception e) {
			throw e;
		}

	}

	public void getSuggestion(CCTConnection conn) throws Exception {

		try {

			//
			// SUGGESTION
			//
			List<CommonSelectItem> listPort;

			SelectItemManager selectItemManager = new SelectItemManager(conn, getUser(), getLocale());

			// setJsonSuggestDeparture(JSONUtil.serialize(listAirport));
			// setJsonSuggestArrival(JSONUtil.serialize(listAirport));


		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ComponentModel getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
