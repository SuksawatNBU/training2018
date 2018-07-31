package util.web;

import java.util.LinkedHashMap;
import java.util.Map;

import com.cct.domain.Tree;

public class TreeUtil {

	public static final String TYPE_CHECKBOX = "CHECKBOX";
	public static final String TYPE_SPAN = "SPAN";
	public static final String TYPE_IMAGE = "IMAGE";

	public static final String DELIMITER_BREAK_ID = "_";
	private static final int DATA_LEVEL_1 = 1;

	private String treeType = "";
	private String treeId = "";
	private String event = "";
	private Object[] datas;
	private Map<String, Tree> mapData = new LinkedHashMap<String, Tree>();
	private Map<String, Object> mapDataLevel = new LinkedHashMap<String, Object>();

	private static final String BEGIN_UL = "<ul>";
	private static final String END_UL = "</ul>";
	private static final String BEGIN_LI = "<li>";
	private static final String END_LI = "</li>";

	private static final String TAG_CHECKBOX = "<input type='checkbox' id='%s' name='%s_CHECKBOX' value='%s' />";
	private static final String TAG_CHECKBOX_CHILD = "<li>" + TAG_CHECKBOX + "%s</li>";

	private static final String TAG_SPAN = "<span data-tooltip='%s' data-tooltip-position='right' id='%s' class='%s_SPAN' value='%s' style='cursor: pointer;'>%s<input type='hidden' name='HIDDEN_%s' value='%s'></span>";
	private static final String TAG_SPAN_CHILD = "<li>" + TAG_SPAN + "</li>";
	
	private static final String TAG_SPAN_EVENT = "<span data-tooltip='%s' data-tooltip-position='right' id='%s' class='%s_SPAN' value='%s' style='cursor: pointer;' onclick='%s(%s)'>%s<input type='hidden' name='HIDDEN_%s' value='%s'></span>";
	private static final String TAG_SPAN_EVENT_CHILD = "<li>" + TAG_SPAN_EVENT + "</li>";

	private static final String TAG_IMAGE = "<img name='%s_IMG' src='%s' style='cursor: pointer;'/>&nbsp;" + TAG_SPAN;
	private static final String TAG_IMAGE_CHILD = "<li>" + TAG_IMAGE + "</li>";
	
	private static final String TAG_IMAGE_EVENT = "<img name='%s_IMG' src='%s' style='cursor: pointer;'/>&nbsp;" + TAG_SPAN_EVENT;
	private static final String TAG_IMAGE_EVENT_CHILD = "<li>" + TAG_IMAGE_EVENT + "</li>";

	public TreeUtil() {

	}

	public TreeUtil(String treeId, String treeType, Map<String, Tree> mapData, String eventOnclick) {
		this.treeId = treeId;
		this.treeType = treeType;
		this.mapData = mapData;
		this.event = eventOnclick;
		this.datas = (Object[]) (mapData.values().toArray());
	}

	public String genarateTree() {
		genarateItem();
		String html = "<ul id='" + treeId + "'>";
		if (mapDataLevel.size() > 0) {
			String htmlTmp = displayData(mapDataLevel);

			// ลบ Tag </ul> และ </li> ที่เกินออก
			html += htmlTmp.substring(0, htmlTmp.length() - 10);
			// System.out.println(html);
		}
		html += END_UL;

		return html;
	}

	private void genarateItem() {
		if (datas.length > 0) {
			int minLevel = ((Tree) datas[0]).getMinLevel();
			int maxLevel = ((Tree) datas[0]).getMaxLevel();

			for (int currentLevel = minLevel; currentLevel <= maxLevel; currentLevel++) {

				for (int index = datas.length - 1; index >= 0; index--) {

					Tree tree = (Tree) datas[index];
//					 System.out.println(currentLevel + " > " + tree.getCurrentLevel() + " " + tree.getCurrentId());

					if (tree.getCurrentLevel() == currentLevel) {

						if (tree.getCurrentLevel() == DATA_LEVEL_1) {
							mapDataLevel.put(tree.getCurrentId(), tree);
						} else {
							String parentIds = searchParentData(tree.getParentId(), datas);
							parentIds += DELIMITER_BREAK_ID + tree.getCurrentId();
							// System.out.println(currentLevel + " > " +
							// parentIds);
							updateData(mapData, mapDataLevel, parentIds, parentIds);
						}
					}
				}
			}
		}
	}

	private String searchParentData(String parentId, Object[] listData) {
		String parentIds = "";

		if ((parentId != null) && (parentId.trim().length() > 0)) {

			for (Object dataObject : listData) {

				Tree tree = (Tree) dataObject;

				if (tree.getCurrentId().equals(parentId)) {
					parentIds = parentIds + DELIMITER_BREAK_ID;
					parentIds = tree.getCurrentId() + parentIds;
					String pppId = searchParentData(tree.getParentId(), listData);
					if (pppId.length() > 0) {
						parentIds = pppId + DELIMITER_BREAK_ID + parentIds;
					}
				}
			}
			if (parentIds.length() > 0) {
				parentIds = parentIds.substring(0, parentIds.length() - 1);
			}

		}
		return parentIds;
	}

	private void updateData(Map<String, Tree> mapData, Map<String, Object> mapDataLevel, String parentIds, String groupParentIds) {
		if (parentIds.indexOf(DELIMITER_BREAK_ID) > -1) {
			String[] arrayIds = parentIds.split(DELIMITER_BREAK_ID);
			String parentId = arrayIds[0];
			String currentId = arrayIds[1];
			String groupId = groupParentIds.substring(0, groupParentIds.indexOf(currentId) + currentId.length());

			Tree tree = mapData.get(currentId);
			tree.setParentIds(groupId);

			if (mapDataLevel.get(parentId) != null) {
				((Tree) mapDataLevel.get(parentId)).getMapChild().put(tree.getCurrentId(), tree);
			}

			parentIds = parentIds.substring(parentIds.indexOf(DELIMITER_BREAK_ID) + 1, parentIds.length());

			if (mapDataLevel.get(parentId) != null) {
				updateData(mapData, ((Tree) mapDataLevel.get(parentId)).getMapChild(), parentIds, groupParentIds);
			}
		}
	}

	private String displayData(Map<String, Object> mapDataLevel) {

		String html = "";
		if (mapDataLevel.size() > 0) {

			for (String currentId : mapDataLevel.keySet()) {

				Tree tree = (Tree) mapDataLevel.get(currentId);
				// System.out.println(tree.getCurrentId() + " " +
				// tree.getLabel() + " " + tree.getCurrentLevel());
				if (tree.getMapChild().size() > 0) {
					html = displayData(tree.getMapChild()) + html;
					// System.out.println("hh: \n" + html);
					if (html.length() == 0) {
						html = genarateDataItem(tree) + "\n" + html;
					} else {
						html = genarateBeginDataItem(tree) + "\n" + html;
					}
				} else {
					html = genarateDataItem(tree) + "\n" + html;
				}
			}
		}
		html = html + genarateEndDataItem();
		// System.out.println("kk: \n" + html);
		return html;
	}

	private String genarateDataItem(Tree tree) {
		String parentIds = tree.getParentIds();
		if (parentIds == null) {
			parentIds = tree.getCurrentId();
		}

		String html = "";
		if (haveChild(tree.getCurrentId())) {
			// System.out.println("------M " + tree.getCurrentId());
			html += BEGIN_LI;
			if (treeType.equals(TYPE_CHECKBOX)) {
				html += String.format(TAG_CHECKBOX, tree.getCurrentId(), treeId, parentIds);
				html += "<span data-tooltip='"+ tree.getDescription() +"' data-tooltip-position='right' class='" + treeId + "_SPAN'>" + tree.getLabel() + "</span>";

			} else if (treeType.equals(TYPE_SPAN)) {
				if (event.equals("")) {
					html += String.format(TAG_SPAN, tree.getDescription(), tree.getCurrentId(), treeId, parentIds, tree.getLabel(), tree.getCurrentId(), parentIds);
				} else {
					html += String.format(TAG_SPAN_EVENT, tree.getDescription(), tree.getCurrentId(), treeId, parentIds, event, tree.getCurrentId(), tree.getLabel(), tree.getCurrentId(), parentIds);
				}
			} else if (treeType.equals(TYPE_IMAGE)) {
				if (event.equals("")) {
					html += String.format(TAG_IMAGE, treeId, tree.getImage(), tree.getDescription(), tree.getCurrentId(), treeId, parentIds, tree.getLabel(), tree.getCurrentId(), tree.getParentIds());
				} else {
					html += String.format(TAG_IMAGE_EVENT, treeId, tree.getImage(), tree.getDescription(), tree.getCurrentId(), treeId, parentIds, event, tree.getCurrentId(), tree.getLabel(), tree.getCurrentId(), tree.getParentIds());
				}
			}

			html += END_LI;
			return html;
		} else {
			// System.out.println("------F " + tree.getCurrentId());
			if (treeType.equals(TYPE_CHECKBOX)) {
				// html += String.format(TAG_CHECKBOX_CHILD, tree.getCurrentId(), treeId, tree.getParentIds(), tree.getLabel());
				html += String.format(TAG_CHECKBOX_CHILD, tree.getCurrentId(), treeId, tree.getParentIds(), "<span data-tooltip-position='right' data-tooltip='"+ tree.getDescription() +"' class='" + treeId + "_SPAN'>" + tree.getLabel() + "</span>");
			} else if (treeType.equals(TYPE_SPAN)) {
				if (event.equals("")) {
					html += String.format(TAG_SPAN_CHILD, tree.getDescription(), tree.getCurrentId(), treeId, tree.getParentIds(), tree.getLabel(), tree.getCurrentId(), parentIds);
				} else {
					html += String.format(TAG_SPAN_EVENT_CHILD, tree.getDescription(), tree.getCurrentId(), treeId, tree.getParentIds(), event, tree.getCurrentId(), tree.getLabel(), tree.getCurrentId(), parentIds);
				}
			} else if (treeType.equals(TYPE_IMAGE)) {
				if (event.equals("")) {
					html += String.format(TAG_IMAGE_CHILD, treeId, tree.getImage(), tree.getDescription(), tree.getCurrentId(), treeId, tree.getParentIds(), tree.getLabel(), tree.getCurrentId(), tree.getParentIds());
				} else {
					html += String.format(TAG_IMAGE_EVENT_CHILD, treeId, tree.getImage(), tree.getDescription(), tree.getCurrentId(), treeId, tree.getParentIds(), event, tree.getCurrentId(), tree.getLabel(), tree.getCurrentId(), tree.getParentIds());					
				}
			}
			return html;
		}
	}

	private boolean haveChild(String currentId) {
		boolean have = true;
		if (mapData.get(currentId) == null) {
			have = false;
		}
		return have;
	}

	private String genarateBeginDataItem(Tree tree) {
		String groupId = tree.getParentIds();
		if (groupId == null) {
			groupId = tree.getCurrentId();
		}
		String tag = "";
		if (treeType.equals(TYPE_CHECKBOX)) {
			tag += String.format(TAG_CHECKBOX, tree.getCurrentId(), treeId, groupId) + "<span data-tooltip-position='right' data-tooltip='"+ tree.getDescription() +"' class='" + treeId + "_SPAN'>" + tree.getLabel() + "</span>";

		} else if (treeType.equals(TYPE_SPAN)) {
			tag += String.format(TAG_SPAN, tree.getDescription(), tree.getCurrentId(), treeId, groupId, tree.getLabel(), tree.getCurrentId(), groupId);
		} else if (treeType.equals(TYPE_IMAGE)) {
			tag += String.format(TAG_IMAGE, treeId, tree.getImage(), tree.getDescription(), tree.getCurrentId(), treeId, groupId, tree.getLabel(), tree.getCurrentId(), groupId);
		}

		String html = BEGIN_LI + tag + "\n" + BEGIN_UL;

		return html;
	}

	private String genarateEndDataItem() {
		String html = END_UL + END_LI;
		return html;
	}
}