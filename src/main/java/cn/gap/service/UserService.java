package cn.gap.service;

public interface UserService {
	/*
	 *  根据用户名和密码查询,如果正确返回ID，不正确则返回null
	 */
	public String findbyNameAndPassword();
}
