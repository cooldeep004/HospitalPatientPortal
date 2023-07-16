package com.patient.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.patient.model.staffSchema;


public interface staffRepo extends JpaRepository<staffSchema, Integer>{
	staffSchema findByEmail(String email);
	staffSchema findByUser(String user);
}

