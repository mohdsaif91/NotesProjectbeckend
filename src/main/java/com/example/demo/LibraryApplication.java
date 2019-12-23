package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.example.demo.repo.bookrepo;

@SpringBootApplication
public class LibraryApplication {

	@Autowired
	private bookrepo bookrep;
	@Component
	class DataSetup implements ApplicationRunner{

		@Override
		public void run(ApplicationArguments args) throws Exception {
//			bookrep.save(books.builder().booknamem("the person life").title("going with the wind").build());
//			bookrep.save(books.builder().booknamem("jack").title("Cold as ice").build());
//			bookrep.save(books.builder().booknamem("thor").title("jack and jill").build());
//			bookrep.save(books.builder().booknamem("Lion king").title("going to shoping").build());
			
		}
		
	}
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
