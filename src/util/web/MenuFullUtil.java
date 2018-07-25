package util.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cct.domain.Operator;

public class MenuFullUtil {
	private static final String BEGIN_LI = "<li>";
	private static final String END_LI = "</li>";
	private static final String BEGIN_UL = "<ul class='fgButton'>";
	private static final String END_UL = "</ul>";
	private static final String BEGIN_HREF = "<a id='%s' tabindex='-1' href='javascript:void(0);'>";
	private static final String BEGIN_HREF_FOR_LINK = "<a class='ui-widget' id='%s' tabindex='-1' href='%s'>";
	private static final String END_HREF = "</a>";
	private static final String JAVASCRIPT_VOID_0 = "javascript:void(0);";

	public static final String DELIMITER_FUNCTION = ",";
	public static final String OPERATOR_TYPE_MENU = "M";
	public static final String OPERATOR_TYPE_FUNCTION = "F";
	
	private static final String BEGIN_LI_TWO = "<li class='menuMain'>";
	private static final String ICON_MENU = "<span class='ui-icon ui-icon-stop' ></span>";
	

	private static final int OPERATOR_LEVEL_1 = 1;
	private String context;
	private Object[] operators;
	private Map<String, Operator> mapMenu;
	private Map<String, Operator> mapMenuLevel = new LinkedHashMap<String, Operator>();

	public MenuFullUtil() {

	}

	public MenuFullUtil(String context, Map<String, Operator> mapMenu) {
		this.context = context;
		this.mapMenu = mapMenu;
		this.operators = (Object[]) (mapMenu.values().toArray());
	}

	public String genarateMenuBar() {
		genarateItem();
		String html = "<div id='news-items' class='hidden'>";
		html += "<ul id='menu'>";
		if (mapMenuLevel.size() > 0) {
			String htmlTmp = displayOperator(mapMenuLevel);
			html += htmlTmp.substring(0, htmlTmp.length() - 10);
		}
		html += END_UL + "</div>";
		return html;
	}

	public String genarateItem() {
		String html = "";

		if (operators.length > 0) {
			int minLevel = ((Operator) operators[0]).getMinLevel();
			int maxLevel = ((Operator) operators[0]).getMaxLevel();

			for (int currentLevel = minLevel; currentLevel <= maxLevel; currentLevel++) {

				for (int index = operators.length - 1; index >= 0; index--) {

					Operator operator = (Operator) operators[index];
					//System.out.println(currentLevel + " > " + operator.getCurrentLevel() + " " + operator.getOperatorId() + " " + operator.getOperatorType());
					if (operator.getOperatorType().equals(OPERATOR_TYPE_MENU) == false) {
						continue;
					}

					if (operator.getCurrentLevel() == currentLevel) {

						if (operator.getCurrentLevel() == OPERATOR_LEVEL_1) {
							mapMenuLevel.put(operator.getOperatorId(), operator);
						} else {
							String parentOperatorIds = searchParentOperator(operator.getParentId(), operators);
							parentOperatorIds += "," + operator.getOperatorId();
							//System.out.println(currentLevel + " > " + parentOperatorIds);
							updateOperator(mapMenu, mapMenuLevel, parentOperatorIds, parentOperatorIds);
						}
					}
				}
			}
		}
		return html;
	}

	public String searchParentOperator(String parentId, Object[] listOperator) {
		String parentIds = "";

		if ((parentId != null) && (parentId.trim().length() > 0)) {

			for (Object operatorObject : listOperator) {

				Operator operator = (Operator) operatorObject;
				if (operator.getOperatorType().equals(OPERATOR_TYPE_MENU) == false) {
					continue;
				}

				if (operator.getOperatorId().equals(parentId)) {
					parentIds = parentIds + ",";
					parentIds = operator.getOperatorId() + parentIds;
					String pppId = searchParentOperator(operator.getParentId(), listOperator);
					if (pppId.length() > 0) {
						parentIds = pppId + "," + parentIds;
					}
				}
			}
			if (parentIds.length() > 0) {
				parentIds = parentIds.substring(0, parentIds.length() - 1);
			}

		}
		return parentIds;
	}

	public void updateOperator(Map<String, Operator> mapMenu, Map<String, Operator> mapMenuLevel, String parentIds, String groupParentIds) {

		if (parentIds.indexOf(",") > -1) {
			String[] operatorIds = parentIds.split(",");
			String parentOperatorId = operatorIds[0];
			String currentOperatorId = operatorIds[1];
			String groupOperatorId = groupParentIds.substring(0, groupParentIds.indexOf(currentOperatorId) + currentOperatorId.length());

			Operator operator = mapMenu.get(currentOperatorId);
			operator.setParentOperatorIds(groupOperatorId);
			if (mapMenuLevel.get(parentOperatorId) != null) {
				mapMenuLevel.get(parentOperatorId).getMapOperator().put(operator.getOperatorId(), operator);
			}

			parentIds = parentIds.substring(parentIds.indexOf(",") + 1, parentIds.length());

			if (mapMenuLevel.get(parentOperatorId) != null) {
				updateOperator(mapMenu, mapMenuLevel.get(parentOperatorId).getMapOperator(), parentIds, groupParentIds);
			}
		}
	}

	public String displayOperator(Map<String, Operator> mapMenuLevel) {

		String html = "";
		if (mapMenuLevel.size() > 0) {

			for (String operatorId : mapMenuLevel.keySet()) {

				Operator operator = mapMenuLevel.get(operatorId);
//				if (operator.getDetail().equalsIgnoreCase("N")) {
//					continue;
//				}
					
				// System.out.println(operator.getOperatorId() + " " + operator.getLabel() + " " + operator.getCurrentLevel());
				if (operator.getMapOperator().size() > 0) {
					// String tmpHtml = displayOperator(operator.getMapOperator());
					html = displayOperator(operator.getMapOperator()) + html;
					//System.out.println("hh: \n" + html);
					if (html.length() == 0) {
						// html = "<li>" + operator.getOperatorId() + " " + operator.getLabel() + " " + operator.getCurrentLevel() + "</li>" + "\n" + html;
						html = genarateMenuItem(operator) + "\n" + html;
					} else {
						// html = "<li>" + operator.getOperatorId() + " " + operator.getLabel() + " " + operator.getCurrentLevel() + "\n<ul>" + "\n" + html;
						html = genarateBeginMenuItem(operator) + "\n" + html;
					}
				} else {
					// html = "<li>" + operator.getOperatorId() + " " + operator.getLabel() + " " + operator.getCurrentLevel() + "</li>" + "\n" + html;
					html = genarateMenuItem(operator) + "\n" + html;
				}
			}
		}
		html = html + genarateEndMenuItem();
//		System.out.println("kk: \n" + html);
		return html;
	}

	public String genarateMenuItem(Operator operator) {
		String operatorIds = operator.getParentOperatorIds();
		if (operatorIds == null) {
			operatorIds = operator.getOperatorId();
		}

		String url = JAVASCRIPT_VOID_0;
		if (operator.getUrl() != null) {
			// FIXME
//			if (operator.getChkComplete().equalsIgnoreCase("Y")) {
//				url = context + "/" + operator.getUrl();
//			}
			
			// TODO uncomment for roll back
			url = context + "/" + operator.getUrl();
		}
		String link = String.format(BEGIN_HREF_FOR_LINK, operatorIds, url);
		String html = "";
		
		if (operator.getParentId() == null || operator.getParentId().equals("")) {
			html += BEGIN_LI_TWO;
			html += link + "&nbsp;" + ICON_MENU + "&nbsp;" + "<label  class='monMenu'>" + operator.getLabel() + "</label>" + END_HREF;
			html += END_LI + END_UL;
		} else {
			/*html += BEGIN_LI;
			html += link + operator.getLabel() + END_HREF;
			html += END_LI;*/
			html += BEGIN_LI;

			// FIXME
			// KAM Test เพิ่ม icon สำหรับ ที่เสร็จแล้ว
//			if (operator.getChkComplete() != null && operator.getChkComplete().equals("Y")) {
//				html += link + "&nbsp;" + ICON_MENU + "&nbsp;" + operator.getLabel() + " <img src='" + ServletActionContext.getRequest().getContextPath()
//						+ "/images/icon/i_apply.png' /> " + " " + END_HREF;
//
//			} else {
//				html += link + "&nbsp;" + ICON_MENU + "&nbsp;" + operator.getLabel() + END_HREF;
//			}
			
			// TODO uncomment for roll back
			html += link + "&nbsp;" + ICON_MENU + "&nbsp;" + operator.getLabel() + END_HREF;
			

			html += END_LI;
		}
		
		return html;
	}

	public String genarateBeginMenuItem(Operator operator) {
		String groupOperatorId = operator.getParentOperatorIds();
		if (groupOperatorId == null) {
			groupOperatorId = operator.getOperatorId();
		}
//		String link = String.format(BEGIN_HREF, groupOperatorId) + operator.getLabel() + END_HREF;
//		String html = BEGIN_LI + link + "\n" + BEGIN_UL;
		
		String html = BEGIN_LI +"<label  class='monMenu'>" + operator.getLabel() + "</label>" + "\n" + BEGIN_UL;
		
		return html;
	}

	public String genarateEndMenuItem() {
		String html = END_UL + END_LI;
		return html;
	}

	public static String searchLabel(Map<String, Operator> mapMenu, String functionCode) {
		String labelHeader = "";

		if (mapMenu.get(functionCode) == null) {
			return labelHeader;
		}

		while (mapMenu.get(functionCode).getParentId() != null && !mapMenu.get(functionCode).getParentId().equals("")) {
			Operator operator = mapMenu.get(functionCode);

			if (operator != null) {
				if (labelHeader.length() > 0) {
					labelHeader = "&nbsp;&rsaquo;&nbsp;" + labelHeader;
				}
				labelHeader = operator.getLabel() + labelHeader;
				functionCode = operator.getParentId();
			}
		}

		labelHeader = "<br>" + labelHeader;
		labelHeader = mapMenu.get(functionCode).getLabel() + labelHeader;

		return labelHeader;
	}

	public static String searchParentOperatorIds(Map<String, Operator> mapMenu, String programCode) {
		String searchParentOperatorIds = "";

		if (programCode == null) {

		} else if (mapMenu.get(programCode) == null) {

		} else {
			searchParentOperatorIds = programCode;
			while (mapMenu.get(programCode).getParentId() != null) {
				Operator operator = mapMenu.get(programCode);

				if (operator != null) {
					searchParentOperatorIds = operator.getParentId() + "," + searchParentOperatorIds;
					programCode = operator.getParentId();
				}
			}
		}
		return searchParentOperatorIds;
	}
}