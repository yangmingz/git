package com.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jxust.ssm.pojo.User;
import com.jxust.ssm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
/**
 * 测试spring整合mybatis spring方式
 * 
 * @author Peng
 * @Date2016年12月11日上午11:52:56
 */
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);

	@Resource
	private UserService userService = null;
	
	/**
	 * 测试查询
	 */
	@Test
	public void test1() {
		User user = userService.getUserById(2);
		logger.info("值：" + user.getUserName());
		System.out.println(user.getUserName());
		logger.info(JSON.toJSONString(user));
	}
	/**
	 * 测试添加
	 */
	@Test
	public void test2() {		
		User user = new User();
		user.setUserName("李白");
		user.setPassword("234234");
		user.setAge(23);
		int count = userService.insert(user);
		logger.info("count：" +count);
	}
}
