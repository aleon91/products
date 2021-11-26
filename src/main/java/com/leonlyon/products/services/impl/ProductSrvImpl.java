package com.leonlyon.products.services.impl; 

import java.util.Optional;
import java.util.concurrent.Future;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.leonlyon.products.exceptions.ProductNoContentException;
import com.leonlyon.products.exceptions.ProductNotFoundException;
import com.leonlyon.products.models.ProductMod;
import com.leonlyon.products.repositories.ProductRep;
import com.leonlyon.products.services.ProductSrv;
import com.leonlyon.products.tools.Constants;
import com.leonlyon.products.tools.Page;

@Service
public class ProductSrvImpl implements ProductSrv {
	
	@Autowired
	private ProductRep productRep;
	
	@Override
	public Page<ProductMod> findProductsByPage(Integer page){
		Page<ProductMod> products = productRep.findProductsByPage(page);
		if(products.getTotalItems()>0)
			return products;
		else
			throw new ProductNoContentException("There are no products");
	}
	
	@Override
	public Page<ProductMod> findProductsByPageAndField(Integer page, String field){
		Page<ProductMod> products = productRep.findProductsByPageAndField(page, field);
		if(products.getTotalItems()>0)
			return products;
		else
			throw new ProductNoContentException("No products found with the specified parameters");
	}
	
	@Override
	public ProductMod findProductByProductId(String productId){
		return Optional.ofNullable(productRep.findProduct(productId)).orElseThrow(() -> new ProductNotFoundException(Constants.NOT_FOUND));
	}

	@Override
	public ProductMod saveProductSync(ProductMod product) {
		product.setProductId(generateProductId());
		return productRep.save(product);
	}
	
	@Override
	@Async("threadPoolTaskExecutor")
	public Future<ProductMod> saveProductAsync(ProductMod product){
		product.setProductId(generateProductId());
		return new AsyncResult<>(productRep.save(product));
	}

	@Override
	public ProductMod updateProduct(String productId, ProductMod product) {
		Optional<ProductMod> opt = Optional.ofNullable(Optional.of(productRep.findProduct(productId)).orElseThrow(() -> new ProductNotFoundException(Constants.NOT_FOUND)));
		if(opt.isPresent())
			return productRep.save(product);
		else
			throw new ProductNotFoundException(Constants.NOT_FOUND);
	}

	@Override
	public void deleteProduct(String productId) {
		Optional<ProductMod> opt = Optional.ofNullable(Optional.of(productRep.findProduct(productId)).orElseThrow(() -> new ProductNotFoundException(Constants.NOT_FOUND)));
		if(opt.isPresent()) {
			productRep.delete(productId);
		}else
			throw new ProductNotFoundException(Constants.NOT_FOUND);
	}
	
	private String generateProductId() {
		var productId = RandomStringUtils.randomAlphabetic(10);
		if(productRep.existsByProductId(productId))
			productId = generateProductId();
		return productId;
	}

}
