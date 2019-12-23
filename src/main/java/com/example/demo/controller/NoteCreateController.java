package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.NotePojo;

@RestController
public class NoteCreateController {

	private static List<NotePojo> noteList = new ArrayList<>();
	private static int idcounter = 0;
	static {
		noteList.add(new NotePojo("1", "SaifFarooqui", "He Lives in the Jungle and Kil Animals", ""));
		noteList.add(new NotePojo("2", "FaizFarooqui", "He Lives in the Jungle and Kil Animals", ""));
		noteList.add(new NotePojo("3", "Kiran", "Nageshwar Tewar Loves You", ""));
		noteList.add(new NotePojo("4", "Twinkel", "He Lives in the Jungle and Kil Animals", ""));
	}

	public List<NotePojo> getAllNotes() {
		return noteList;
	}

}
