package com.patient.patient.service;

import java.util.List;

import com.patient.patient.model.patientSchema;


public interface patientService {


	patientSchema createPatient(patientSchema user);
	List<patientSchema> getAllPatients();
	patientSchema getById(Integer id);
	void delete(Integer id);
	String updateStatus(patientSchema user,Integer id);
	String updateBill(patientSchema user,Integer id);
	List<patientSchema> getAdmitted();
	List<patientSchema> getDischarged();
	void deleteAll();

}
