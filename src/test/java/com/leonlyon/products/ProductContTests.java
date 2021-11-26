package com.leonlyon.products;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.leonlyon.products.controllers.ProductCont;
import com.leonlyon.products.models.ProductMod;
import com.leonlyon.products.tools.Page;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductContTests {
	
	@Autowired
	private ProductCont productCont;
	
	@Test
	public void testCreate() {
		ProductMod product = new ProductMod("First product","This is the first product");
		ResponseEntity<ProductMod> resp = productCont.saveSync(product);
		Assertions.assertThat(resp.getBody()).isNotNull();
	}
	
	@Test
	public void testRead() {
		ResponseEntity<Page<ProductMod>> resp = productCont.findProductsByPage(0);
		Assertions.assertThat(resp.getBody()).isNotNull();
	}

}
