package com.users.cars.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.users.cars.api.domain.Car;
import com.users.cars.api.domain.User;
import com.users.cars.api.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/api/")
public class UserController {

	@Autowired
	private IUserService iUserService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> fetchAllUsers(@RequestParam(value = "find", required = false) String lookupString,
			@RequestParam(value = "sort", required = false, defaultValue = "id:asc") String sortOrder) {
		log.info("Inside controller fetching all users...");
		return ResponseEntity.ok(iUserService.fetchAllUsers(lookupString, sortOrder));

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> fetchUserById(@PathVariable Long id) {
		log.info("Inside controller fetching user by id...");
		var user = iUserService.fetchUserById(id);
		if (user.isPresent())
			return ResponseEntity.ok().body(user.get());
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/users/{userId}/cars")
	public ResponseEntity<List<Car>> fetchCarsByUserId(@PathVariable Long userId) {
		log.info("Inside controller fetching cars by user id...");
		var cars = iUserService.fetchCarsByUserId(userId);
		if (!cars.isEmpty())
			return ResponseEntity.ok().body(cars);
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cars")
	public ResponseEntity<List<Car>> fetchAllCars(@RequestParam(value = "find", required = false) String lookupString,
			@RequestParam(value = "sort", required = false, defaultValue = "id:asc") String sortOrder) {
		log.info("Inside controller fetching all cars...");
		return ResponseEntity.ok().body(iUserService.fetchAllCars(lookupString, sortOrder));
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> fetchCarById(@PathVariable Long id) {
		log.info("Inside controller fetching cars by id...");
		var car = iUserService.fetchCarById(id);
		if (car.isPresent())
			return ResponseEntity.ok().body(car.get());
		return ResponseEntity.notFound().build();
	}
}
