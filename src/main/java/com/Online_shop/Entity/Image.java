/**
 * 
 */
package com.Online_shop.Entity;

import java.sql.Blob;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  many to one relation with product
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fileName;
	private String fileTyep;
	
	@Lob
	private Blob image; // @Lob is used to store large object
	private String downloadUrl;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
