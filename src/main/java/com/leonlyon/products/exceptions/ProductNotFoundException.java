package com.leonlyon.products.exceptions;

public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 7852179652245699790L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
