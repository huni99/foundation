package com.animal.app.dog;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.animal.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping(value="/dog/*")
public class DogController {
	@Autowired
	private KakaoPayService payService;
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
	@ResponseBody
	@PostMapping("cartAdd")
	public int cartAdd(HttpSession session,DogVO dogVO )throws Exception {
		MemberVO memberVO= (MemberVO)session.getAttribute("member");
		int result = dogService.cartAdd(dogVO,memberVO);
		if(result>0) {
			return result;
		}
		return 0;
	}
	@GetMapping("cartList")
	public void cartList(HttpSession session,Model model) throws Exception{
		MemberVO memberVO= (MemberVO)session.getAttribute("member");
		List<DogVO> list= dogService.cartList(memberVO);
		model.addAttribute("dog",list);
	}
	@ResponseBody
	@PostMapping("cartDelete")
	public int cartDelete(Long[] list,HttpSession session) throws Exception {
		MemberVO memberVO= (MemberVO)session.getAttribute("member");
		int result =dogService.cartDelete(list,memberVO);
		
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("adopt")
	public KakaoPayReadyVO dogAdopt(HttpSession session ,DogVO dogVO)throws Exception {
		System.out.println(dogVO.getDogNo());
		MemberVO memberVO= (MemberVO)session.getAttribute("member");
		KakaoPayReadyVO res = payService.kakaoPay(memberVO,dogVO);
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
