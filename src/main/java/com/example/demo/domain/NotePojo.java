package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class NotePojo {
	@Id
	String noteid;
	String username;
	String title;

	public String getNoteid() {
		return noteid;
	}

	public void setNoteid(String noteid) {
		this.noteid = noteid;
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

	String content;

	public NotePojo(String id, String username, String title, String content) {
		this.noteid = id;
		this.title = title;
		this.content = content;
	}

	public NotePojo() {

	}
}
