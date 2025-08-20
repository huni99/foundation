package com.animal.app.member;

import com.animal.app.member.validation.AddGroup;
import com.animal.app.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class MemberVO {
	
	// (message = "") : error 메세지를 직접 정해줄 수 있음, 검증 실패 시 사용자에게 보여줄 메세지
	// (group = {} ) : 검증 그룹 지정, 사용할 그룹에서만 사용(배열이면 {} 안에, 하나면 그냥 작성하면 됨)
	// @NotBlank : 문자열 전용 유효성 검사
	
	private Long memberNo;
	
	@NotBlank(message = "ID 입력은 필수입니다.", groups = AddGroup.class)
	private String memberId;
	
	@NotBlank(message = "비밀번호를 입력하세요.", groups = AddGroup.class)
    @Size(min = 6, max = 12, message = "비밀번호는 6~12자리여야 합니다.", groups = AddGroup.class)
    private String memberPassword;
	
	// 두 번재 입력한 비밀번호가 첫 번째 입력한 비밀번호와 같은지 검사
	@NotBlank(message = "비밀번호가 일치하지 않습니다.", groups = AddGroup.class)
	private String passwordCheck;
	
	private String memberPhone;
	
	// 이메일 검사 Annotation
	@Email(message = "올바른 이메일 형식이 아닙니다.", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "이메일 입력은 필수입니다.", groups = AddGroup.class)
    private String memberEmail;
	
	@NotBlank(message = "이름 입력은 필수입니다.", groups = {AddGroup.class})
    private String memberName;
	
	private String memberRoleStr; // form에서 받은 ADMIN/USER
	private int memberRole;        // DB에 저장할 값 1/2

	private ProfileVO profileVO;
	
	
}
