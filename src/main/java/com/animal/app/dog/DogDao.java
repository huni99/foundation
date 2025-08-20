package com.animal.app.dog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DogDao {

	public List<DogVO> list()throws Exception;
	
}
