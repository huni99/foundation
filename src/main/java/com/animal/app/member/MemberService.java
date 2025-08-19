package com.animal.app.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private Transactional transactional;
	
	// Join
	public int join(MemberVO memberVO, MultipartFile profile) throws Exception {
		// 회원 정보(MemberVO)를 DB에 insert
		int result = memberDAO.join(memberVO);
		
		// 프로필 정보(ProfileVO) 객체 생성(초기값은 기본 이미지)
		ProfileVO profileVO = new ProfileVO();
		profileVO.setMemberId(memberVO.getMemberId());  // 가입한 회원 아이디 저장
		profileVO.setSaveName("default.jpg");  // 기본 저장 파일명
		profileVO.setOriName("default.jpg");  // 기본 원본 파일명
		
		// 프로필 이미지 파일이 업로드 된 경우
		// null이 아니면서 비어있지 않음
		if(profile != null && !profile.isEmpty()) {
			// 업로드된 파일을 서버에 저장하고, 저장된 파일명을 profileVO에 세팅
			
			// 사용자가 업로드한 파일의 원래 이름을 저장
			profileVO.setOriName(profile.getOriginalFilename());
		}
		
		// 프로필 정보를 DB에 insert
		result = memberDAO.profileInsert(profileVO);
		
		// 회원 권한 정보를 담을 Map 생성
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberVO.getMemberId());  // 가입한 회원 아이디
		map.put("memberRole", 2);  // 기본 권한 번호(일반 회원 : 2)
		
		// 회원에게 기본 권한 부여
		result = memberDAO.addRole(map);
		
		return result;
				
	}
	
	
	// hasMemberError
	// 검증 메서드
	// 입력한 비밀번호 두 개가 같은지 검사
	
	
	
	
	// Login
	public MemberVO login(MemberVO memberVO) throws Exception {
		MemberVO checkVO = memberDAO.login(memberVO);
		
		if(checkVO != null && memberVO.getMemberPassword().equals(checkVO.getMemberPassword())) {
			return checkVO;
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
