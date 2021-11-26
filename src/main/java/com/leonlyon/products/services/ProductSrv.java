package com.leonlyon.products.services;

import java.util.concurrent.Future;

import com.leonlyon.products.models.ProductMod;
import com.leonlyon.products.tools.Page;

public interface ProductSrv {
	
	public Page<ProductMod> findProductsByPage(Integer page);
	
	public Page<ProductMod> findProductsByPageAndField(Integer page, String field);
	
	public ProductMod findProductByProductId(String productId);
	
	public ProductMod saveProductSync(ProductMod product);
	
	public Future<ProductMod> saveProductAsync(ProductMod product);
	
	public ProductMod updateProduct(String productId,ProductMod product);
	
	public void deleteProduct(String productId);

}
