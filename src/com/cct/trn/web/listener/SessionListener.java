package com.cct.trn.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import util.log.LogUtil;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		LogUtil.SESSION_LISTENER.info("Session Created: " + event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		LogUtil.SESSION_LISTENER.info("Session Destroyed: " + event.getSession().getId());
	}

}
