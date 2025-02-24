/**
 * 
 */
package com.Online_shop.Entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Many to one relation with Category
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // used as primary key
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String descriptions;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // it will remove respective image of product as well if product is remove
	private List<Image>  images;

	public Product(String name, String brand, BigDecimal price, int inventory, String descriptions,Category category) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.inventory = inventory;
		this.descriptions = descriptions;
		this.category = category;
	}
	
	
	

}
