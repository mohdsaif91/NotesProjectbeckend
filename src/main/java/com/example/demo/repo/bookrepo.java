package com.example.demo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domain.books;

@RepositoryRestResource(path="books",collectionResourceRel = "books")
public interface bookrepo extends PagingAndSortingRepository<books, Integer>{
	
	

}
