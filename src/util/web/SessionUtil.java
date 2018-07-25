package util.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class SessionUtil {

	/**
	 * สำหรับเก็บค่าลงใน session ผ่าน Action class เช่น การเรียกใช้ผ่าน Action ต่างๆ ของ struts
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	/**
	 * สำหรับดึงค่าจาก session ผ่าน Action class เช่น การเรียกใช้ผ่าน Action ต่างๆ ของ struts
	 * @param key
	 * @param value
	 */
	public static Object get(String key) {
		return ActionContext.getContext().getSession().get(key);
	}

	/**
	 * สำหรับดึง parameter ที่ส่งมากับ request
	 * @param key
	 * @return
	 */
	public static String requestParameter(String key) {
		return  ServletActionContext.getRequest().getParameter(key);
	}

	/**
	 * สำหรับเก็บค่าลงใน session ผ่าน http request เช่น การเรียกใช้ผ่าน servlet class
	 * @param key
	 * @param value
	 */
	public static void setAttribute(String key, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(key, value);
	}

	/**
	 * สำหรับเก็บค่าลงใน session ผ่าน http request เช่น การเรียกใช้ผ่าน servlet class
	 * @param key
	 */
	public static Object getAttribute(String key) {
		return ServletActionContext.getRequest().getSession().getAttribute(key);
	}
	
	/**
	 * สำหรับดึงชื่อ context ของ web นั้น
	 * @param key
	 * @param value
	 */
	public static String getContextName() {
		return ServletActionContext.getRequest().getContextPath();
	}

	public static Map<String, Object> get() {
		return ActionContext.getContext().getSession();
	}

	public static void remove(String key) {
		ActionContext.getContext().getSession().remove(key);
	}

	public static void removes() {
		Map<String, Object> mapObject = get();
		for (String key : mapObject.keySet()) {
			remove(key);
		}
	}

	public static void removesIgnore(String keyIgnore) {
		Map<String, Object> mapObject = get();
		for (String key : mapObject.keySet()) {
			if (keyIgnore.equals(key)) {
				continue;
			}
			remove(key);
		}
	}

	public static void removesIgnore(String[] keyIgnores) {
		Map<String, Object> mapObject = get();
		for (String key : mapObject.keySet()) {
			if (haveKeyIn(key, keyIgnores)) {
				continue;
			}
			remove(key);
		}
	}

	private static boolean haveKeyIn(String key, String[] keyIgnores) {
		boolean haveKey = false;
		for (String keyIgnore : keyIgnores) {
			if (key.equals(keyIgnore)) {
				haveKey = true;
				break;
			}
		}
		return haveKey;
	}

	public static String getId() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return session.getId();
	}

	public static void setTimeout(int timeout) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setMaxInactiveInterval(timeout);
	}

	public static long getCreationTime() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return session.getCreationTime();
	}

	public static long getLastAccessedTime() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return session.getLastAccessedTime();
	}

	public static String getSeverIpLocal() {
		String localAddr = ServletActionContext.getRequest().getLocalAddr();
		return localAddr;
	}

	public static String getSeverIpRemote() {
		String remoteAddr = ServletActionContext.getRequest().getRemoteAddr();
		return remoteAddr;
	}
}
