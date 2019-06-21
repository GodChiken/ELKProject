package com.kbh.elk.app.service;

import com.kbh.elk.app.config.dto.BookStoreDTO;
import com.kbh.elk.app.config.exception.common.BusinessException;
import com.kbh.elk.app.entity.BookStore;
import com.kbh.elk.app.repository.BookStoreRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookStoreService {

	private ModelMapper modelMapper;

	private BookStoreRepository bookStoreRepository;

	public BookStoreDTO getBookStore(int bookStoreIdx){
		BookStore bookStore = bookStoreRepository.findById(bookStoreIdx).orElseThrow(() -> new BusinessException(10000));
		BookStoreDTO bookStoreDTO = convertDTO(bookStore);
		return bookStoreDTO;
	}
	public BookStoreDTO convertDTO(BookStore bookStore){
		return modelMapper.map(bookStore, BookStoreDTO.class);
	}
}
