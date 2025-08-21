package com.animal.app.member;

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
import com.animal.app.member.validation.UpdateGroup;

import jakarta.servlet.http.HttpSession;


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
	// AddGroup으로 지정된 클래스만 사용하겠다
	// @Validated를 쓰고 검증 그룹을 지정하지 않으면 그룹이 없는 것들만 검증함
	// BindingResult : 매개변수 위치 중요 / MVC에서 폼 입력값 검증(Validation) 결과를 담는 객체
	// 반드시 @Validated 또는 @Valid가 붙은 객체 바로 뒤에 선언해야 됨 -> 해당 객체의 결과가 bindingResult에 바인딩 됨
	@PostMapping("join")
	public String join(@Validated({AddGroup.class, UpdateGroup.class}) MemberVO memberVO, BindingResult bindingResult ,MultipartFile profile) throws Exception {
		
		// 권한 변환: ADMIN → 1, USER → 2
	    if("ADMIN".equals(memberVO.getMemberRoleStr())) {
	        memberVO.setMemberRole(1);
	    } else {
	        memberVO.setMemberRole(2);
	    }
		
		// Service 쪽에서 MemberVO와 BindingResult를 검증
		boolean check = memberService.hasMemberError(memberVO, bindingResult);
		
		// 에러가 있다면
		if(check) {
			// 회원가입 페이지로 리턴
			return "member/join";
		}
		
		// 검증 통과 시 회원가입 처리
		// DB 저장 + 프로필 파일 저장
		int result = memberService.join(memberVO, profile);
		
		// 에러가 없으면 / 페이지로 리턴
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
	
	
	// update
	@GetMapping("update")
	public String update(HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		model.addAttribute("memberVO",memberVO);
		return "member/memberUpdate";
	}
	
	
	// update
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) MemberVO memberVO, BindingResult bindingResult, MultipartFile profile, HttpSession session) throws Exception {
		
		// 에러가 있다면 수정 페이지(memberUpdate)로 리턴
		if(bindingResult.hasErrors()) {
			return "member/memberUpdate";
		}
		
		// 세션에서 로그인 사용자 정보 가져오기
		MemberVO member = (MemberVO)session.getAttribute("member");
		memberVO.setMemberNo(member.getMemberNo());  // PK(MEMBER_NO) 세팅
		memberVO.setMemberId(member.getMemberId());  // ID도 세팅
		
		// DB update 시도
		int result = memberService.update(memberVO, profile);
		
		// 성공 시 세션 갱신
//		if (result > 0) {
//			memberVO.setMemberPassword(member.getMemberPassword());
//			memberVO = memberService.login(memberVO);
//			session.setAttribute("member", memberVO);
//		}
		
		if (result > 0) {
		    MemberVO newMember = memberService.detail(memberVO.getMemberNo());
		    session.setAttribute("member", newMember);
		}

		
		return "redirect:./detail";
		
	}
	
	
	// 프로필 detail
	@GetMapping("detail")
	public void detail() throws Exception {
		
	}
	
	
	

}
