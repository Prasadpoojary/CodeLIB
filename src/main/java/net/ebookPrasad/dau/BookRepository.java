package net.ebookPrasad.dau;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ebookPrasad.model.Book;
import java.lang.String;


public interface BookRepository extends JpaRepository<Book, Long> {
	Iterable<Book> findAllByUid(Long uid);
	
	Iterable<Book> findByCategory(String category);
	
	@Query(value = "SELECT CATEGORY FROM BOOKS",nativeQuery = true)
	Iterable<String> getAllCategory();

	Iterable<Book> findAllById(Long id);
	
	
}
