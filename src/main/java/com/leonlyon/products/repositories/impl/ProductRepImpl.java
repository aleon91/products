package com.leonlyon.products.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.leonlyon.products.models.ProductMod;
import com.leonlyon.products.repositories.ProductRep;
import com.leonlyon.products.tools.Constants;
import com.leonlyon.products.tools.Page;

@Service
public class ProductRepImpl implements ProductRep {
	
	private Map<String,ProductMod> products = new HashMap<>();
	
	@Override
	public boolean existsByProductId(String productId) {
		return products.containsKey(productId);
	}

	@Override
	public Page<ProductMod> findProductsByPage(Integer page) {
		List<ProductMod> items = products.values().stream().skip(Long.valueOf(page)*Constants.ITEMS_PAGE).limit(Constants.ITEMS_PAGE).toList();
		return new Page<>(items, page, Constants.ITEMS_PAGE, products.size());
	}

	@Override
	public Page<ProductMod> findProductsByPageAndField(Integer page, String field) {
		Long numItems = products.values().stream().filter(p -> p.findByField(field)).count();
		List<ProductMod> items = products.values().stream().filter(p -> p.findByField(field)).skip(Long.valueOf(page)*Constants.ITEMS_PAGE).limit(Constants.ITEMS_PAGE).toList();
		return new Page<>(items, page, Constants.ITEMS_PAGE, numItems.intValue());
	}

	@Override
	public ProductMod findProduct(String productId) {
		return products.get(productId);
	}

	@Override
	public ProductMod save(ProductMod product) {
		products.put(product.getProductId(), product);
		return product;
	}

	@Override
	public void delete(String productId) {
		products.remove(productId);
	}

}
