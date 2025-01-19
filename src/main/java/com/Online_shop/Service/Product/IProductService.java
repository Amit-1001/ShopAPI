package com.Online_shop.Service.Product;

import java.util.List;

import com.Online_shop.Entity.Product;
import com.Online_shop.Request.AddProductRequest;
import com.Online_shop.Request.ProductUpdateRequest;
import com.Online_shop.dto.ProductDto;

public interface IProductService {
	Product addProduct(AddProductRequest product);
	Product getProductById(Long id);
	
	void deleteProductById(Long id);
	Product updateProduct(ProductUpdateRequest request, Long productId);
	
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByBrand(String brand);
	List<Product> getProductByCategoryAndBrand(String category, String brand);
	List<Product> getProductsByName(String name);
	List<Product> getProductByBrandAndName(String brand, String name);
	
	Long countProductsByBrandAndName(String brand, String name);
	ProductDto convertToDto(Product product);
	List<ProductDto> getConvertedProduct(List<Product> products);
	

}
