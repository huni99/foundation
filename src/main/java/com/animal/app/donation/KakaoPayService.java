package com.animal.app.donation;

import org.springframework.stereotype.Service;

@Service
public class KakaoPayService {
// KakaoPayProperties에서 불러온 설정값을 확인하서나 사용할 수 있도록 관리
// 현재는 단순 테스트용 메서드(test)만 구현되어 있음	
	
	// KakaoPayProperties (host, adminKey, cid 등) 불러오기
	 private final KakaoPayProperties properties;

	    public KakaoPayService(KakaoPayProperties properties) {
	        this.properties = properties;
	    }

	    public void test() {
	        System.out.println(properties.getHost());  // https://kapi.kakao.com
	        System.out.println(properties.getAdminKey()); 
	        System.out.println(properties.getCid());   // TC0ONETIME
	    }

}
