package com.kbh.elk.app.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookStoreIdx;
	private String name;
}
