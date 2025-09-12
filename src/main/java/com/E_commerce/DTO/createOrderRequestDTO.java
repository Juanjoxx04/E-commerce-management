package com.E_commerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class createOrderRequestDTO {

	private String clientName;
	private Long productId;
	private int quantity;
}
