package com.users.cars.api.service;

import java.util.List;
import java.util.Optional;

import com.users.cars.api.domain.Car;
import com.users.cars.api.domain.User;

public interface IUserService {

	List<User> fetchAllUsers(String lookupString, String sortOrder);

	Optional<User> fetchUserById(Long id);

	List<Car> fetchCarsByUserId(Long id);

	List<Car> fetchAllCars(String lookupString, String sortOrder);

	Optional<Car> fetchCarById(Long id);

}
