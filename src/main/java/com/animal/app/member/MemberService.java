package com.animal.app.member;

import java.util.HashMap;
import java.util.Map;

import com.animal.app.commons.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Service

// 메서드 실행을 하나의 트랜잭션으로 묶어주는 어노테이션
// 여러 DAO 작업이 수행되더라도 모두 성공하면 커밋, 하나라도 에러나면 롤백시켜줌
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
    private FileManager fileManager;

	@Value("${app.upload}")
	private String upload;
	
	@Value("${app.member}")
	private String all;

	
	// Join
	public int join(MemberVO memberVO, MultipartFile profile) throws Exception {
		// 회원 정보(MemberVO)를 DB에 insert
		int result = memberDAO.join(memberVO);
		
		// 프로필 정보(ProfileVO) 객체 생성(초기값은 기본 이미지)
		ProfileVO profileVO = new ProfileVO();
		profileVO.setMemberNo(memberVO.getMemberNo());  // 가입한 회원 아이디 저장
		profileVO.setSaveName("default.jpg");  // 기본 저장 파일명
		profileVO.setOriName("default.jpg");  // 기본 원본 파일명
		
		// 프로필 이미지 파일이 업로드 된 경우
		// null이 아니면서 비어있지 않음
		if(profile != null && !profile.isEmpty()) {
			// 업로드된 파일을 서버에 저장하고, 저장된 파일명을 profileVO에 세팅
			profileVO.setSaveName(fileManager.fileSave(upload+all, profile));
			
			// 사용자가 업로드한 파일의 원래 이름을 저장
			profileVO.setOriName(profile.getOriginalFilename());
		}
		
		// 프로필 정보를 DB에 insert
		result = memberDAO.profileInsert(profileVO);
		
		// 회원 권한 정보를 담을 Map 생성
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberVO.getMemberId());  // 가입한 회원 아이디
		map.put("memberRole", memberVO.getMemberRole()); 
		
		// 회원에게 기본 권한 부여
		result = memberDAO.addRole(map);
		
		return result;
				
	}
	
	
	// hasMemberError
	// 검증 메서드
	// 이미 사용중인 아이디인지 검사
	// 입력한 비밀번호 두 개가 같은지 검사
	public boolean hasMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		// check 값이 true이면 검증 실패
		// check 값이 false이면 검증 통과
		boolean check = false;
		
		// 1. Annotation 검증
		check = bindingResult.hasErrors();
		
		// 2. 사용자 정의로 패스워드가 일치하는지 검사
		// 입력한 비밀번호가 같지 않으면 true
		if(!memberVO.getMemberPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "", "비밀번호가 일치하지 않습니다"); 
		}
		
		// 3. ID 중복 검사
		MemberVO result = memberDAO.login(memberVO);
		
		// result가 null이 아니라면 ID가 중복임
		if(result != null) {
			check = true;
		bindingResult.rejectValue("memberId", "", "이미 사용중인 아이디입니다");  // member.id.equal : "이미 사용중인 아이디입니다"라는 메세지가 출력 됨 
		}
		
		// 같으면 check를 리턴
		return check;
		
	}
	
	
	// Login
	public MemberVO login(MemberVO memberVO) throws Exception {
		MemberVO checkVO = memberDAO.login(memberVO);
		
		if(checkVO != null && memberVO.getMemberPassword().equals(checkVO.getMemberPassword())) {
			return checkVO;
		}
		
		return null;
	}
	
	// update(정보 수정)
	public int update(MemberVO memberVO) throws Exception {
		return memberDAO.update(memberVO);
	}
	
	
	
	
}
