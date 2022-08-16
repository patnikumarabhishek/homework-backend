package com.users.cars.api.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.users.cars.api.domain.Car;
import com.users.cars.api.domain.User;
import com.users.cars.api.repository.CarsRepository;
import com.users.cars.api.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements IUserService {

	private static final String NAME = "name";

	private static final String DESC = "DESC";

	private static final String ASC = "ASC";

	private static final String MAKE = "make";

	private static final String MODEL = "model";

	private static final String NUMBERPLATE = "numberplate";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarsRepository carsRepository;

	@Override
	public List<User> fetchAllUsers(String lookupString, String sortOrder) {
		log.info("Inside service fetch all users....");
		String order = !ObjectUtils.isEmpty(sortOrder) ? sortOrder.split(":")[1] : "";
		String sortByField = !ObjectUtils.isEmpty(sortOrder) ? sortOrder.split(":")[0] : "";
		return fetchFinalUsersData(lookupString, order, sortByField);
	}

	private List<User> fetchFinalUsersData(String lookupString, String order, String sortByField) {
		if (!ObjectUtils.isEmpty(lookupString) && ASC.equalsIgnoreCase(order)) {
			return fetchUserSearchAndSortDataAsc(lookupString, sortByField);
		} else if (!ObjectUtils.isEmpty(lookupString) && DESC.equalsIgnoreCase(order)) {
			return fetchUserSearchAndSortDataDesc(lookupString, sortByField);
		} else if (ObjectUtils.isEmpty(lookupString) && DESC.equalsIgnoreCase(order)) {
			return sortUsersByField(sortByField);
		}
		return userRepository.findAll();
	}

	private List<User> sortUsersByField(String sortByField) {
		if (NAME.equalsIgnoreCase(sortByField)) {
			return userRepository.findAll().stream().sorted(Comparator.comparing(User::getName).reversed())
					.collect(Collectors.toList());
		}
		return userRepository.findAll().stream().sorted(Comparator.comparing(User::getId).reversed())
				.collect(Collectors.toList());
	}

	private List<User> fetchUserSearchAndSortDataDesc(String lookupString, String sortByField) {
		if (NAME.equalsIgnoreCase(sortByField)) {
			return userRepository.findByLookupString(lookupString).stream()
					.sorted(Comparator.comparing(User::getName).reversed()).collect(Collectors.toList());
		}
		return userRepository.findByLookupString(lookupString).stream()
				.sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
	}

	private List<User> fetchUserSearchAndSortDataAsc(String lookupString, String sortByField) {
		if (NAME.equalsIgnoreCase(sortByField)) {
			return userRepository.findByLookupString(lookupString).stream().sorted(Comparator.comparing(User::getName))
					.collect(Collectors.toList());
		}
		return userRepository.findByLookupString(lookupString).stream().sorted(Comparator.comparing(User::getId))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<User> fetchUserById(Long id) {
		log.info("Inside service fetch user by id....");
		return userRepository.findById(id);
	}

	@Override
	public List<Car> fetchCarsByUserId(Long id) {
		log.info("Inside service fetch cars by user id....");
		return carsRepository.findByUser(new User(id));
	}

	@Override
	public List<Car> fetchAllCars(String lookupString, String sortOrder) {
		log.info("Inside service fetch all cars....");
		String order = !ObjectUtils.isEmpty(sortOrder) ? sortOrder.split(":")[1] : "";
		String sortByField = !ObjectUtils.isEmpty(sortOrder) ? sortOrder.split(":")[0] : "";
		return fetchFinalCarsData(lookupString, order, sortByField);
	}

	private List<Car> fetchFinalCarsData(String lookupString, String order, String sortByField) {
		if (!ObjectUtils.isEmpty(lookupString) && ASC.equalsIgnoreCase(order)) {
			return fetchCarsSearchAndSortDataAsc(lookupString, sortByField);
		} else if (!ObjectUtils.isEmpty(lookupString) && DESC.equalsIgnoreCase(order)) {
			return fetchCarsSearchAndSortDataDesc(lookupString, sortByField);
		} else if (ObjectUtils.isEmpty(lookupString) && DESC.equalsIgnoreCase(order)) {
			return sortCarsByField(sortByField);
		}
		return carsRepository.findAll();
	}

	private List<Car> sortCarsByField(String sortByField) {
		if (MAKE.equalsIgnoreCase(sortByField)) {
			return carsRepository.findAll().stream().sorted(Comparator.comparing(Car::getMake).reversed())
					.collect(Collectors.toList());
		} else if (NUMBERPLATE.equalsIgnoreCase(sortByField)) {
			return carsRepository.findAll().stream().sorted(Comparator.comparing(Car::getNumberplate).reversed())
					.collect(Collectors.toList());
		} else if (MODEL.equalsIgnoreCase(sortByField)) {
			return carsRepository.findAll().stream().sorted(Comparator.comparing(Car::getModel).reversed())
					.collect(Collectors.toList());
		}
		return carsRepository.findAll().stream().sorted(Comparator.comparing(Car::getId).reversed())
				.collect(Collectors.toList());
	}

	private List<Car> fetchCarsSearchAndSortDataDesc(String lookupString, String sortByField) {
		if (MAKE.equalsIgnoreCase(sortByField)) {
			return carsRepository.findByLookupString(lookupString).stream()
					.sorted(Comparator.comparing(Car::getMake).reversed()).collect(Collectors.toList());
		} else if (NUMBERPLATE.equalsIgnoreCase(sortByField)) {
			return carsRepository.findByLookupString(lookupString).stream()
					.sorted(Comparator.comparing(Car::getNumberplate).reversed()).collect(Collectors.toList());
		} else if (MODEL.equalsIgnoreCase(sortByField)) {
			return carsRepository.findByLookupString(lookupString).stream()
					.sorted(Comparator.comparing(Car::getModel).reversed()).collect(Collectors.toList());
		}
		return carsRepository.findByLookupString(lookupString).stream()
				.sorted(Comparator.comparing(Car::getId).reversed()).collect(Collectors.toList());
	}

	private List<Car> fetchCarsSearchAndSortDataAsc(String lookupString, String sortByField) {
		if (MAKE.equalsIgnoreCase(sortByField)) {
			return carsRepository.findByLookupString(lookupString).stream().sorted(Comparator.comparing(Car::getMake))
					.collect(Collectors.toList());
		} else if (NUMBERPLATE.equalsIgnoreCase(sortByField)) {
			return carsRepository.findByLookupString(lookupString).stream()
					.sorted(Comparator.comparing(Car::getNumberplate)).collect(Collectors.toList());
		} else if (MODEL.equalsIgnoreCase(sortByField)) {
			return carsRepository.findByLookupString(lookupString).stream().sorted(Comparator.comparing(Car::getModel))
					.collect(Collectors.toList());
		}
		return carsRepository.findByLookupString(lookupString).stream().sorted(Comparator.comparing(Car::getId))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Car> fetchCarById(Long id) {
		log.info("Inside service fetch car by id....");
		return carsRepository.findById(id);
	}

}
