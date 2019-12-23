package com.example.demo.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Noteuserpojo {
	
	@Id
	String id;
	String title;
	String content;
	String username; 
	String canupdate;
	public String getCanupdate() {
		return canupdate;
	}
	public void setCanupdate(String canupdate) {
		this.canupdate = canupdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static List<Noteuserpojo> getAllNotes(ResultSet rs,String canupdate) throws SQLException {
		List<Noteuserpojo> nList = new ArrayList<>();
		while (rs.next()) {
			Noteuserpojo nup = new Noteuserpojo();
			nup.setId(rs.getString("id"));
			nup.setContent(rs.getString("content"));
			nup.setTitle(rs.getString("title"));
			nup.setUsername(rs.getString("username"));
			System.out.println("inside Pojo classs "+canupdate);
			nup.setCanupdate(canupdate);
			nList.add(nup);
		}
		return nList;
	}
}
