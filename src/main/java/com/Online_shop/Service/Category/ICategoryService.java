package com.Online_shop.Service.Category;

import java.util.List;

import com.Online_shop.Entity.Category;

public interface ICategoryService{
	Category getCategoryById(Long id);
	Category getCategoryByName(String name);
	
	List<Category> getAllCategoies();
	
	Category addCategory(Category category);
	Category updateCategory(Category category, Long id);
	
	void deleteCategoryById(Long id);

}
