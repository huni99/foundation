package com.animal.app.dog;

import java.time.LocalDate;

import com.animal.app.commons.FileVO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DogVO {
	
	private Long dogNo;
	@NotBlank
	private String dogName;
	@NotNull
	@Past
	private LocalDate dogBirth;
	private Integer dogGender;
	@NotBlank
	private String dogType;
	private Boolean dogActive;
	
	private Boolean dogInoculation;
	@NotNull
	@Positive
	private Integer dogPrice;
	private String dogInfo;
	private FileVO dogFileVO;
}
