package com.kbh.elk.app.service;

import com.kbh.elk.app.entity.Book;
import com.kbh.elk.app.entity.BookStore;
import com.kbh.elk.app.repository.BookRepository;
import com.kbh.elk.app.repository.BookStoreRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

	private BookRepository bookRepository;
	private BookStoreRepository bookStoreRepository;
	private final Logger logger = LoggerFactory.getLogger("ELKProjectPipeLine");
	private final Logger logger2 = LoggerFactory.getLogger(this.getClass());

	public Book select(int bookIdx){
		logger.info("책을 조회했다.");
		logger2.info("책을 조회했다.");
		return bookRepository.getOne(bookIdx);
	}
	public void insert(){
		Book book = new Book();
		bookRepository.save(book);
		logger.info("새 책이 삽입 됬으나, 비어있다." + book.toString());
	}
	public void update(int bookIdx){
		Book book = bookRepository.findById(bookIdx).get();
		book.setAuthor("김보훈");
		book.setName("ELK 완전정복 가이드");
		BookStore bookStore = bookStoreRepository.getOne(1);
		book.setBookStore(bookStore);
		logger.info("책 제목을 수정했다." + book.toString());
	}
	public void delete(int bookIdx){
		bookRepository.deleteById(bookIdx);
		logger.info("책이 지워졌다.");
	}
}
