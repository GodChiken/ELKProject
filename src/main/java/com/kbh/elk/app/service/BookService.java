package com.kbh.elk.app.service;

import com.kbh.elk.app.entity.Book;
import com.kbh.elk.app.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

	private BookRepository bookRepository;

	public ResponseEntity get(int bookIdx){
		Book book = bookRepository.findById(bookIdx).get();
		return ResponseEntity.ok(book);
	}
	public ResponseEntity post(int name){
		bookRepository.save(null);
		return ResponseEntity.ok().build();
	}
	public ResponseEntity put(int bookIdx){
		return ResponseEntity.ok().build();
	}
	public ResponseEntity delete(int bookIdx){
		return ResponseEntity.ok().build();
	}
}
