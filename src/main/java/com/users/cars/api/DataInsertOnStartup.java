package com.users.cars.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.users.cars.api.domain.Car;
import com.users.cars.api.domain.User;
import com.users.cars.api.repository.CarsRepository;
import com.users.cars.api.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataInsertOnStartup {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarsRepository carsRepository;

	@EventListener
	public void inserDataIntoDB(ApplicationReadyEvent event) throws IOException {
		List<User> users = userRepository.findAll();
		List<Car> cars = carsRepository.findAll();
		if (users.isEmpty() && cars.isEmpty()) {
			log.info("sample data insertion start....");
			users.add(new User(1L, "Teet Järveküla"));
			users.add(new User(2L, "Pille Purk"));
			users.add(new User(3L, "Mati Kaal"));
			users.add(new User(4L, "Külli Kukk"));
			users.add(new User(5L, "Teet Kruus"));
			cars.add(new Car(1L, "Lada", "2101", "123ASD", new User(1L)));
			cars.add(new Car(2L, "Kia", "Sorento", "534TTT", new User(1L)));
			cars.add(new Car(3L, "Skoda", "Octavia", "999GLF", new User(2L)));
			cars.add(new Car(4L, "Kia", "Sorento", "555TFF", new User(2L)));
			cars.add(new Car(5L, "Lada", "2101", "445KKK", new User(3L)));
			cars.add(new Car(6L, "Audi", "A6", "997HHH", new User(3L)));
			cars.add(new Car(7L, "BMW", "760", "444RRR", new User(4L)));
			cars.add(new Car(8L, "Audi", "A6", "876OUI", new User(4L)));
			cars.add(new Car(9L, "BMW", "740", "112YUI", new User(5L)));
			userRepository.saveAll(users);
			carsRepository.saveAll(cars);
			log.info("sample data insertion end....");
		}
	}

}
