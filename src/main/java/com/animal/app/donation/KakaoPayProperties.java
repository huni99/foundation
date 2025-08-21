package com.animal.app.donation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "kakao.pay")
@Data
public class KakaoPayProperties {

	private String host;
    private String adminKey;
    private String cid;
}
