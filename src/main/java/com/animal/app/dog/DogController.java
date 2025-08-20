package com.animal.app.dog;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/dog/*")
public class DogController {
	@Autowired
	private DogService dogService;
	@GetMapping("list")
	public void list (Model model)throws Exception {
		List<DogVO> list = dogService.list();
		model.addAttribute("dog",list );
	}
	
	@GetMapping("add")
	public void insert() {
		
	}
	@PostMapping("add")
	public void insert(DogVO dogVO , MultipartFile dogFile) {
		
	}
}
