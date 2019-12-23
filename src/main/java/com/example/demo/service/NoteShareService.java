package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Noteuserpojo;
import com.example.demo.domain.NotificationPojo;
import com.example.demo.domain.UserNamePojo;
import com.exapmle.demo.connection.ConnectionClass;

@Service
public class NoteShareService {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public void updateAllNote(List<String> unp, String id, String title, String uname) {
		unp.forEach(s -> {
			String query = "insert into " + s + "note(noteid,read,notetitle,username) values('" + id + "',false,'"
					+ title + "','" + uname + "')";
			System.out.println(query);
			try {
				con = ConnectionClass.getConnection();
				ps = con.prepareCall(query);
				ps.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public List<NotificationPojo> getAllNoti(String uname) throws ClassNotFoundException, SQLException {
		List<NotificationPojo> getAll = new ArrayList<>();
		String query = "select * from " + uname + "note where read=false";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		getAll = NotificationPojo.getNoti(rs);
		getAll.forEach(s -> {
			System.out.println(s.getNoteid());
		});
		return getAll;
	}

	public List<Noteuserpojo> getsingleNote(String id, String uname, String ogusername)
			throws ClassNotFoundException, SQLException {
		List<Noteuserpojo> getsinglelist = new ArrayList<>();
		getsinglelist.clear();
		String canupdate = getCanupdate(id, ogusername);
		String query = "select * from noteuserpojo where id='" + id + "' and username='" + uname + "'";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		System.out.println("can Update " + canupdate);
		getsinglelist = Noteuserpojo.getAllNotes(rs, canupdate);
		return getsinglelist;
	}

	private String getCanupdate(String id, String uname) throws ClassNotFoundException, SQLException {
		String canupdate = "";
		String update = "";
		String query = "select update from " + id + " where username='" + uname + "'";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			update = rs.getString("update");
		}
		if (update.equals("yes") && update != null) {
			canupdate = "yes";
		} else {
			System.out.println("second wala print");
			canupdate = "d-none";
		}
		return canupdate;
	}

	public String updatecontribute(String id, String username, List<String> unpList) {
		unpList.forEach(s -> {
			int i = 0;
			i++;
			String query = "update " + id + " set username='" + s + "',update='yes',delete='no',share='no',read='yes'";
			try {
				System.out.println(query + " ->" + i);
				con = ConnectionClass.getConnection();
				ps = con.prepareStatement(query);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return "Updated Your Contribute !";
	}

	public List<Noteuserpojo> getAllnotes() throws ClassNotFoundException, SQLException {
		List<Noteuserpojo> noteList = new ArrayList<>();
		noteList.clear();
		String query = "select * from noteuserpojo";
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		String canupdate = "";
		noteList = Noteuserpojo.getAllNotes(rs, canupdate);
		return noteList;
	}

	public String updatemain(Noteuserpojo nup) throws ClassNotFoundException, SQLException {
		String msg = "";
		String id = nup.getId();
		String title = nup.getTitle();
		String content = nup.getContent();
		String query = "update noteuserpojo set content='" + content + "',title='" + title + "' where id='" + id + "'";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		int i = ps.executeUpdate();
		if (i != 0) {
			msg = "updated";
		} else {
			msg = "note updates";
		}
		return msg;
	}
}
