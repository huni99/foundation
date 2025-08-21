package com.animal.app.donation;


import java.util.Date;

import lombok.Data;

@Data
// 후원VO
public class DonationVO {
	
	private Long donationId;
    private Long memberNo;
    private Long dogNo;
    private String tid;
    private int amount;
    private String status;
    private Date aprovedAt;

}
