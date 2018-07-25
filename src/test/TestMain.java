package test;

import java.util.Locale;
import java.util.ResourceBundle;

import util.bundle.BundleUtil;
import util.calendar.CalendarUtil;

public class TestMain {
	public static void main(String[] args) {
		try {
//			ResourceBundle bundle = BundleUtil.getBundle("D:/My Projects/12-[EXAM]-WebFramwork/02-SourceCode/WorkSpace/training2018/src/resources/bundle/common/Message", null);
//			System.out.println(bundle.getString("xxx"));
			
			CalendarUtil.convertDateString("2560-0516", "YYYY-MM-DD", new Locale("th", "TH"), "DD/MM/YYYY HH:mm:ss", Locale.ENGLISH);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
