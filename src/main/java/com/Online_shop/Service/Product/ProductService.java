package com.Online_shop.Service.Product;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Online_shop.Entity.Product;
import com.Online_shop.Entity.*;
import com.Online_shop.Exception.ProductNotFoundException;
import com.Online_shop.Repository.CategoryRepository;
import com.Online_shop.Repository.ImageRepository;
import com.Online_shop.Repository.ProductRepository;
import com.Online_shop.Request.AddProductRequest;
import com.Online_shop.Request.ProductUpdateRequest;
import com.Online_shop.config.ShopConfig;
import com.Online_shop.dto.ImageDto;
import com.Online_shop.dto.ProductDto;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public Product addProduct(AddProductRequest request) {
		// TODO Auto-generated method stub
		//check if the category is found in DB
		//If yes, set it as the new product category
		//If no, then save it as new category
		// The set as the new product category
		
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
				.orElseGet(()->{
					Category newCategory = new Category(request.getCategory().getName());
					return categoryRepository.save(newCategory);
				});
		
		request.setCategory(category);
		return productRepository.save(createProducts(request, category));
	}
	
	private Product createProducts(AddProductRequest request,Category category) {
		return new Product(
					request.getName(),
					request.getBrand(),
					request.getPrice(),
					request.getInventory(),
					request.getDescriptions(),
					category
				);
	}

	
	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.orElseThrow(()-> new ProductNotFoundException("Product Not Found!"));
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		productRepository.findById(id).ifPresentOrElse(productRepository::delete
				,()-> {throw new ProductNotFoundException("Product Not Found!");});
		
	}

	@Override
	public Product updateProduct(ProductUpdateRequest request, Long productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId)
				.map(existingProduct -> updateExistingProduct(existingProduct, request))
				.map(productRepository::save)
				.orElseThrow(()-> new ProductNotFoundException("Product Not Found!"));
		
	}
	
	private Product updateExistingProduct(Product existingProduct,ProductUpdateRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setDescriptions(request.getDescriptions());
		
		Category category = categoryRepository.findByName(request.getCategory().getName());
		existingProduct.setCategory(category);
		return existingProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		// TODO Auto-generated method stub
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductByCategoryAndBrand(String category, String brand) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryNameAndBrand(category,brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return productRepository.findByBrandAndName(brand,name);
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return productRepository.countByBrandAndName(brand, name);
	}
	
	@Override
	public List<ProductDto> getConvertedProduct(List<Product> products){
		return products.stream().map(this::convertToDto).toList();
	}
	
	@Override
	public ProductDto convertToDto(Product product) {
		//
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		
		List<Image> images =  imageRepository.findByProductId(product.getId());
		
		List<ImageDto> imageDtos = images.stream()
				.map(image-> modelMapper.map(image, ImageDto.class))
				.toList();
		productDto.setImages(imageDtos);
		return productDto;
	}

}
