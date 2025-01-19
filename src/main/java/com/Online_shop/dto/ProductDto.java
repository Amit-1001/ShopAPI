package com.Online_shop.dto;

import java.math.BigDecimal;
import java.util.List;

import com.Online_shop.Entity.Category;
import com.Online_shop.Entity.Image;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDto {
	
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String descriptions;
	
	
	private Category category;
	
	private List<ImageDto>  images;


}
