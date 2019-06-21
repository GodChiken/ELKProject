package com.kbh.elk.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(exclude = {"bookStore"})
@ToString(exclude = {"bookStore"})
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookIdx;
	private String name;
	private String author;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "book_store_idx")
	private BookStore bookStore;

}
