package com.ljq.service;

import com.ljq.dao.UserDao;
import com.ljq.po.User;

public class UserService {
	
	private UserDao ud;
	
	public UserService() {
		super();
		ud = new UserDao();
		// TODO Auto-generated constructor stub
	}

	public void newUser(User user) {
		ud.newUser(user);
	}
	
	public boolean login(User user) {
		User u = ud.getUserByUserName(user.getUsername());
		if(u==null) {
			return false;
		}
		if(!u.getPassword().equals(user.getPassword())) {
			return false;
		}
		if(u.getStatus()==1) {
			return true;
		}
		return false;
	}
	
	public boolean checkUserName(String username) {
		User user = ud.getUserByUserName(username);
		if(user==null) {
			return true;
		}
		return false;
	}
	
	public void updateUser(User user) {
		ud.updateUser(user);
	}
	
	public User getUserByCode(String code) {
		return ud.getUserByCode(code);
	}
	
	public User getUserByUserName(String username) {
		return ud.getUserByUserName(username);
	}
}
