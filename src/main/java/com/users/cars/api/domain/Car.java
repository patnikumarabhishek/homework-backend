package com.users.cars.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	public Car() {
	}

	public Car(Long id, String make, String model, String numberplate, User user) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.numberplate = numberplate;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String make;
	private String model;
	private String numberplate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private User user;

}
