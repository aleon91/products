package com.leonlyon.products.repositories;

import com.leonlyon.products.models.ProductMod;
import com.leonlyon.products.tools.Page;

public interface ProductRep {
	
	public boolean existsByProductId(String productId);
	
	public Page<ProductMod> findProductsByPage(Integer page);
	
	public Page<ProductMod> findProductsByPageAndField(Integer page,String field);
	
	public ProductMod findProduct(String productId);
	
	public ProductMod save(ProductMod product);
	
	public void delete(String productId);

}
