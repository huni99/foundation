package com.animal.app.donation;

import org.springframework.stereotype.Service;

@Service
public class KakaoPayService {
	
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
