package org.springframework.beans;

public class UserService {

	private UserDao userDao;

	public UserService() {
		System.out.println("UserService构造");
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		System.out.println("UserService#setUserDao()");
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void my_init() {
		System.out.println("UserService#my_init() 自定义初始化方法被调用");
	}
}
