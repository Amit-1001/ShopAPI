
package com.Online_shop.Controller;

import java.nio.file.ProviderNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Online_shop.Entity.Product;
import com.Online_shop.Exception.ProductNotFoundException;
import com.Online_shop.Request.AddProductRequest;
import com.Online_shop.Request.ProductUpdateRequest;
import com.Online_shop.Response.ApiResponse;
import com.Online_shop.Service.Product.IProductService;
import com.Online_shop.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllProducts(){
	
			List<Product> products = productService.getAllProducts();
			List<ProductDto> convertedProductDto = productService.getConvertedProduct(products);
			
			if(convertedProductDto.isEmpty()) {
				return ResponseEntity.ok(new ApiResponse("Product not Found!",null));
			}
			
			return ResponseEntity.ok((new ApiResponse("success!",convertedProductDto)));
		
	}
	
	@GetMapping("/product/{id}/product")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
		try {
			Product product = productService.getProductById(id);
			ProductDto productDto = productService.convertToDto(product);
			
			return ResponseEntity.ok(new ApiResponse("Succes!",productDto));
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProducts(@RequestBody AddProductRequest product){
		try {
			Product newProduct = productService.addProduct(product);
			ProductDto productDto = productService.convertToDto(newProduct);
			return ResponseEntity.ok(new ApiResponse("Add product success!",productDto));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/product/{productId}/update")
	public ResponseEntity<ApiResponse> updateProducts(@RequestBody ProductUpdateRequest request, @PathVariable Long productId){
		try {
			Product updatedProduct = productService.updateProduct(request,productId);
			ProductDto productDto = productService.convertToDto(updatedProduct);

			
			return ResponseEntity.ok(new ApiResponse("Product updated!",productDto));
		} catch (ProviderNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/product/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
		try {
			Product product = productService.getProductById(productId);
			
			return ResponseEntity.ok(new ApiResponse("Deleted!",product));
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/products/by/brand-and-name")
	public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
		
			try {
				List<Product> products = productService.getProductByBrandAndName(brandName, productName);
				List<ProductDto> convertedProductDto = productService.getConvertedProduct(products);
				if(convertedProductDto.isEmpty()) {
					
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found!",null));

				}
				
				return ResponseEntity.ok(new ApiResponse("Success!",convertedProductDto));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

				
			}
		
	}
	@GetMapping("/products/by/category-and-brand")
	public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brandName){
		
			try {
				List<Product> products = productService.getProductByCategoryAndBrand(category, brandName);
				List<ProductDto> convertedProductDto = productService.getConvertedProduct(products);

				if(convertedProductDto.isEmpty()) {
					
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found!",null));

				}
				
				return ResponseEntity.ok(new ApiResponse("Success!",convertedProductDto));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
			}
		
	}
	
	@GetMapping("/products/{name}/products")
	public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
		try {
			List<Product> products = productService.getProductsByName(name);
			List<ProductDto> convertedProductDto = productService.getConvertedProduct(products);

			if(convertedProductDto.isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found!",null));

			}
			
			return ResponseEntity.ok(new ApiResponse("Success!",convertedProductDto));
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/products/by-brand")
	public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand){
		try {
			List<Product> products = productService.getProductsByBrand(brand);
			List<ProductDto> convertedProductDto = productService.getConvertedProduct(products);

			if(convertedProductDto.isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found!",null));

			}
			
			return ResponseEntity.ok(new ApiResponse("Success!",convertedProductDto));
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/products/{category}/all/products")
	public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String category){
		try {
			List<Product> products = productService.getProductsByCategory(category);
			List<ProductDto> convertedProductDto = productService.getConvertedProduct(products);

			if(convertedProductDto.isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Product Found!",null));

			}
			
			return ResponseEntity.ok(new ApiResponse("Success!",convertedProductDto));
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/products/count/by-brand/by-name")
	public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brand, @RequestParam String name){
		try {
			var productCount = productService.countProductsByBrandAndName(brand, name);	
			
			return ResponseEntity.ok(new ApiResponse("Product count!",productCount));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
		}
	}


}
