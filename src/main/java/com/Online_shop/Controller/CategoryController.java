package com.Online_shop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.Online_shop.Entity.Category;
import com.Online_shop.Exception.AlreadyExistCategory;
import com.Online_shop.Exception.CategoryNotFoundException;
import com.Online_shop.Response.ApiResponse;
import com.Online_shop.Service.Category.ICategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllCategories(){
		try {
			List<Category> categories = categoryService.getAllCategoies();
			
			return ResponseEntity.ok(new ApiResponse("Found!",categories));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:",HttpStatus.INTERNAL_SERVER_ERROR));
		}
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCategory(@RequestBody Category newCategory){
		try {
			Category category = categoryService.addCategory(newCategory);
			
			return ResponseEntity.ok(new ApiResponse("Success!",category));
		} catch (AlreadyExistCategory e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
		}
				
	}
	
	@GetMapping("/category/{catId}/category")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long catId){
		try {
			Category category = categoryService.getCategoryById(catId);
			
			return ResponseEntity.ok(new ApiResponse("Fond!",category));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String categoryName){
		try {
			Category category = categoryService.getCategoryByName(categoryName);
			
			return ResponseEntity.ok(new ApiResponse("Fond!",category));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/category/{id}/delete")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
		try {
			categoryService.deleteCategoryById(id);
			
			return ResponseEntity.ok(new ApiResponse("Deleted!",null));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/category/{id}/update")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
		try {
			Category newCategory = categoryService.updateCategory(category, id);
			
			return ResponseEntity.ok(new ApiResponse("Category Updated!",newCategory));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
			
			
		}
	}

}
