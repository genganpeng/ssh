package cn.gap.beans;

import javax.servlet.http.HttpSession;

public class SessionUser {
	//用户名
	private String userName;
	//session的id
	private String sessionId;
	//session
	private HttpSession session;
	
	public SessionUser() {
		super();
	}
	
	public SessionUser(String userName, String sessionId, HttpSession session) {
		super();
		this.userName = userName;
		this.sessionId = sessionId;
		this.session = session;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "SessionUser [userName=" + userName + ", sessionId=" + sessionId
				+ "]";
	}

}
