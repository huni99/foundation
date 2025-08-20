package com.animal.app.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {
	@Autowired
	private DogDao dogDao;
	
}
