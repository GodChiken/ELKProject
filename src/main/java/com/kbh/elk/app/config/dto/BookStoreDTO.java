package com.kbh.elk.app.config.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookStoreDTO {
	private int bookStoreIdx;
	private String name;
	private List<BookDTO> bookList;
}
