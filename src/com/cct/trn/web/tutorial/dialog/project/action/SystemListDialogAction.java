package com.cct.trn.web.tutorial.dialog.project.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonDialogAction;
import com.cct.common.CommonDomain;
import com.cct.domain.SearchCriteria;
import com.cct.trn.core.tutorial.dialog.project.domain.SystemListDialogModel;
import com.cct.trn.core.tutorial.dialog.project.domain.SystemListDialogSearchCriteria;
import com.cct.trn.core.tutorial.dialog.project.service.SystemListDialogManager;

public class SystemListDialogAction extends CommonDialogAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9124161263859428551L;
	
	private SystemListDialogModel model = new SystemListDialogModel();
 
	@Override
	public SearchCriteria initSearchCriteria() {
		SystemListDialogSearchCriteria criteria = new SystemListDialogSearchCriteria();
		return criteria;
	}
	
    @Override
    public List<CommonDomain> initForSearchListById(CCTConnection conn, String id) throws Exception  {
    	LogUtil.DIALOG.debug("initForSearchListById");
    	
    	List<CommonDomain> lstResult = new ArrayList<CommonDomain>();
    	try {
    		SystemListDialogManager manager = new SystemListDialogManager(conn, getUser(), getLocale());
    		SystemListDialogSearchCriteria criteria = (SystemListDialogSearchCriteria) getModel().getCriteriaPopup();
    		criteria.setProjectId(id);
    		
    		lstResult = manager.searchDetail(criteria);
//    		lstResult = manager.searchDetail((SystemListDialogSearchCriteria) getModel().getCriteriaPopup(), id);
			
		} catch (Exception e) {
			throw e;
		}
    	return lstResult;
    }
	
	@Override
	public SystemListDialogModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.DIALOG;
	}

	@Override
	public List<CommonDomain> searchById(CCTConnection conn, String id)
			throws Exception {
		LogUtil.DIALOG.debug("searchById");
    	
    	List<CommonDomain> lstResult = new ArrayList<CommonDomain>();
    	try {
    		SystemListDialogManager manager = new SystemListDialogManager(conn, getUser(), getLocale());
    		SystemListDialogSearchCriteria criteria = (SystemListDialogSearchCriteria) getModel().getCriteriaPopup();
    		criteria.setProjectId(id);
    		
    		lstResult = manager.searchDetail(criteria);
//    		lstResult = manager.searchDetail((SystemListDialogSearchCriteria) getModel().getCriteriaPopup());
			
		} catch (Exception e) {
			throw e;
		}
    	return lstResult;
	}

}
