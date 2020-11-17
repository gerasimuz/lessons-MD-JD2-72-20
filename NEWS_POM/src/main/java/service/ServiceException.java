package service;

import java.io.Serializable;

public class ServiceException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
		
	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}	
	
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
	
}