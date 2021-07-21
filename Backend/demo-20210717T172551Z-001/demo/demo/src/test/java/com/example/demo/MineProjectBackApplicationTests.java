package com.example.demo;

import com.example.demo.models.Category;
import com.example.demo.models.product;
import com.example.demo.repos.CategoryRepository;
import com.example.demo.repos.ProduitRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MineProjectBackApplicationTests {

	@Autowired
	public CategoryService categoryService ;
	@Autowired
	public ProductService productService ;
	@Autowired
	public CategoryRepository categoryRepository ;
	@Autowired
	public ProduitRepository productRepository ;


public MineProjectBackApplicationTests(){

}
	@Test
	public void addCategory( ){
		Category c = new Category();
		c.setNom("category1");
		c.setQt(220);
		categoryService.addCategory(c);
	}

	@Test
	public void showCategories(){

		categoryRepository.findAll();
	}
/*
	@Test

	//	@Test
	public void deleteCategory(){
		long id = 1L ;
		categoryRepository.deleteById(id);
	}
	@Test
	public void updateCategory(){
		long id = 2L ;
		if(categoryRepository.findById(id).isPresent()){
			Category cat = categoryRepository.findById(id).get() ;
			cat.setDate_creation(cat.getDate_creation());
			cat.setQt(300);
			cat.setNom("cat2 ");
			categoryService.updateCategory(cat);

		}

	}
	@Test
	public void addProduct() {
		product product = new product();
		long cat_id = 2L ;
		if(categoryRepository.findById(cat_id).isPresent()){
			Category cat =categoryRepository.findById(cat_id).get() ;
			product.setCategory(cat);
			product.setNom("prod1");
			product.setQuantite(89);
			productService.addProduct(product);
		}

	}
	@Test
	public void showProducts() {

		productRepository.findAll();
	}
	@Test
	public void showProductsInCat() {
		long catid = 2L;
		productRepository.showProductsInCat(catid);
	}
	@Test
	public void showProduct() {
		long id = 4 ;
		productRepository.findById(id);
	}
	@Test
	public void deleteProduct() {
		long id = 2 ;
		productRepository.deleteById(id);
	}
	@Test
	public void updateProduct() {
		long id = 5L ;
		if(productRepository.findById(id).isPresent()){
			product prod = productRepository.findById(id).get();
			prod.setDate_creation(prod.getDate_creation());
			prod.setDisponble(true);
			prod.setQuantite(69);
			prod.setNom("prod5");
			productService.updateProduct(prod);

		}


	}


*/


}
