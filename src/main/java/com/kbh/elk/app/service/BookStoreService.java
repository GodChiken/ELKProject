package com.kbh.elk.app.service;

import com.kbh.elk.app.entity.BookStore;
import com.kbh.elk.app.repository.BookStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookStoreService {
	private BookStoreRepository bookStoreRepository;

	@Async("taskExecutor")
	public BookStore getBookStore(int bookStoreIdx){
		return bookStoreRepository.getOne(bookStoreIdx);
	}
}
