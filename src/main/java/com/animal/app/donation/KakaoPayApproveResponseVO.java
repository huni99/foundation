package com.animal.app.donation;

import lombok.Data;

@Data
public class KakaoPayApproveResponseVO {
	
	
	private String aid;                // 요청 고유 번호
    private String tid;                // 결제 고유 번호
    private String cid;                // 가맹점 코드
    private String sid;                // 정기결제용 ID
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private AmountVO amount;             // 결제 금액 객체
    private String item_name;
    private String item_code;
    private int quantity;
    private String created_at;
    private String approved_at;        // 결제 승인 시간
}
