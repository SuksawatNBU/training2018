package util.web;

import java.util.LinkedHashMap;
import java.util.Map;

import com.cct.domain.Operator;

public class MenuTreeUtil {
	private static final String BEGIN_LI = "<li>";
	private static final String END_LI = "</li>";
	private static final String BEGIN_UL = "<ul>";
	private static final String END_UL = "</ul>";
	private static final String BEGIN_HREF = "<input type='checkbox' id='%s' name='operatorName' value='%s-M' onclick='clickOperator(this)'>";
	private static final String BEGIN_HREF_FOR_LINK = "<input type='checkbox'id='%s' name='operatorName' value='%s-M' onclick='clickOperator(this)'>";
	private static final String END_HREF = "";
	private static final String TAG_FUNCTION = "<li><input type='checkbox' id='%s' name='operatorName' value='%s-F' onclick='clickOperator(this)'/>%s</li>";

	public static final String DELIMITER_FUNCTION = "_";
	public static final String OPERATOR_TYPE_MENU = "M";
	public static final String OPERATOR_TYPE_FUNCTION = "F";
	
	private static final int OPERATOR_LEVEL_1 = 1;
	private String treeId;
	private Object[] operators;
	private Map<String, Operator> mapMenu;
	private Map<String, Operator> mapMenuLevel = new LinkedHashMap<String, Operator>();

	public MenuTreeUtil() {

	}

	public MenuTreeUtil(String treeId, Map<String, Operator> mapMenu) {
		this.treeId = treeId;
		this.mapMenu = mapMenu;
		this.operators = (Object[]) (mapMenu.values().toArray());
	}

	public String genarateMenuBar() {
		genarateItem();
		String html = "<ul id='" + treeId + "' class='filetree'>";
//		String html = "<div id='news-items' class='hidden'>";
		//html += "<ul id='menu'>";
		if (mapMenuLevel.size() > 0) {
			String htmlTmp = displayOperator(mapMenuLevel);
			html += htmlTmp.substring(0, htmlTmp.length() - 10);
		}
		html += END_UL;// + "</div>";
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

					if (operator.getCurrentLevel() == currentLevel) {

						if (operator.getCurrentLevel() == OPERATOR_LEVEL_1) {
							mapMenuLevel.put(operator.getOperatorId(), operator);
						} else {
							String parentOperatorIds = searchParentOperator(operator.getParentId(), operators);
							parentOperatorIds += DELIMITER_FUNCTION + operator.getOperatorId();
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
					parentIds = parentIds + DELIMITER_FUNCTION;
					parentIds = operator.getOperatorId() + parentIds;
					String pppId = searchParentOperator(operator.getParentId(), listOperator);
					if (pppId.length() > 0) {
						parentIds = pppId + DELIMITER_FUNCTION + parentIds;
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

		if (parentIds.indexOf(DELIMITER_FUNCTION) > -1) {
			String[] operatorIds = parentIds.split(DELIMITER_FUNCTION);
			String parentOperatorId = operatorIds[0];
			String currentOperatorId = operatorIds[1];
			String groupOperatorId = groupParentIds.substring(0, groupParentIds.indexOf(currentOperatorId) + currentOperatorId.length());

			Operator operator = mapMenu.get(currentOperatorId);
			operator.setParentOperatorIds(groupOperatorId);

			if (mapMenuLevel.get(parentOperatorId) != null) {
				mapMenuLevel.get(parentOperatorId).getMapOperator().put(operator.getOperatorId(), operator);
			}

			parentIds = parentIds.substring(parentIds.indexOf(DELIMITER_FUNCTION) + 1, parentIds.length());

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
				if (operator.getVisible().equals("N")) {
					continue;
				}

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

//		String url = JAVASCRIPT_VOID_0;
//		if (operator.getUrl() != null) {
//			url = treeId + "/" + operator.getUrl();
//		}

		String html = "";
		if (operator.getOperatorType().equals(OPERATOR_TYPE_MENU)) {
			String link = String.format(BEGIN_HREF_FOR_LINK, operator.getOperatorId(), operatorIds);
			//System.out.println("------M " + operator.getOperatorId());
			//System.out.println("------M " + link);
			html += BEGIN_LI;
			html += link + operator.getLabel() + END_HREF;
			html += END_LI;
			return html;
		} else if (operator.getOperatorType().equals(OPERATOR_TYPE_FUNCTION)) {
			String link = String.format(TAG_FUNCTION, operator.getOperatorId(), operatorIds, operator.getLabel());
			//System.out.println("------F " + operator.getOperatorId());
			//System.out.println("------F " + link);
			html += link;
			return html;
		}
		return html;
	}

	public String genarateBeginMenuItem(Operator operator) {
		String groupOperatorId = operator.getParentOperatorIds();
		if (groupOperatorId == null) {
			groupOperatorId = operator.getOperatorId();
		}
		//System.out.println(operator);
		String link = String.format(BEGIN_HREF, operator.getOperatorId(), groupOperatorId) + operator.getLabel() + END_HREF;
		String html = BEGIN_LI + link + "\n" + BEGIN_UL;
		return html;
	}

	public String genarateEndMenuItem() {
		String html = END_UL + END_LI;
		return html;
	}
}