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
import com.patient.patient.model.patientSchema;
import com.patient.patient.repositories.patientRepo;
import com.patient.patient.serviceImpl.patientServiceImpl;

@RestController
@RequestMapping("/api/v1/patient")
public class patientController {

	@Autowired
	private patientServiceImpl patientImpl;
	@Autowired
	private patientRepo repo;

	// To create a new patients with unique email
	@PostMapping("/newpatient")
	public ResponseEntity<String> createUser(@Valid @RequestBody patientSchema patient) {
		if (repo.findByEmail(patient.getEmail()) != null ) {
			patientSchema registedPatient = repo.findByEmail(patient.getEmail());
			return ResponseEntity.badRequest()
					.body("patient already exists kindly update patient Id " + registedPatient.getId());
		}

		patientSchema patientCreated = patientImpl.createPatient(patient);
		return new ResponseEntity<>(
				patientCreated.getName() + " Successfully register with id " + patientCreated.getId(),
				HttpStatus.CREATED);
	}

	//get All Registered patients
	@GetMapping("/allpatient")
	public ResponseEntity<List<patientSchema>> getAllPatient() {
		List<patientSchema> allPatients = patientImpl.getAllPatients();
		return new ResponseEntity<>(allPatients, HttpStatus.OK);
	}

	//get patient by id
	@GetMapping("/getpatient/{id}")
	public ResponseEntity<patientSchema> getById(@PathVariable int id) {
		patientSchema patient = patientImpl.getById(id);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	//update status(admitted, discharge) true=admitted , false=Discharged
	@PutMapping("/updatestatus/{id}")
	public ResponseEntity<String> updateStatus(@RequestBody patientSchema patient, @PathVariable int id) {
		String msg = patientImpl.updateStatus(patient, id);

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	//to update bill (patients should be admitted)
	@PutMapping("/updateBill/{id}")
	public ResponseEntity<String> updateBill(@RequestBody patientSchema patient, @PathVariable int id) {

		if (!repo.findById(id).getStatus()) {
			return ResponseEntity.badRequest()
					.body("patient already discharged to generate new bill first admit it :patient Id = "
							+ id);
		}

		String msg=	patientImpl.updateBill(patient, id);
		
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	//to patient delete by id
	@DeleteMapping("/deletepatient/{id}")
	public ResponseEntity<Map<String, String>> deletePatient(@PathVariable int id) {
		patientImpl.delete(id);
		return new ResponseEntity<>(Map.of("message ", "patient deleted"), HttpStatus.OK); 
	}

	//get all admitted patient
	@GetMapping("/getadmitted")
	public ResponseEntity<List<patientSchema>> getAdmitted() {
		List<patientSchema> patient = patientImpl.getAdmitted();
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	//get all discharged patient
	@GetMapping("/getdischarged")
	public ResponseEntity<List<patientSchema>> getDischarged() {
		List<patientSchema> patient = patientImpl.getDischarged();
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	//delete All Registered patients
		@DeleteMapping("/deleteall")
		public ResponseEntity<String> deleteAllPatient() {
			patientImpl.deleteAll();
			return new ResponseEntity<>("all deleted", HttpStatus.OK);
		}
	
}
