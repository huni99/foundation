package com.animal.app.dog;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DogVO {
	private Long dogNo;
	private String dogName;
	private LocalDate dogBirth;
	private int dogGender;
	private String dogType;
	private boolean dogActive;
	private boolean dogInoculation;
	private int dogPrice;
	private String dogInfo;
}
