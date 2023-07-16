package com.patient.patient.exception;

public class patientNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public patientNotFoundException(String msg)
	{
		super(msg);
	}

}
