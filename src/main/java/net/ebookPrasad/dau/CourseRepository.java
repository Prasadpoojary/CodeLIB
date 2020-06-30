package net.ebookPrasad.dau;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ebookPrasad.model.Course;
import java.lang.String;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Iterable<Course> findAllByUid(Long uid);
	
	Iterable<Course> findByCategory(String category);

	@Query(value = "SELECT CATEGORY FROM COURSES", nativeQuery = true)
	Iterable<String> getAllCategory();

	Iterable<Course> findAllById(Long id);
	
}
