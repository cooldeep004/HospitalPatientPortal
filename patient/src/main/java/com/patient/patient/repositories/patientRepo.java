package com.patient.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.patient.model.patientSchema;


public interface patientRepo extends JpaRepository<patientSchema, Integer> {
     patientSchema findByEmail(String email);
     patientSchema findById(int id);
     
     
}
