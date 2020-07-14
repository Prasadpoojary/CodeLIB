package net.ebookPrasad.dau;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ebookPrasad.model.Book;
import java.lang.String;


public interface BookRepository extends JpaRepository<Book, Long> {
	Iterable<Book> findAllByUid(Long uid);
	
	Iterable<Book> findByCategory(String category);

	Iterable<Book> findAllById(Long id);
	
	
}
