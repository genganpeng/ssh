package cn.gap.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.gap.action.login.LoginAction;
import cn.gap.beans.SessionUser;
import cn.gap.beans.UserLogin;

/**
 * 管理所有的session
 * @author genganpeng
 *
 */
public class SessionManage implements HttpSessionAttributeListener {
	private static List<SessionUser> sessionUserList;
	
	static {
		sessionUserList = Collections.synchronizedList(new ArrayList<SessionUser>());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("start add session attr。。。");
		HttpSession session = se.getSession();
		String attrName= se.getName();
		//添加用户登陆的session属性
		if (attrName.equals(LoginAction.USER_SESSION)) {
			UserLogin userLogin = (UserLogin)session.getAttribute(LoginAction.USER_SESSION);
			for (int i = sessionUserList.size() - 1; i >=0; i--) {
				SessionUser sessionUser = sessionUserList.get(i);
				//该用户已经登陆,就将之前用户登陆的session失效
				if (sessionUser.getUserName().equals(userLogin.getUserName())) {
					System.out.println("之前的用户登陆的失效！。。。"+sessionUser.getSessionId());
					try {
						sessionUser.getSession().invalidate();
					} catch (Exception e) {
						System.out.println("用户已经失效！。。。");
					}
					sessionUserList.remove(i);
					break;
				}
			}
			
			SessionUser sessionUser = new SessionUser(userLogin.getUserName(), session.getId(), session);
			sessionUserList.add(sessionUser);
			System.out.println(sessionUser.toString());
		}
		System.out.println("ended add session attr...");
		
	}

	@Override
	/**
	 *  Called after the attribute is removed
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("delete session attr...");
		HttpSession session = se.getSession();
		if (se.getName().equals(LoginAction.USER_SESSION)) {
			for (int i = sessionUserList.size() - 1; i >= 0; i--) {
				SessionUser sessionUser = sessionUserList.get(i);
				if (sessionUser.getSessionId().equals(session.getId())) {
					sessionUserList.remove(i);
					System.out.println("由于session过期或者登出而导致session失效。。。"+session.getId());
					break;
				}
			}
		}
		System.out.println("end delete session attr...");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

}
