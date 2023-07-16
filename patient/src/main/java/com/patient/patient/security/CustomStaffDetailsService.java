package com.patient.patient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.patient.patient.model.staffSchema;
import com.patient.patient.repositories.staffRepo;

@Service
public class CustomStaffDetailsService implements UserDetailsService{
   @Autowired
	private staffRepo sRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		staffSchema user=this.sRepo.findByUser(username);
		return user;
	}
	

}
