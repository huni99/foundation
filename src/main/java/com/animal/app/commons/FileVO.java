package com.animal.app.commons;

import com.animal.app.member.MemberVO;

import lombok.Data;

@Data
public class FileVO extends MemberVO{
	
	private String oriName;
	private String saveName;

}
