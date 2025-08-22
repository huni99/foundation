package com.animal.app.donation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.animal.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/donation/*")
@RequiredArgsConstructor
public class DonationController {
	
	
	private final DonationService donationService;

	@GetMapping("/ready")
	public String ready(@RequestParam("dogNo") Long dogNo, HttpSession session) throws Exception {
		// 현재 로그인한 회원 정보(member)와 선택한 강아지 번호(dogNo)를 기반으로 DonationService.ready() 호출
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    KakaoPayReadyResponseVO response = donationService.ready(member.getMemberNo(), dogNo);

	    // tid 저장 (approve에서 사용해야 함)
	    session.setAttribute("tid", response.getTid());

	    // 카카오페이 결제창으로 redirect
	    return "redirect:" + response.getNext_redirect_pc_url();
	}


	// 결제 성공
	@GetMapping("/success")
	public String success(@RequestParam("pg_token") String pgToken, @RequestParam("dogNo") Long dogNo, HttpSession session) throws Exception {
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    String tid = (String) session.getAttribute("tid");

	    // 카카오 결제 승인 처리
	    donationService.approve(member.getMemberNo(), dogNo, tid, pgToken);

	    // 세션에 성공 플래그 저장
	    session.setAttribute("donationSuccess", true);

	    // tid는 한 번 쓰고 제거
	    session.removeAttribute("tid");

	    // 다시 강아지 상세페이지로 이동
	    return "redirect:/dog/detail?dogNo=" + dogNo;
	}


	// 결제 취소
    @GetMapping("/cancel")
    public String cancel() throws Exception {
        return "redirect:/"; // 취소 시 홈으로
    }

    // 결제 실패
    @GetMapping("/fail")
    public String fail() throws Exception {
        return "redirect:/"; // 실패 시 홈으로
    }
	

}
