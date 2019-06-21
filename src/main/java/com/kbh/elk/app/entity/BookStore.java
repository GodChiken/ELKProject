package com.kbh.elk.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"bookList"})
@ToString(exclude = {"bookList"})
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BookStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookStoreIdx;
	private String name;

	@OneToMany(mappedBy = "bookStore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Book> bookList;
}
