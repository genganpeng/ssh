package cn.gap.action.login;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.gap.beans.UserLogin;
import cn.gap.service.UserService;
import cn.gap.tool.Cookies;
import cn.gap.tool.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登陆类
 * @author genganpeng
 *
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,SessionAware {
	private String userName;
	private String password;
	private boolean userCookie;
	private boolean flag;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> session;
	private Cookies cookies;
    public static final String USER_SESSION = "user.session";  
    
    @Autowired
    private UserService userService;
	
    /**
     * 进入首页后自动登陆，如果cookie为空或者cookie中用户名和密码不正确的话就昂重新登陆
     * @return
     */
    public String autoLogin() {
    	cookies = new Cookies(request, response);
    	Cookie cookie = cookies.getCookie(Cookies.USER_COOKIE);
    	if (cookie == null) {
    		return "login";
    	}
    	else {
    		String array[] = cookie.getValue().split(",");
    		userName = array[0];
    		password = array[1];
    		if (!userName.equals("admin") || !password.equals("admin")) {
    			return "login";
    		}
    		System.out.println(userName + "\t" + password);
			session.put(USER_SESSION, new UserLogin(userName, password, request.getRemoteAddr()));
    	}
    	return SUCCESS;
    }
    
    /**
     * 用户登陆，如果用户名和密码正确而且用户选择自动登陆，则保存cookie
     * @return
     */
	public String login() {
		System.out.println(userService.findbyNameAndPassword());
		System.out.println(userName + "\t" + password + "\t" + userCookie);
		userName = userName.trim();
		if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
			if (userName.equals("admin") && password.equals("admin")) {
				if (userCookie) {
					System.out.println("添加cookie");
					cookies = new Cookies(request, response);
					//cookie的有效期为一天
					cookies.add(Cookies.USER_COOKIE, userName + "," + password, 10);
				}
				session.put(USER_SESSION, new UserLogin(userName, password, request.getRemoteAddr()));
			}
			flag = true;
			return SUCCESS;
			
		}
		flag = false;
		return ERROR;
	}
	
	/**
	 * 用户退出，清空session和该用户的cookie
	 * @return
	 */
	public String logout() {
		//删除session和cookie
		HttpSession httpSession = request.getSession();
		if (httpSession != null) {
			httpSession.removeAttribute(USER_SESSION);
		}
		cookies = new Cookies(request, response);
		Cookie cookie = cookies.getCookie(Cookies.USER_COOKIE);
		if (cookie != null)
			cookies.delete(cookie);
		return SUCCESS;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isUserCookie() {
		return userCookie;
	}

	public void setUserCookie(boolean userCookie) {
		this.userCookie = userCookie;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
}
