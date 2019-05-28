package com.kbh.elk.app.repository;

import com.kbh.elk.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
