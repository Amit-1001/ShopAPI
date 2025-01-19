package com.Online_shop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Online_shop.Entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	 List<Image> findByProductId(Long id);

}
