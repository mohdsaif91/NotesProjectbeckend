package com.example.demo.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationPojo {
	
	int id;
	String noteid;
	Boolean read;
	String username;
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNoteid() {
		return noteid;
	}
	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public static List<NotificationPojo> getNoti(ResultSet rs) throws SQLException{
		List<NotificationPojo> nList=new ArrayList<>();
		nList.clear();
		while(rs.next()) {
			NotificationPojo np=new NotificationPojo();
			np.id=rs.getInt("id");
			np.noteid=rs.getString("noteid");
			np.read=rs.getBoolean("read");
			np.username=rs.getString("username");
			np.title=rs.getString("notetitle");
			nList.add(np);
		}
		
		return nList;
	}
}
