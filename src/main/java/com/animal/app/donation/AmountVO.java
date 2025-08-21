package com.animal.app.donation;

import lombok.Data;

@Data
public class AmountVO {
	
	private int total;      // 전체 결제 금액
    private int tax_free;   // 비과세 금액
    private int vat;        // 부가세
    private int point;      // 사용한 포인트
    private int discount;   // 할인 금액

}
