package com.animal.app.dog;

import java.time.LocalDate;

import com.animal.app.commons.FileVO;

import lombok.Data;

@Data
public class DogVO {
	private Long dogNo;
	private String dogName;
	private LocalDate dogBirth;
	private Integer dogGender;
	private String dogType;
	private Boolean dogActive;
	private Boolean dogInoculation;
	private Integer dogPrice;
	private String dogInfo;
	private FileVO dogFileVO;
}
