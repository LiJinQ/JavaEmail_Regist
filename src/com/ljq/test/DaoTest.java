package com.ljq.test;

import org.junit.Test;

import com.ljq.dao.UserDao;
import com.ljq.po.User;

public class DaoTest {

	@Test
	public void test() {
		User user;
		UserDao ud = new UserDao();
		user = new User(2,"天啦","1","1","11111",0);
		user.setEmail("22222");
		ud.updateUserByDbutil(user);
	}

}
