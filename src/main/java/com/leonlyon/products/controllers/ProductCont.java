package com.leonlyon.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonlyon.products.models.ProductMod;
import com.leonlyon.products.services.ProductSrv;
import com.leonlyon.products.tools.Page;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/products")
public class ProductCont {
	
	@Autowired
	private ProductSrv productSrv;

	@GetMapping("/{page}")
	public ResponseEntity<Page<ProductMod>> findProductsByPage(@PathVariable Integer page){
		Page<ProductMod> rep = productSrv.findProductsByPage(page);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	@GetMapping("/{page}/{field}")
	public ResponseEntity<Page<ProductMod>> findProductsByPageAndField(@PathVariable Integer page,@PathVariable String field){
		Page<ProductMod> rep = productSrv.findProductsByPageAndField(page, field);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductMod> findProductByProductId(@PathVariable String productId){
		ProductMod rep = productSrv.findProductByProductId(productId);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	@PostMapping("/sync")
	public ResponseEntity<ProductMod> saveSync(@RequestBody ProductMod product){
		ProductMod rep = productSrv.saveProductSync(product);
		return new ResponseEntity<>(rep,HttpStatus.CREATED);
	}

	@PostMapping("/async")
	public ResponseEntity<String> saveAsync(@RequestBody ProductMod product){
		productSrv.saveProductAsync(product);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ProductMod> update(@PathVariable String productId,@RequestBody ProductMod product){
		ProductMod rep = productSrv.updateProduct(productId, product);
		return new ResponseEntity<>(rep,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> delete(@PathVariable String productId){
		productSrv.deleteProduct(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
