package com.animal.app.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	// Join(회원가입)
	public int join(MemberVO memberVO) throws Exception;
	
	// profileInsert(프로필)
	public int profileInsert(ProfileVO profileVO) throws Exception;
	
	// Login(로그인)
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	// Logout(로그아웃)
	public MemberVO logout(MemberVO memberVO) throws Exception;
	
	// 회원에게 기본 권한 부여
	public int addRole(Map<String, Object> map) throws Exception; 
	
	// update(정보 수정)
	public int update(MemberVO memberVO) throws Exception;

	
	

}
