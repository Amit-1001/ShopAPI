package com.Online_shop.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ImageDto {
	private Long imageId;
	private String imageName;
	private String downloadUrl;
	
}
