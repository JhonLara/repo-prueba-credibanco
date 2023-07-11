package com.prueba.credibanco.entity;

/**
 * Exception creada para manejar los errores en la app
 * 
 * @author Jhon Lara
 *
 */
public class BankException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BankException(String message) {
		super(message);
	}
}
