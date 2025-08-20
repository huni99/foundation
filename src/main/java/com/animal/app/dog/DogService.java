package com.animal.app.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {
	@Autowired
	private DogDao dogDao;

	public List<DogVO> list()throws Exception {
		return dogDao.list();
		
	}
	
}
