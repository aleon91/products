package com.leonlyon.products.tools;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page<T> {
	
	private List<T> items;
	private Integer currentPage;
	private Integer itemsPage;
	private Integer totalItems;

}
