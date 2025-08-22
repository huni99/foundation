package com.animal.app.dog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.animal.app.member.MemberVO;

@Mapper
public interface DogDao {

	public List<DogVO> list()throws Exception;

	public int insert(DogVO dogVO)throws Exception;

	public int insertFile(Map<String,Object> map)throws Exception;

	public DogVO detail(DogVO dogVO)throws Exception;

	public List<DogVO> cartList(MemberVO memberVO)throws Exception;

	public int cartAdd(Map<String, Long> map)throws Exception;

	public int cartDelete(Map<String, Object> map) throws Exception;

	public int delete(Long item_name)throws Exception;

	public int adoptLog(Map<String, Long> map)throws Exception;
	
}
