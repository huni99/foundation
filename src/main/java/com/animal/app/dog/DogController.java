package com.animal.app.dog;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.animal.app.donation.DonationService;
import com.animal.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping(value="/dog/*")
@RequiredArgsConstructor
public class DogController {
	private final KakaoService payService;
	@Autowired
	private DogService dogService;
	
	@Autowired
	private DonationService donationService;
	
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
		
		Integer donation =  donationService.getTotalDonation(dogVO.getDogNo());
		
		if(donation == null) {
			donation=0;
		}
		
		model.addAttribute("totalDonation", donation);
		
			
		
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
		MemberVO memberVO= (MemberVO)session.getAttribute("member");
		KakaoPayReadyVO res = payService.kakaoPay(memberVO,dogVO);
		return res;
	}
	
	@GetMapping("success")
	public String Success(@RequestParam("pg_token") String pgToken ,Model model,HttpSession session)throws Exception{
		KakaoApproveResponse res = payService.kakaoPayApprove(pgToken);
		int result= dogService.delete(Long.parseLong(res.getItem_name()));
		dogService.adoptLog(((MemberVO)session.getAttribute("member")).getMemberNo(),Long.parseLong(res.getItem_name()));
		
		model.addAttribute("url","/dog/list");
		model.addAttribute("msg","결제 완료 ");
		
		return "/dog/result";
	}
	@GetMapping("fail")
	public String fail(Model model) {
		model.addAttribute("url","/dog/list");
		model.addAttribute("msg","결제 실패 ");
		return "/dog/result";
	}
	@GetMapping("cancel")
	public String cancel(Model model) {
		model.addAttribute("url","/dog/list");
		model.addAttribute("msg","결제 취소 ");
		return "/dog/result";
	}
	@GetMapping("/denied")
	public String denied(Model model) {
	    model.addAttribute("msg", "권한이 없습니다");
	    model.addAttribute("url", "/");
	    return "dog/result";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
