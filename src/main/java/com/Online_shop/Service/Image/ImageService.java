package com.Online_shop.Service.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Online_shop.Entity.Image;
import com.Online_shop.Entity.Product;
import com.Online_shop.Exception.ImageNotFoundException;
import com.Online_shop.Repository.ImageRepository;
import com.Online_shop.Service.Product.IProductService;
import com.Online_shop.dto.ImageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private IProductService productService;
	

	@Override
	public Image getImageById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id)
				.orElseThrow(()-> new ImageNotFoundException("Image Not Found!"));
	}

	@Override
	public void deleteImageById(Long id) {
		// TODO Auto-generated method stub
		imageRepository.findById(id).ifPresentOrElse(imageRepository::delete
				,()-> {throw new ImageNotFoundException("Image not Found with id:"+ id);});
		
	}

	@Override
	public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
		// TODO Auto-generated method stub
		Product product = productService.getProductById(productId);
		
		List<ImageDto> savedImageDtos = new ArrayList<>();
		for(MultipartFile file:files) {
			try {
				Image image = new Image();
				image.setFileName(file.getOriginalFilename());
				image.setFileTyep(file.getContentType());
				image.setImage(new SerialBlob(file.getBytes()));
				image.setProduct(product);
				
				String buildDownloadUrl = "api/v1/images/image/download"; // api path
				String downloadUrl = buildDownloadUrl+image.getId();
				image.setDownloadUrl(downloadUrl);
				Image savedImage = imageRepository.save(image);
				
				savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
				
				imageRepository.save(savedImage);
				
				
				ImageDto imageDto = new ImageDto();
				
				imageDto.setImageId(savedImage.getId());
				imageDto.setImageName(savedImage.getFileName());
				imageDto.setDownloadUrl(savedImage.getDownloadUrl());
				savedImageDtos.add(imageDto); // adding new image 
				
			} catch (IOException | SQLException e) {
				// TODO: handle exception
				throw new RuntimeException(e.getMessage());
			}
		}
		return savedImageDtos;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		// TODO Auto-generated method stub
		Image image = getImageById(imageId);
		try {
			
			image.setFileName(file.getOriginalFilename());
			image.setFileName(file.getOriginalFilename());
			image.setImage(new SerialBlob(file.getBytes()));
			
			imageRepository.save(image);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
	}

}
