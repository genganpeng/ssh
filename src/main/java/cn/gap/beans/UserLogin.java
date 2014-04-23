package cn.gap.beans;

/**
 * 用户登陆的类
 * @author genganpeng
 *
 */
public class UserLogin {
	//用户名
	private String userName;
	//密码
	private String password;
	//登陆的ip
	private String ip;
	
	public UserLogin() {
		
	}
	
	public UserLogin(String userName, String password, String ip) {
		super();
		this.userName = userName;
		this.password = password;
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
