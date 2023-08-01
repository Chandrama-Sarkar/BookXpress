package com.microservice.bookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.bookstore.Entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books,Integer> {

	@Query("select book.price from Books book where book.bookId =:n")
	public int getPriceOfBook(@Param("n") Integer bookId);

	public boolean existsByBookName(String bookName);
}
