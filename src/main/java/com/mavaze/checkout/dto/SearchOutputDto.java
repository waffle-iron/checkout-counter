package com.mavaze.checkout.dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchOutputDto<E> {
	
	private long totalCount;
	
	private List<E> entities;
}
