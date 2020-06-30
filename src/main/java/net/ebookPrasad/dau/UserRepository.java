package net.ebookPrasad.dau;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ebookPrasad.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	Iterable<User> findAllById(Long id);

	
}
