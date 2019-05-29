package com.kbh.elk.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BookStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookStoreIdx;
	private String name;
}
