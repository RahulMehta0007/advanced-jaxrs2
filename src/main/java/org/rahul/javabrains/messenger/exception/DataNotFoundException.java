package org.rahul.javabrains.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(Long id) {
		super("Message id "+id+" not found");
	}

	
	
	
}
