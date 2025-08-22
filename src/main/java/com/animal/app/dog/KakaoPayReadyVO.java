package com.animal.app.dog;

import lombok.Data;

@Data
public class KakaoPayReadyVO {
	private String tid;
	private String next_redirect_mobile_url;
	private String next_redirect_pc_url;
	private String partner_order_id;
}
