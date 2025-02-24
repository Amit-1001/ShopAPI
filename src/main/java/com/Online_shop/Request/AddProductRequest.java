package com.Online_shop.Request;

import java.math.BigDecimal;

import com.Online_shop.Entity.Category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together: 
 * In other words, @Data generates all the boilerplate that is normally associated with simple POJOs (Plain Old Java Objects)
 *  and beans: getters for all fields, setters for all non-final fields, and appropriate toString, 
 *  equals and hashCode implementations that involve the fields of the class, and a constructor that initializes all final fields,
 *   as well as all non-final fields with no initializer that have been marked with @NonNull, in order to ensure the field is never null.
 * **/
@Data
public class AddProductRequest {
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String descriptions;
	private Category category;
	

}
