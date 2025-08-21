package com.animal.app.donation;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonationDAO {
	
	int insertDonation(DonationVO donationVO) throws Exception;

}
