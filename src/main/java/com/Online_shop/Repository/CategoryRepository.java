package com.Online_shop.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.Online_shop.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

	boolean existsByName(String name);

}
