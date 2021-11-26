package com.leonlyon.products;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.leonlyon.products.controllers.ProductCont;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductsApplicationTests {
	
	@Autowired
	private ProductCont productCont;

	@Test
	void contextLoads() {
		Assertions.assertThat(productCont).isNot(null);
	}

}
