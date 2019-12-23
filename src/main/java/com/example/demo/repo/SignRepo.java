package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.SignupPojo;

public interface SignRepo extends JpaRepository<SignupPojo, Integer>{

}
