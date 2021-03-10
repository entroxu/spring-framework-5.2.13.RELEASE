package org.springframework.beans;

public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		System.out.println("UserDao#setDataSource 注入");
	}


}
