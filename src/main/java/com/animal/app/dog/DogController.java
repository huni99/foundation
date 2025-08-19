package com.animal.app.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/dog/*")
public class DogController {
	@Autowired
	private DogService dogService;
	@GetMapping("list")
	public void list () {
		
	}
}
