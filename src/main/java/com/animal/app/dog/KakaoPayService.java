package com.animal.app.dog;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.animal.app.member.MemberVO;

@Service
public class KakaoPayService {
	@Value("${kakopay.secretKey}")
	private String secretKey;
	@Value("${kakopay.cid}")
	private String cid;
	@Autowired
	private DogDao dogDao;
	public KakaoPayReadyVO kakaoPay(MemberVO memberVO, DogVO dogVO) throws Exception{
		dogVO= dogDao.detail(dogVO);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "SECRET_KEY "+secretKey);
		headers.set("Content-Type","application/json");
		Map<String,Object> payParams = new HashMap();
		payParams.put("cid", cid);
		payParams.put("partner_order_id", "KAO20230318001");
		payParams.put("partner_user_id", memberVO.getMemberName());
		payParams.put("item_name", dogVO.getDogName());
		payParams.put("quantity", 1);
		payParams.put("total_amount", dogVO.getDogPrice());
		payParams.put("tax_free_amount",0);
		payParams.put("approval_url", "http://localhost:80/dogPay/success");
		payParams.put("cancel_url", "http://localhost:80/dogPay/cancel");
		payParams.put("fail_url", "http://localhost:80/dogPay/fail");
		
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(payParams,headers); 
		
		RestTemplate template= new RestTemplate();
		String url="https://open-api.kakaopay.com/online/v1/payment/ready";
		
		ResponseEntity<KakaoPayReadyVO> res = template.postForEntity(url, request, KakaoPayReadyVO.class);
		
		System.out.println(res.getBody());
		
		return res.getBody();
	}
}
