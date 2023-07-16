package com.patient.patient.serviceImpl;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.patient.patient.exception.patientNotFoundException;
import com.patient.patient.model.patientSchema;
import com.patient.patient.model.staffSchema;
import com.patient.patient.repositories.patientRepo;
import com.patient.patient.service.patientService;

@Service
public class patientServiceImpl implements patientService{

	
	@Autowired
	private patientRepo repo;
	
	@Override
	public patientSchema createPatient(patientSchema patient) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        staffSchema username = (staffSchema)authentication.getPrincipal();
        String staffName=username.getName();
		patient.setCreatedBy(staffName);
		patient.setUpdatedBy(staffName);
		patient.setCreatedDate(new Date());
		patient.setLastUpdated(new Date());
		if(patient.getStatus()==null)
     	patient.setStatus(false);
		if(patient.getRoom()==null)
		patient.setRoom("NA");
		repo.save(patient);
		return patient;
	}

@Override
public List<patientSchema> getAllPatients() {
	return repo.findAll();
}


@Override
public void delete(Integer id) {
	patientSchema patient=repo.findById(id).orElseThrow(() -> new patientNotFoundException("patient not found with your details or already deleted"));
	repo.delete(patient);
	return ;
}



@Override
public patientSchema getById(Integer id) {
	patientSchema patient=repo.findById(id).orElseThrow(() -> new patientNotFoundException("patient not found with your details kindly check your detail"));
	return patient;
}

@Override
public String updateStatus(patientSchema patient , Integer id) {
	patientSchema updateStatus=repo.findById(id).orElseThrow(() -> new patientNotFoundException("patient not found with your details kindly check your detail"));
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    staffSchema username = (staffSchema)authentication.getPrincipal();
    String staffName=username.getName();
    
    Boolean status=patient.getStatus();
    String msg="";
    if(status) {
    	 msg= "patient admitted in room : "+ patient.getRoom() ;
    		updateStatus.setRoom(patient.getRoom());
    		updateStatus.setAdmitDate(new Date());
         }
    else
	{
		 msg= "patient discharged total Bill : "+ updateStatus.getExpanse() ;	
			updateStatus.setRoom("NA");
			updateStatus.setExpanse(0);
			updateStatus.setAdmitDate(null);
	}
    updateStatus.setStatus(patient.getStatus());
	updateStatus.setUpdatedBy(staffName);
	updateStatus.setLastUpdated(new Date());
	repo.save(updateStatus);
	return msg;
}

@Override
public String updateBill(patientSchema patient, Integer id) {
	patientSchema updateBill=repo.findById(id).orElseThrow(() -> new patientNotFoundException("patient not found with your details kindly check your detail"));
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    staffSchema username = (staffSchema)authentication.getPrincipal();
    
    String staffName=username.getName();
    updateBill.setExpanse(patient.getExpanse());
    updateBill.setUpdatedBy(staffName);
    updateBill.setLastUpdated(new Date());
	repo.save(updateBill);
	String msg="bill updated current bill : "+patient.getExpanse();
	return msg;
}

@Override
public List<patientSchema> getAdmitted() {
	List<patientSchema> patients=repo.findAll().stream().filter(p->p.getStatus()).collect(Collectors.toList());
	return patients;
}

@Override
public List<patientSchema> getDischarged() {
	List<patientSchema> patients=repo.findAll().stream().filter(p-> !(p.getStatus())).collect(Collectors.toList());
	return patients;
}

@Override
public void deleteAll() {
	// TODO Auto-generated method stub
	repo.deleteAll();
	
}





	
}
