package com.Online_shop.Response;

import java.util.List;

import com.Online_shop.dto.ImageDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse {
	
	public ApiResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	private String message;
	private Object data;

}
