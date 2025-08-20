package com.animal.app.dog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.animal.app.commons.FileManager;

@Transactional(rollbackFor =Exception.class )
@Service
public class DogService {
	@Autowired
	private DogDao dogDao;
	@Autowired
	private FileManager filemanager;
	@Value("${app.upload}")
	private String upload;
	@Value("${app.dog}")
	private String all;
	public List<DogVO> list()throws Exception {
		return dogDao.list();
		
	}

	public int insert(DogVO dogVO, MultipartFile dogFile) throws Exception{
		int result=	dogDao.insert(dogVO);
		
		String savename=filemanager.fileSave(upload+all, dogFile);
		Map<String,Object> map = new HashMap<>();
		map.put("saveName", savename);
		map.put("oriName", dogFile.getOriginalFilename());
		map.put("dogNo",dogVO.getDogNo());
		result =dogDao.insertFile(map);
		return result;
	}

	public DogVO detail(DogVO dogVO) {
		return dogDao.detail(dogVO);
	}
	
}
