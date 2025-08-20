package com.animal.app.dog;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.animal.app.member.validation.AddGroup;

import jakarta.validation.Valid;


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
	public void insert(DogVO dogVO) {
		
	}
	@PostMapping("add")
	public String insert(@Valid DogVO dogVO,BindingResult bindingResult , MultipartFile dogFile) throws Exception{
		if(bindingResult.hasErrors()) {
			return "dog/add";
		}
		dogService.insert(dogVO,dogFile);
		return "redirect:/dog/list";
	}
	@GetMapping("detail")
	public void detail(DogVO dogVO,Model model)throws Exception{
		dogVO=dogService.detail(dogVO);
		model.addAttribute("dogVO",dogVO);
		
	}
}
