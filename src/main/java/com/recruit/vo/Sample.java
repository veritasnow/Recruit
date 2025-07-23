package com.recruit.vo;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Sample {

	private String id;
	private String name;
	
	@Builder
	public Sample(String id, String name) {
		this.id   = id;
		this.name = name;
	}
	
}
