package com.leonlyon.products.exceptions;

public class ProductNoContentException extends RuntimeException {
	
	private static final long serialVersionUID = 1048184195188759948L;

	public ProductNoContentException(String message) {
		super(message);
	}

}
