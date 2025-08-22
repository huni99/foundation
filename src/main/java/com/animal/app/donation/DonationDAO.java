package com.animal.app.donation;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonationDAO {
	
	// insertDonation
	int insertDonation(DonationVO donationVO) throws Exception;
	
	// DOG_NO의 총 합계 
	Integer selectAmount(long dogNo) throws Exception;

}
