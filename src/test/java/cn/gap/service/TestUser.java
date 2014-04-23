package cn.gap.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
	private ApplicationContext ac;
	
	@Before
	public void initialize() {
		ac = new ClassPathXmlApplicationContext("classpath:spring/*");
	}
	
	@Test
	public void findbyNameAndPassword() {
		UserService service = (UserService) ac.getBean("userService");
		service.findbyNameAndPassword();
	}
}
