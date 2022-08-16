package com.users.cars.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.users.cars.api.domain.Car;
import com.users.cars.api.domain.User;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

	List<Car> findByUser(User user);

	@Query(value = "select * from car c where c.id like %?1% or c.make like %?1% or c.model like %?1% or c.numberplate like %?1%", nativeQuery = true)
	List<Car> findByLookupString(String lookupString);

}
