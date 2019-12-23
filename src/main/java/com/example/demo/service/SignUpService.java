package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.exapmle.demo.connection.ConnectionClass;

public class SignUpService {
	Connection con;
	PreparedStatement ps;

	public void createTableuser(String userName) throws ClassNotFoundException, SQLException {
		String query = "create table " + userName + " (id text,title text,content text)";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		ps.execute();
		createNoteTable(userName);
	}

	private void createNoteTable(String uName) throws SQLException, ClassNotFoundException {
		String query = "create table " + uName + "Note(id serial,noteid text,read boolean,username text,notetitle text)";
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		ps.execute();
	}

}
