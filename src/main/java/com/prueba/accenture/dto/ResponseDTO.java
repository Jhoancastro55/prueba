package com.prueba.accenture.dto;

import lombok.Data;

@Data
public class ResponseDTO {

	private String message;
	
	public ResponseDTO() {
		
	}

	public ResponseDTO(String message) {
		this.message = message;
	}



	
	
	
	
}
