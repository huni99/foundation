package com.animal.app.donation;

import lombok.Data;

@Data
public class KakaoPayReadyResponseVO {

	private String tid;                     // 결제 고유 번호
	private String next_redirect_app_url;   // 앱 redirect url
	private String next_redirect_mobile_url;// 모바일 redirect url
	private String next_redirect_pc_url;    // PC redirect url
	private String created_at;              // 결제 준비 요청 시간
}
