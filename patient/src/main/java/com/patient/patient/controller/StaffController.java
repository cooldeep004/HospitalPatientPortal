package com.patient.patient.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.patient.model.staffSchema;
import com.patient.patient.repositories.staffRepo;
import com.patient.patient.serviceImpl.StaffServiceImpl;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
	@Autowired
	private staffRepo repo;

	@Autowired
	private StaffServiceImpl staffImpl;

    //to create staff (unique user and email)
	@PostMapping("/newstaff")
	public ResponseEntity<String> createUser(@Valid @RequestBody staffSchema user) {
		if (repo.findByEmail(user.getEmail()) != null ) {
			staffSchema registedStaff = repo.findByEmail(user.getEmail());
			return ResponseEntity.badRequest().body("staff already exists with same email id staff Id " + registedStaff.getId());
		}
		if (repo.findByUser(user.getUser()) != null ) {
			staffSchema registedStaff = repo.findByUser(user.getUser());
			return ResponseEntity.badRequest().body("staff already exists with same user name staff Id " + registedStaff.getId());
		}

		staffSchema userCreated = staffImpl.createStaff(user);
		return new ResponseEntity<>(userCreated.getName() + " Successfully register with id " + userCreated.getId(),
				HttpStatus.CREATED);
	}

	//to get All staff
	@GetMapping("/allstaff")
	public ResponseEntity<List<staffSchema>> getAllStaff() {
		List<staffSchema> allStaff = staffImpl.getAllStaff();
		return new ResponseEntity<>(allStaff, HttpStatus.OK);
	}

	//to get staff by id
	@GetMapping("/getstaff/{id}")
	public ResponseEntity<staffSchema> getById(@PathVariable int id) {
		staffSchema staff = staffImpl.getById(id);
		return new ResponseEntity<>(staff, HttpStatus.OK);
	}

	//update staff details
	@PutMapping("/updatestaff/{id}")
	public ResponseEntity<String> updateStatus(@RequestBody staffSchema staff, @PathVariable int id) {
		staffImpl.updateDetails(staff, id);
		return new ResponseEntity<>("details updated", HttpStatus.OK);

	}

	//staff delete by id
	@DeleteMapping("/deletestaff/{id}")
	public ResponseEntity<Map<String, String>> deletePatient(@PathVariable int id) {
		staffImpl.delete(id);
		return new ResponseEntity<>(Map.of("message", "staff deleted"), HttpStatus.OK); 
	}
	//delete All Registered staff
			@DeleteMapping("/deleteall")
			public ResponseEntity<String> deleteAllPatient() {
				staffImpl.deleteAll();
				return new ResponseEntity<>("all deleted", HttpStatus.OK);
			}
		

}