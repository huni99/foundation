package com.animal.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// join
	@GetMapping("join")
	public void join(MemberVO memberVO) throws Exception {
		
	}
	
	// join
	@PostMapping("join")
	public String join(MemberVO memberVO, MultipartFile profile) throws Exception {
	
		return "redirect:/";
	}
	
	// login
	@GetMapping("login")
	public void login() throws Exception {
		
	}
	
	// login
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session) throws Exception {
		memberVO = memberService.login(memberVO);
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
		}
		 
		return "redirect:/";
	}
	
	// logout
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
	
	
	

}
