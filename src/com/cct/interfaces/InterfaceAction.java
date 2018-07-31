/**
 * Description: interface InterfaceAction
 * @Author: Mano.s
 * @Version: 1.0
 * @Create date: 15/02/2012
 * Changes Log
 * ----------------
 */

package com.cct.interfaces;

import util.database.CCTConnection;

import com.cct.domain.Transaction;

public interface InterfaceAction {

	// From menu
	public String init() throws Exception;

	// Load combo for search page
	public void getComboForSearch(CCTConnection conn) ;

	// Load combo for add and edit page
	public void getComboForAddEdit(CCTConnection conn) ;

	// To search
	public String search() throws Exception;

	// To go to add page
	public String gotoAdd() throws Exception;

	// To add
	public String add() throws Exception;

	// To edit
	public String edit() throws Exception;

	// To go to edit page
	public String gotoEdit() throws Exception;

	// To go to view page
	public String gotoView() throws Exception;

	// To update Active
	public String updateActive() throws Exception;

	// To delete
	public String delete() throws Exception;

	// To export
	public String export() throws Exception;

	// To show transaction
	public void showTransaction(Transaction transaction);

}