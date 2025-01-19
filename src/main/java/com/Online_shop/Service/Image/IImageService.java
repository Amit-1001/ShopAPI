package com.Online_shop.Service.Image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Online_shop.Entity.Image;
import com.Online_shop.dto.ImageDto;

public interface IImageService {
	Image getImageById(Long id);
	void deleteImageById(Long id);
	List<ImageDto> saveImage(List<MultipartFile> files, Long productId);
	void updateImage(MultipartFile file, Long imageId);
}
