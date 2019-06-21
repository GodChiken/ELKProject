package com.kbh.elk.app.presentation;

import com.kbh.elk.app.service.BookService;
import com.kbh.elk.app.service.BookStoreService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Controller
@AllArgsConstructor
public class ELKController {

	private BookService bookService;
	private BookStoreService bookStoreService;

	@GetMapping(value = "/bookStore/{bookStoreIdx}")
	public ResponseEntity bookStoreNameByIdx(@PathVariable int bookStoreIdx){
		return ResponseEntity.ok(bookStoreService.getBookStore(bookStoreIdx));
	}

	@GetMapping(value = "/book/{bookIdx}")
	public ResponseEntity book(@PathVariable int bookIdx) throws ExecutionException, InterruptedException {
		return ResponseEntity.ok(bookService.select(bookIdx).get());
	}

	@PostMapping(value = "/book")
	public ResponseEntity bookForBookStoreIdx(){
		bookService.insert();
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/book/{bookIdx}")
	public ResponseEntity putBookForBookStoreIdx(@PathVariable int bookIdx){
		bookService.update(bookIdx);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/book/{bookIdx}")
	public ResponseEntity deleteBookForBookStoreIdx(@PathVariable int bookIdx){
		return ResponseEntity.ok().build();
	}
}
