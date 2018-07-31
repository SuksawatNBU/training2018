package com.cct.trn.core.dialog.standardui.treeview.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.string.StringUtil;

import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.dialog.standardui.treeview.domain.TutorialTree;

public class TreeDialogDAO {

	private SQLPath sqlPath = SQLPath.DIALOG_SQL;

	protected Map<String, com.cct.domain.Tree> searchTree(CCTConnection conn) throws Exception {

		Map<String, com.cct.domain.Tree> mapTree = new LinkedHashMap<String, com.cct.domain.Tree>();
		try {

			TutorialTree tree = new TutorialTree();
//			tree.setGroupSystem();
//			tree.setOperatorType();
//			tree.setPath();

			tree.setOperatorLevel("1");
			tree.setListNo("1");
			tree.setSystemName("ระบบรักษาความปลอดภัย");
			tree.setMenuName("ระบบรักษาความปลอดภัย");
			tree.setFunctionName("ระบบรักษาความปลอดภัย");
			tree.setImage("/training2018/images/treeview/i_blue.gif");
			tree.setParentId("");
			tree.setCurrentId("001000000000");
			tree.setLabel("ระบบรักษาความปลอดภัย");
			tree.setCurrentLevel(1);
			tree.setMinLevel(1);
			tree.setMaxLevel(4);
			tree.setDescription("");
			mapTree.put(tree.getCurrentId(), tree);
			
			tree = new TutorialTree();
			tree.setOperatorLevel("2");
			tree.setListNo("2");
			tree.setSystemName("ข้อมูลผู้ใช้งานระบบ");
			tree.setMenuName("ข้อมูลผู้ใช้งานระบบ");
			tree.setFunctionName("ข้อมูลผู้ใช้งานระบบ");
			tree.setImage("/training2018/images/treeview/i_green.gif");
			tree.setParentId("001000000000");
			tree.setCurrentId("001001000000");
			tree.setLabel("ข้อมูลผู้ใช้งานระบบ");
			tree.setCurrentLevel(2);
			tree.setMinLevel(1);
			tree.setMaxLevel(4);
			tree.setDescription("");
			mapTree.put(tree.getCurrentId(), tree);
			
			tree = new TutorialTree();
			tree.setOperatorLevel("3");
			tree.setListNo("3");
			tree.setSystemName("ข้อมูลผู้ใช้งานระบบ");
			tree.setMenuName("ข้อมูลผู้ใช้งานระบบ");
			tree.setFunctionName("ข้อมูลผู้ใช้งานระบบ");
			tree.setImage("/training2018/images/treeview/i_orange.gif");
			tree.setParentId("001001000000");
			tree.setCurrentId("001001001000");
			tree.setLabel("ข้อมูลผู้ใช้งานระบบ");
			tree.setCurrentLevel(3);
			tree.setMinLevel(1);
			tree.setMaxLevel(4);
			tree.setDescription("");
			mapTree.put(tree.getCurrentId(), tree);
			
			tree = new TutorialTree();
			tree.setOperatorLevel("4");
			tree.setListNo("4");
			tree.setSystemName("ตั้งข้อกำหนดระบบรักษาความปลอดภัย");
			tree.setMenuName("ตั้งข้อกำหนดระบบรักษาความปลอดภัย");
			tree.setFunctionName("ตั้งข้อกำหนดระบบรักษาความปลอดภัย");
			tree.setImage("/training2018/images/treeview/i_pink.gif");
			tree.setParentId("001001001000");
			tree.setCurrentId("001001001001");
			tree.setLabel("ตั้งข้อกำหนดระบบรักษาความปลอดภัย");
			tree.setCurrentLevel(4);
			tree.setMinLevel(1);
			tree.setMaxLevel(4);
			tree.setDescription("");
			mapTree.put(tree.getCurrentId(), tree);
			
			tree = new TutorialTree();
			tree.setOperatorLevel("3");
			tree.setListNo("5");
			tree.setSystemName("จัดการข้อมูลผู้ใช้งานระบบ");
			tree.setMenuName("จัดการข้อมูลผู้ใช้งานระบบ");
			tree.setFunctionName("จัดการข้อมูลผู้ใช้งานระบบ");
			tree.setImage("/training2018/images/treeview/i_orange.gif");
			tree.setParentId("001001000000");
			tree.setCurrentId("001001002000");
			tree.setLabel("จัดการข้อมูลผู้ใช้งานระบบ");
			tree.setCurrentLevel(3);
			tree.setMinLevel(1);
			tree.setMaxLevel(4);
			tree.setDescription("");
			mapTree.put(tree.getCurrentId(), tree);
			
			tree = new TutorialTree();
			tree.setOperatorLevel("4");
			tree.setListNo("6");
			tree.setSystemName("ค้นหาผู้ใช้");
			tree.setMenuName("ค้นหาผู้ใช้");
			tree.setFunctionName("ค้นหาผู้ใช้");
			tree.setImage("/training2018/images/treeview/i_pink.gif");
			tree.setParentId("001001002000");
			tree.setCurrentId("001001002001");
			tree.setLabel("ค้นหาผู้ใช้");
			tree.setCurrentLevel(4);
			tree.setMinLevel(1);
			tree.setMaxLevel(4);
			tree.setDescription("");
			mapTree.put(tree.getCurrentId(), tree);
			
			tree = new TutorialTree();
			tree.setOperatorLevel("4");
			tree.setListNo("7");
			tree.setSystemName("เพิ่มผู้ใช้");
			tree.setMenuName("เพิ่มผู้ใช้");
			tree.setFunctionName("ค้นหาผู้ใช้");
			tree.setImage("/training2018/images/treeview/i_pink.gif");
			tree.setParentId("001001002000");
			tree.setCurrentId("001001002002");
			tree.setLabel("เพิ่มผู้ใช้");
			tree.setCurrentLevel(4);
			tree.setMinLevel(1);
			tree.setMaxLevel(4);
			tree.setDescription("");
			mapTree.put(tree.getCurrentId(), tree);
			
//			String sql = SQLUtil.getSQLString(conn.getSchemas()
//					, sqlPath.getClassName()
//					, sqlPath.getPath()
//					, "searchTree"
//					, params);
//
//			stmt = conn.createStatement();
//			rst = stmt.executeQuery(sql);
//			String level = "0";
//			while (rst.next()) {
//				if (rst.getString("OPERATOR_LEVEL").equals("0")) {
//					continue;
//				}
//
//				TutorialTree tree = new TutorialTree();
//				tree.setGroupSystem(StringUtil.nullToString(rst.getString("GROUP_SYSTEM")));
//				tree.setOperatorType(StringUtil.nullToString(rst.getString("OPERATOR_TYPE")));
//				// tree.setPath(StringUtil.nullToString(rst.getString("PATH")));
//
//				level = StringUtil.nullToString(rst.getString("OPERATOR_LEVEL"));
//				tree.setOperatorLevel(level);
//				tree.setListNo(StringUtil.nullToString(rst.getString("LIST_NO")));
//				tree.setUrl(StringUtil.nullToString(rst.getString("URL")));
//				tree.setSystemName(StringUtil.nullToString(rst.getString("SYSTEM_NAME")));
//				tree.setMenuName(StringUtil.nullToString(rst.getString("MENU_NAME")));
//				tree.setFunctionName(StringUtil.nullToString(rst.getString("FUNCTION_NAME")));
//
//				// extend from com.cct.domain.Tree สำหรับวาด tree
//				switch (level) {
//				case "1":
//					tree.setImage("/training2018/images/treeview/i_blue.gif");
//					break;
//				case "2":
//					tree.setImage("/training2018/images/treeview/i_green.gif");
//					break;
//				case "3":
//					tree.setImage("/training2018/images/treeview/i_orange.gif");
//					break;
//				case "4":
//					tree.setImage("/training2018/images/treeview/i_pink.gif");
//					break;
//				default:
//					tree.setImage("/training2018/images/treeview/i_beige.gif");
//					break;
//				}
//
//				tree.setParentId(StringUtil.nullToString(rst.getString("PARENT_ID")));
//				tree.setCurrentId(StringUtil.nullToString(rst.getString("OPERATOR_ID")));
//				tree.setLabel(StringUtil.nullToString(rst.getString("LABEL_TH")));
//
//				tree.setCurrentLevel(Integer.valueOf(rst.getString("OPERATOR_LEVEL")));
//				tree.setMinLevel(1);
//				tree.setMaxLevel(4);
//				tree.setDescription(StringUtil.nullToString(rst.getString("DETAIL")));
//
//				mapTree.put(tree.getCurrentId(), tree);
//			}
		} catch (Exception e) {
			throw e;
		} finally {
//			CCTConnectionUtil.closeAll(rst, stmt);
		}

		return mapTree;
	}

}
