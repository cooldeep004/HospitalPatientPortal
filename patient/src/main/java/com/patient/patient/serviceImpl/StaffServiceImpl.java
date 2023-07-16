package com.patient.patient.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patient.patient.exception.StaffNotFoundException;
import com.patient.patient.model.staffSchema;
import com.patient.patient.repositories.staffRepo;
import com.patient.patient.service.staffService;


@Service
public class StaffServiceImpl implements staffService{

	@Autowired
	private staffRepo repo;
	@Override
	
	public staffSchema createStaff(staffSchema user) {
		String encodedPassword = passwordEncoder1().encode(user.getPassword());
        user.setPassword(encodedPassword);
		repo.save(user);
		return user;
	}

	@Override
	public List<staffSchema> getAllStaff() {
		return repo.findAll();
	}

	@Override
	public staffSchema getById(Integer id) {
		staffSchema staff=repo.findById(id).orElseThrow(() -> new StaffNotFoundException("staff not found with entered Id of deleted , registered again"));
		return staff;
	}
	
	@Override
	public String updateDetails(staffSchema user, Integer id) {
		staffSchema update=repo.findById(id).orElseThrow(() -> new StaffNotFoundException("staff not found with entered Id of deleted , registered again"));
		update.setStaffType(user.getStaffType());
		repo.save(update);
		return "staff type updated";
	}
	

	@Override
	public void delete(Integer id) {
		staffSchema staff=repo.findById(id).orElseThrow(() -> new StaffNotFoundException("staff not found with entered Id of deleted , registered again"));
		repo.delete(staff);
		return ;
	}

	@Bean
    public PasswordEncoder passwordEncoder1() {
        return new BCryptPasswordEncoder();
    }

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

}