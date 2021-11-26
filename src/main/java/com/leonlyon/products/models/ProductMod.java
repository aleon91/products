package com.leonlyon.products.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductMod {
	
	private String productId;
	private String name;
	private String description;
	
	public ProductMod(String name,String description) {
		this.name = name;
		this.description = description;
	}
	
	public boolean findByField(String field) {
		return productId.contains(field) || name.contains(field) || description.contains(field);
	}

}
