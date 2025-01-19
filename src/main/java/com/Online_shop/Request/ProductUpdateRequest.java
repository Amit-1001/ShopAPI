package com.Online_shop.Request;

import java.math.BigDecimal;

import com.Online_shop.Entity.Category;

import lombok.Data;

@Data
public class ProductUpdateRequest {
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String descriptions;
	private Category category;
	

}
