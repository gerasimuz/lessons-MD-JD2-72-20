package by.itacademy.gera.dao;

import java.io.Serializable;

public class DAOException extends Exception implements Serializable{
	private static final long serialVersionUID = -8980027392750017579L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}
}