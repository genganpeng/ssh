package cn.gap.service.impl;

import org.springframework.stereotype.Service;

import cn.gap.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public String findbyNameAndPassword() {
		System.out.println("这是测试的哦，亲！");
		return null;
	}

}
