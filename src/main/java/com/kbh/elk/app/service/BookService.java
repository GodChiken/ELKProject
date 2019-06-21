package com.kbh.elk.app.service;

import com.kbh.elk.app.config.dto.BookDTO;
import com.kbh.elk.app.config.exception.common.BusinessException;
import com.kbh.elk.app.entity.Book;
import com.kbh.elk.app.entity.BookStore;
import com.kbh.elk.app.repository.BookRepository;
import com.kbh.elk.app.repository.BookStoreRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@AllArgsConstructor
public class BookService {

	private ModelMapper modelMapper;

	private BookRepository bookRepository;
	private BookStoreRepository bookStoreRepository;

	@Async("taskExecutor")
	public Future<BookDTO> select(int bookIdx) {
		Book book = bookRepository.findById(bookIdx).orElseThrow(() -> new BusinessException(10000));
		BookDTO bookDTO = convertDTO(book);
		return new AsyncResult<>(bookDTO);
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

	public BookDTO convertDTO(Book book){
		return modelMapper.map(book, BookDTO.class);
	}
}
