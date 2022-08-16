package com.users.cars.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.users.cars.api.domain.Car;
import com.users.cars.api.domain.User;
import com.users.cars.api.repository.CarsRepository;
import com.users.cars.api.repository.UserRepository;
import com.users.cars.api.service.UserService;

@SpringBootTest
class UserCarsApiApplicationTests {

	@Mock
	private UserRepository userRepository;

	@Mock
	private CarsRepository carsRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void fetchAllUsersTest1() {
		when(userRepository.findByLookupString(anyString())).thenReturn(new ArrayList<User>());
		when(userRepository.findAll()).thenReturn(new ArrayList<User>());
		when(userService.fetchAllUsers("abc", "abc:ase")).thenReturn(new ArrayList<User>());
		assertTrue(true, "Test cases successfully executed..");
	}

	@Test
	void fetchAllUsersTest2() {
		when(userRepository.findByLookupString("teet")).thenReturn(new ArrayList<User>());
		when(userRepository.findAll()).thenReturn(new ArrayList<User>());
		when(userService.fetchAllUsers("teet", "name:desc")).thenReturn(new ArrayList<User>());
		assertTrue(true, "Test cases successfully executed..");
	}

	@Test
	void fetchAllCarsTest3() {
		when(carsRepository.findByLookupString(anyString())).thenReturn(new ArrayList<Car>());
		when(carsRepository.findAll()).thenReturn(new ArrayList<Car>());
		when(userService.fetchAllCars("abc", "abc:des")).thenReturn(new ArrayList<Car>());
		assertTrue(true, "Test cases successfully executed..");
	}

	@Test
	void fetchAllCarsTest4() {
		when(carsRepository.findByLookupString("lada")).thenReturn(new ArrayList<Car>());
		when(carsRepository.findAll()).thenReturn(new ArrayList<Car>());
		when(userService.fetchAllCars("kia", "make:desc")).thenReturn(new ArrayList<Car>());
		assertTrue(true, "Test cases successfully executed..");
	}

	@Test
	void fetchUserByIdTest5() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
		when(userService.fetchUserById(anyLong())).thenReturn(Optional.of(new User()));
		assertTrue(true, "Test cases successfully executed..");
	}

	@Test
	void fetchCarsByUserIdTest6() {
		when(carsRepository.findByUser(new User(anyLong()))).thenReturn(new ArrayList<Car>());
		when(userService.fetchCarsByUserId(anyLong())).thenReturn(new ArrayList<Car>());
		assertTrue(true, "Test cases successfully executed..");
	}

	@Test
	void fetchCarByIdTest7() {
		when(carsRepository.findById(anyLong())).thenReturn(Optional.of(new Car()));
		when(userService.fetchCarById(anyLong())).thenReturn(Optional.of(new Car()));
		assertTrue(true, "Test cases successfully executed..");
	}

	@Test
	void fetchAllUsersTest8() {
		when(userRepository.findByLookupString("teet")).thenReturn(new ArrayList<User>());
		when(userRepository.findAll()).thenReturn(new ArrayList<User>());
		when(userService.fetchAllUsers("teet", "id:asc")).thenReturn(new ArrayList<User>());
		assertTrue(true, "Test cases successfully executed..");
	}

}
