package com.animal.app.dog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DogDao {

	public List<DogVO> list()throws Exception;

	public int insert(DogVO dogVO)throws Exception;

	public int insertFile(Map<String,Object> map)throws Exception;

	public DogVO detail(DogVO dogVO);
	
}
