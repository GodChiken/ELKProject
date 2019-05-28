package com.kbh.elk.app.presentation;

import com.kbh.elk.app.service.BookService;
import com.kbh.elk.app.service.BookStoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ELKController {

	private BookService bookService;
	private BookStoreService bookStoreService;

	@GetMapping(value = "/bookStore/{bookStoreIdx}")
	public ResponseEntity getBookStoreNameByIdx(@PathVariable int bookStoreIdx){
		return ResponseEntity.ok("asdf");
	}
	@GetMapping(value = "/book/{bookIdx}")
	public ResponseEntity getBook(@PathVariable int bookIdx){
		return ResponseEntity.ok().build();
	}
	@PostMapping(value = "/bookStore/{bookStoreIdx}/{bookIdx}")
	public ResponseEntity postBookForBookStoreIdx(@PathVariable int bookStoreIdx,int bookIdx){
		return ResponseEntity.ok().build();
	}
	@PutMapping(value = "/bookStore/{bookStoreIdx}/{bookIdx}")
	public ResponseEntity putBookForBookStoreIdx(@PathVariable int bookStoreIdx,int bookIdx){
		return ResponseEntity.ok().build();
	}
	@DeleteMapping(value = "/bookStore/{bookStoreIdx}/{bookIdx}")
	public ResponseEntity deleteBookForBookStoreIdx(@PathVariable int bookStoreIdx,int bookIdx){
		return ResponseEntity.ok().build();
	}
}
