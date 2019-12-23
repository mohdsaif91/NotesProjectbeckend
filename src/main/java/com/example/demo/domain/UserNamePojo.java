package com.example.demo.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserNamePojo {

	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static List<UserNamePojo> getAllUser(ResultSet rs) throws SQLException {
		List<UserNamePojo> uList = new ArrayList<>();
		uList.clear();
		while (rs.next()) {
			UserNamePojo unp = new UserNamePojo();
			unp.username = rs.getString("user_name");
			uList.add(unp);
		}
		return uList;
	}
}
