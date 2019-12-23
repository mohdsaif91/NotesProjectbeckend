package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Noteuserpojo;

public interface NotesRepo extends JpaRepository<Noteuserpojo, Integer> {

}
