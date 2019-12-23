package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Noteuserpojo;
import com.example.demo.domain.UserNamePojo;
import com.exapmle.demo.connection.ConnectionClass;

@Service
public class NoteCreateService {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	private static List<Noteuserpojo> allCources = new ArrayList<>();

	public List<Noteuserpojo> findAll(String userName) throws ClassNotFoundException, SQLException {
		String canupdate = "";
		String query = "select * from noteuserpojo where username='" + userName + "'";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		allCources = Noteuserpojo.getAllNotes(rs, canupdate);
		return allCources;
	}

	public void deleteById(String id, String username) throws ClassNotFoundException, SQLException {
		String query = "delete from noteuserpojo where id='" + id + "'";
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		deleteFromUser(id, username);
	}

	private void deleteFromUser(String id, String username) throws SQLException, ClassNotFoundException {
		String userdeletequery = "delete from " + username + " where id='" + id + "'";
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(userdeletequery);
		ps.executeUpdate();
	}

//	private NotePojo findById(String id) {
//		List<NotePojo> noteList = allCources.stream().filter(s -> s.getNoteid().equals(id))
//				.collect(Collectors.toList());
//		NotePojo np = new NotePojo();
//		noteList.forEach(n -> {
//			np.setNoteid(n.getNoteid());
//			np.setContent(n.getContent());
//			np.setUsername(n.getUsername());
//		});
//		return np;
//	}

//	public NotePojo findByIdForView(String id) {
//		List<NotePojo> viewList = allCources.stream().filter(s -> s.getNoteid().equals(id))
//				.collect(Collectors.toList());
//		NotePojo npo = new NotePojo();
//		viewList.forEach(s -> {
//			npo.setNoteid(s.getNoteid());
//			npo.setContent(s.getContent());
//			npo.setUsername(s.getUsername());
//		});
//		return npo;
//	}

	public String giverandomStringandNUmber() {
		Random r = new Random();
		int count = r.nextInt(100);
		String ALPHA_NUMRIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder();
		sb.append("a");
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMRIC_STRING.length());
			sb.append(ALPHA_NUMRIC_STRING.charAt(character));
		}

		return sb.toString();
	}

	public String getuserAuth(String uname, String pass) throws ClassNotFoundException, SQLException {
		String res = "";
		String query = "select * from signup_pojo where user_name='" + uname + "' and password='" + pass + "'";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = "auth";
		} else {
			res = "noauth";
		}
		return res;
	}

	public void createTable(String tablename, String Uname) throws ClassNotFoundException, SQLException {
		String query = "create table " + tablename
				+ " (id serial,username text,update text,share text,delete text,read text)";
		System.out.println(query);
		try {
			con = ConnectionClass.getConnection();
			ps = con.prepareStatement(query);
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			String insertquery = "insert into " + tablename + " (username,update,share,delete,read)values('" + Uname
					+ "','yes','yes','yes','yes');";
			System.out.println(insertquery);
			con = ConnectionClass.getConnection();
			ps = con.prepareStatement(insertquery);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertIntoUser(String username, Noteuserpojo nup, String id)
			throws ClassNotFoundException, SQLException {
		String title = nup.getTitle();
		String content = nup.getContent();
		String query = "insert into " + username + " (id,title,content) values('" + id + "','" + title + "','" + content
				+ "')";
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		ps.execute();
	}

	public void UpdateNote(Noteuserpojo nup) throws ClassNotFoundException, SQLException {
		String content = nup.getContent();
		String title = nup.getTitle();
		String id = nup.getId();
		String username = nup.getUsername();
		String query = "update noteuserpojo set content='" + content + "',title='" + title + "' where id='" + id
				+ "' and username='" + username + "' ";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		updateUser(nup, id, username, content, title);
	}

	private void updateUser(Noteuserpojo nup, String id, String username, String content, String title)
			throws ClassNotFoundException, SQLException {
		String userupdateQuery = "update " + username + " set title='" + title + "',content='" + content
				+ "' where id='" + id + "'";
		System.out.println(userupdateQuery);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(userupdateQuery);
		ps.executeUpdate();

	}

	public List<UserNamePojo> getAllUser() throws ClassNotFoundException, SQLException {
		List<UserNamePojo> Ulist = new ArrayList<>();
		Ulist.clear();
		String query = "select user_name from signup_pojo";
		System.out.println(query);
		con = ConnectionClass.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		Ulist = UserNamePojo.getAllUser(rs);
		return Ulist;
	}

}
