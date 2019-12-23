package com.example.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.NotePojo;
import com.example.demo.domain.Noteuserpojo;
import com.example.demo.domain.NotificationPojo;
import com.example.demo.domain.SignupPojo;
import com.example.demo.domain.UserNamePojo;
import com.example.demo.repo.NotesRepo;
import com.example.demo.repo.SignRepo;
import com.example.demo.service.NoteCreateService;
import com.example.demo.service.NoteShareService;
import com.example.demo.service.SignUpService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Component
public class bookcontroller {

	List<NotePojo> allCources = new ArrayList<>();
	@Autowired
	SignRepo sr;
	@Autowired
	NotesRepo nr;
//	@Autowired
//	private bookrepo bkr;
	@Autowired
	NoteCreateService ncs;
	@Autowired
	NoteShareService nss;
//	@RequestMapping("/notes/{id}")
//	@ResponseBody
//	public Optional<books> hj(@PathVariable("id") int id) {
//		return bkr.findById(id);
//	}

	@PutMapping("/update/")
	public String updatestudent(@RequestBody Noteuserpojo nu) throws ClassNotFoundException, SQLException {
		ncs.UpdateNote(nu);
		return "Note Updated";
	}

	SignUpService sus = new SignUpService();

	@RequestMapping("/mynotes/{userName}")
	public List<Noteuserpojo> getAllCources(@PathVariable String userName) throws ClassNotFoundException, SQLException {
		return ncs.findAll(userName);
	}

	@DeleteMapping("/delete/{id}/{username}")
	public String deleteNotes(@PathVariable String id, @PathVariable String username)
			throws ClassNotFoundException, SQLException {
		ncs.deleteById(id, username);
		return "Note Deleted !";
	}

//	@GetMapping("/get/{id}")
//	public NotePojo getnoteIfViewed(@PathVariable String id) {
//		NotePojo np = ncs.findByIdForView(id);
//		return np;
//	}

	@PostMapping("/signup/")
	public void signUp(@RequestBody SignupPojo state) throws ClassNotFoundException, SQLException {
		SignupPojo sp = state;
		String username = state.getUserName();
		sus.createTableuser(username);
		sr.save(sp);
	}

	@GetMapping("/signin/{username}/{password}")
	public String signin(@PathVariable String username, @PathVariable String password)
			throws ClassNotFoundException, SQLException {
		String res = ncs.getuserAuth(username, password);
		System.out.println(res);
		return res;
	}

	@PostMapping("/createNote/")
	public String createNote(@RequestBody Noteuserpojo np) throws ClassNotFoundException, SQLException {
		String uid = ncs.giverandomStringandNUmber();
		System.out.println("Unique Id " + uid);
		String userName = np.getUsername();
		Noteuserpojo nup = new Noteuserpojo();
		nup.setId(uid);
		nup.setTitle(np.getTitle());
		nup.setContent(np.getContent());
		nup.setUsername(userName);
		ncs.insertIntoUser(userName, nup, uid);
		nr.save(nup);
		ncs.createTable(uid, userName);
		return "Note saved !";
	}

	@GetMapping("/share")
	public List<UserNamePojo> getUserList() throws ClassNotFoundException, SQLException {
		List<UserNamePojo> li = new ArrayList<>();
		li = ncs.getAllUser();
		return li;
	}

	@PutMapping("/sharenote/{id}/{title}/{uname}/")
	public String savesharedata(@PathVariable String id, @PathVariable String title, @PathVariable String uname,
			@RequestBody List<String> unp) {
		nss.updateAllNote(unp, id, title, uname);
		return "Shared Notes!";
	}

	@GetMapping("/notification/{uname}")
	public List<NotificationPojo> getAllNoti(@PathVariable String uname) throws ClassNotFoundException, SQLException {
		System.out.println("Called Notification Method");
		List<NotificationPojo> gotAllNoti = new ArrayList<>();
		gotAllNoti.clear();
		gotAllNoti = nss.getAllNoti(uname);
		return gotAllNoti;
	}

	@GetMapping("/viewnote/{id}/{username}/{ogusername}")
	public List<Noteuserpojo> getAllNote(@PathVariable String id, @PathVariable String username,
			@PathVariable String ogusername) throws ClassNotFoundException, SQLException {
		List<Noteuserpojo> list = new ArrayList<>();
		list = nss.getsingleNote(id, username, ogusername);
		return list;
	}

	@PutMapping("/updatecontribute/{id}/{uname}/")
	public String updateContribute(@PathVariable String id, @PathVariable String uname,
			@RequestBody List<String> unpList) {
		String res = nss.updatecontribute(id, uname, unpList);
		return res;
	}

	@GetMapping("/getAllNotes")
	public List<Noteuserpojo> getAllNotes() throws ClassNotFoundException, SQLException {
		System.out.println("called Method getAllNotes");
		List<Noteuserpojo> getList = new ArrayList<>();
		getList.clear();
		getList = nss.getAllnotes();
		return getList;
	}

	@PutMapping("/updatemain/")
	public String updateMain(@RequestBody Noteuserpojo nup) throws ClassNotFoundException, SQLException {
		String i=nss.updatemain(nup);
		return i;
	}
}
