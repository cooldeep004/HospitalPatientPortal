package com.patient.patient.service;

import java.util.List;

import com.patient.patient.model.staffSchema;


public interface staffService {
	
	staffSchema createStaff(staffSchema user);
	List<staffSchema> getAllStaff();
	staffSchema getById(Integer id);
	void delete(Integer id);
	String updateDetails(staffSchema user,Integer id);
	void deleteAll();
}
