package com.kbh.elk.app.config.dto;

import com.kbh.elk.app.entity.BookStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private int bookIdx;
	private String name;
	private String author;
	private BookStore bookStore;
}
