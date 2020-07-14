package net.ebookPrasad.dau;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ebookPrasad.model.Course;
import java.lang.String;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Iterable<Course> findAllByUid(Long uid);
	
	Iterable<Course> findByCategory(String category);

	

	Iterable<Course> findAllById(Long id);
	
}
