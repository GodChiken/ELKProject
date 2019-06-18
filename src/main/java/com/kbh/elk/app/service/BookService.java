package com.kbh.elk.app.service;

import com.kbh.elk.app.entity.Book;
import com.kbh.elk.app.entity.BookStore;
import com.kbh.elk.app.repository.BookRepository;
import com.kbh.elk.app.repository.BookStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class BookService {

	private BookRepository bookRepository;
	private BookStoreRepository bookStoreRepository;

	@Async("taskExecutor")
	public Book select(int bookIdx) throws ExecutionException, InterruptedException {
		return new AsyncResult<>(bookRepository.getOne(bookIdx)).get();
	}
	public void insert(){
		Book book = new Book();
		bookRepository.save(book);
	}
	public void update(int bookIdx){
		Book book = bookRepository.findById(bookIdx).get();
		book.setAuthor("김보훈");
		book.setName("ELK 완전정복 가이드");
		BookStore bookStore = bookStoreRepository.getOne(1);
		book.setBookStore(bookStore);
	}
	public void delete(int bookIdx){
		bookRepository.deleteById(bookIdx);
	}
}
