package com.cct.trn.web.standardui.miscellaneous.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;
import util.web.SessionUtil;
import util.web.TreeUtil;

import com.cct.common.CommonAction;
import com.cct.common.CommonSelectItem;
import com.cct.domain.Tree;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.dialog.standardui.treeview.service.TreeDialogManager;
import com.cct.trn.core.selectitem.service.SelectItemManager;
import com.cct.trn.core.standardui.miscellaneous.domain.MiscellaneousModel;
import com.opensymphony.xwork2.ModelDriven;

public class MiscellaneousAction extends CommonAction implements ModelDriven<MiscellaneousModel> {

	private static final long serialVersionUID = 4557027877706302838L;

	private MiscellaneousModel model = new MiscellaneousModel();

	// For Suggestion
	private String jsonSuggestData;
		
	public MiscellaneousAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}

	public String initDate() {
		LogUtil.TRAINING.debug("initDate");
		model.setInputDate("25/12/2016");
		LogUtil.TRAINING.debug("Input date : " + model.getInputDate());
		return "initDate";
	}
	
	public String initTime() {
		LogUtil.TRAINING.debug("initTime");
		return "initTime";
	}
	
	public String initIpAddress() {
		LogUtil.TRAINING.debug("initIpAddress");
		return "initIpAddress";
	}
	
	public String initSuggestion() {
		LogUtil.TRAINING.debug("initSuggestion");
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			SelectItemManager manager = new SelectItemManager(conn, getUser(), getLocale());
			getJSONSuggestDataSelectItem(manager.searchAllQAUserAutoSelectItem(null, null));
			
		} catch (Exception e) {
			LogUtil.TRAINING.debug(e);
		} finally {
			CCTConnectionUtil.close(conn);
		}
		return "initSuggestion";
	}
	
	public void getJSONSuggestDataSelectItem(List<CommonSelectItem> listSuggestDataSelectItem) throws Exception {
	    setJsonSuggestData(JSONUtil.serialize(listSuggestDataSelectItem));
	}
	
	public String initTooltip() {
		LogUtil.TRAINING.debug("initTooltip");
		return "initTooltip";
	}
	
	public String initUpload() {
		LogUtil.TRAINING.debug("initUpload");
		return "initUpload";
	}
	
	public String initAccordion() {
		LogUtil.TRAINING.debug("initAccordion");
		return "initAccordion";
	}
	
	public String initTab() {
		LogUtil.TRAINING.debug("initTab");
		return "initTab";
	}
	
	public String initTreeview() {
		LogUtil.TRAINING.debug("initTreeview");
		return "initTreeview";
	}
	
	public String searchTreeview() {
		LogUtil.TRAINING.debug("searchTreeview");
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			
			String treeId = "";
			if (ServletActionContext.getRequest().getParameter("treeId") != null) {
				treeId = ServletActionContext.getRequest().getParameter("treeId").trim();
			}

			String treeType = TreeUtil.TYPE_SPAN;
			if (ServletActionContext.getRequest().getParameter("treeType") != null && !ServletActionContext.getRequest().getParameter("treeType").equals("")) {
				treeType = ServletActionContext.getRequest().getParameter("treeType").trim();
			}
			
			Map<String, Tree> mapTree = new LinkedHashMap<String, Tree>();
			mapTree = new TreeDialogManager(conn).searchTree();

			if (mapTree.size() == 0) {
				model.getTree().getTreeData().setMapTree(null);
				model.getTree().getTreeData().setHtmlTree(null);
			} else {
				model.getTree().getTreeData().setMapTree(mapTree);
				model.getTree().getTreeData().setHtmlTree(new TreeUtil(treeId, treeType, mapTree, "").genarateTree());
			}
			
		} catch (Exception e) {
			LogUtil.TRAINING.error(e);
		} finally {
			CCTConnectionUtil.close(conn);
		}
		return "searchListTree";
	}
	
	public String setType() {
		LogUtil.TRAINING.debug("setType");
		LogUtil.TRAINING.debug("Type : " + model.getTypeSelected());
		model.setTypeSelected(model.getTypeSelected());
		return ReturnType.SEARCH.getResult();
	}
	
	public String initProgressbar() {
		LogUtil.TRAINING.debug("initProgressbar");
		return "initProgressbar";
	}
	
	@Override
	public MiscellaneousModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
	
	public String getJsonSuggestData() {
		return jsonSuggestData;
	}

	public void setJsonSuggestData(String jsonSuggestData) {
		this.jsonSuggestData = jsonSuggestData;
	}
	
	public String runningProgress() throws Exception {
		LogUtil.TRAINING.info("customFormatNumber");

		String numberFormat = "";
		String inputNumber = "";
		try {
			int progrss = Integer.parseInt(SessionUtil.requestParameter("progress"));
			progrss = progrss+1;
			getModel().setProgress(progrss);
			getModel().setFlagProcess(true);
			if(progrss == 100){
				getModel().setFlagProcess(false);
			}
		} catch (Exception e) {
			LogUtil.TRAINING.error(e);
		}

		return "ajaxModel";
	}

	public String runningProcess() throws Exception {
		LogUtil.TRAINING.info("customFormatNumber");

		String numberFormat = "";
		String inputNumber = "";
		try {
			int progrss = Integer.parseInt(SessionUtil.requestParameter("progress"));

			Thread.sleep(10000);

			progrss = progrss+1;
			getModel().setProgress(progrss);
			getModel().setFlagProcess(true);
			if(progrss == 100){
				getModel().setFlagProcess(false);
			}
			
		} catch (Exception e) {
			LogUtil.TRAINING.error(e);
		}
		return "ajaxModel";
	}
	
	
}
