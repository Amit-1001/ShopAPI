package com.Online_shop.Service.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Online_shop.Entity.Category;
import com.Online_shop.Exception.AlreadyExistCategory;
import com.Online_shop.Exception.CategoryNotFoundException;
import com.Online_shop.Repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category Not Found!"));
	}

	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategoies() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return Optional.of(category).filter(c->!categoryRepository.existsByName(category.getName()))
				.map(categoryRepository::save)
				.orElseThrow(()-> new AlreadyExistCategory(category.getName()+" Already exists!s"));
	}
	
	@Override
	public Category updateCategory(Category category, Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(getCategoryById(id)).map(oldCategory ->
			{oldCategory.setName(category.getName());
				return categoryRepository.save(oldCategory);
			}).orElseThrow(()-> new CategoryNotFoundException("Category Not Found!"));
	}
	

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, ()->{
			throw new CategoryNotFoundException("Category Not Found!");
		});
		
	}

	

}
