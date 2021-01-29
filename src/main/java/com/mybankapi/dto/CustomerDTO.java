package com.mybankapi.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		private String firstName;
		private String lastName;
		private String email;
		private Long mobile;
		private String panNumber;
		private String aadharNumber;
}
