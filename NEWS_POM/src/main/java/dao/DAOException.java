package dao;

import java.io.Serializable;

public class DAOException extends Exception implements Serializable {

	private static final long serialVersionUID = -2381399938229428618L;

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