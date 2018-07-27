package com.ljq.test;

import org.junit.Test;

import com.ljq.dao.UserDao;
import com.ljq.po.User;

public class DaoTest {

	@Test
	public void test() {
		User user;
		UserDao ud = new UserDao();
		user = new User(0,"天啦","1","1","11111",0);
		ud.newUser(user);
//		for(int i=0;i<10;i++) {
//			user = new User("testUser"+i, "111111", "testUser"+i+"@user.com");
//			ud.newUser(user);
//		}
	}

}
