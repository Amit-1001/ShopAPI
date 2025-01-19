package com.Online_shop.Controller;

import java.net.http.HttpHeaders;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Online_shop.Entity.Image;
import com.Online_shop.Exception.ImageNotFoundException;
import com.Online_shop.Response.ApiResponse;
import com.Online_shop.Service.Image.IImageService;
import com.Online_shop.Service.Image.ImageService;
import com.Online_shop.dto.ImageDto;

import org.springframework.*;



import org.springframework.http.*;
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
	
	
	private static final HttpStatusCode INTERNAL_SERVER_ERROR = null;
	private static final HttpStatusCode NOT_FOUND = null;
	@Autowired
	private IImageService iImageService;
	
	@PostMapping("/upload")
	public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId){
	
			try {
				List<ImageDto> imageDtos = iImageService.saveImage(files,productId);

				return ResponseEntity.ok(new ApiResponse("Upload Success!",imageDtos));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!",e.getMessage()));
			}
	}
	
	@GetMapping("/image/download/{imageId}")
	public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException{
		Image image = iImageService.getImageById(imageId);
		ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int)image.getImage().length()));
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileTyep()))
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName()+"\"")
				.body(resource);
	}
	
	@PutMapping("/image/{imageId}/update")
	public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId,@RequestBody MultipartFile file){
		try {
			Image image = iImageService.getImageById(imageId);
			if(image != null) {
				iImageService.updateImage(file, imageId);
				return ResponseEntity.ok(new ApiResponse("Update Success!", null));
			}
		} catch (ImageNotFoundException e) {
			// TODO: handle exception
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update Failed!",INTERNAL_SERVER_ERROR));

	}
	
	
	@DeleteMapping("/image/{imageId}/delete")
	public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
		try {
			Image image = iImageService.getImageById(imageId);
			if(image != null) {
				iImageService.deleteImageById(imageId);
				return ResponseEntity.ok(new ApiResponse("Delete Success!", null));
			}
		} catch (ImageNotFoundException e) {
			// TODO: handle exception
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete Failed!",INTERNAL_SERVER_ERROR));

	}


}
