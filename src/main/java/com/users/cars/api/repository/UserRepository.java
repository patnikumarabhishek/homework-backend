package com.users.cars.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.users.cars.api.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "select * from user u where u.id like %?1% or u.name like %?1%", nativeQuery = true)
	List<User> findByLookupString(String lookupString);

}
