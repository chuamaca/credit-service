package com.bootcamp.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CustomerDto {
	
	private String documentNumber;

    private String firstName;

    private String lastName;

    private String documentType;

    private String type;

}
