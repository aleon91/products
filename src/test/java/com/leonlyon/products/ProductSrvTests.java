package com.leonlyon.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leonlyon.products.models.ProductMod;
import com.leonlyon.products.repositories.ProductRep;
import com.leonlyon.products.services.ProductSrv;
import com.leonlyon.products.tools.Constants;
import com.leonlyon.products.tools.Page;

@ExtendWith(MockitoExtension.class)
public class ProductSrvTests {

	@InjectMocks
	private ProductSrv productSrv;
	@Mock
	private ProductRep productRep;
	
	@Test
	public void testFindProducts() {
		List<ProductMod> list = new ArrayList<>();
		ProductMod productOne = new ProductMod("Product one","This is the first product");
		ProductMod productTwo = new ProductMod("Product two","This is the second product");
		ProductMod productThree = new ProductMod("Product three","This is the third product");
		
		list.add(productOne);
		list.add(productTwo);
		list.add(productThree);
		
		when(productRep.findProductsByPage(0)).thenReturn(new Page<>(list,0,Constants.ITEMS_PAGE,list.size()));
		
		//Test
		Page<ProductMod> prodPage = productSrv.findProductsByPage(0);
		
		assertEquals(3,prodPage.getItems().size());
		verify(productRep,times(1)).findProductsByPage(0);
	}
	
	@Test
	public void saveProduct() {
		ProductMod productOne = new ProductMod("Product one","This is the first product");
		productSrv.saveProductSync(productOne);
		verify(productRep,times(1)).save(productOne);
	}
	
}
