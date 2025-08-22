package com.animal.app.dog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.animal.app.member.MemberVO;

@Service
public class KakaoService {
	@Value("${kakopay.secretKey}")
	private String secretKey;
	@Value("${kakopay.cid}")
	private String cid;
	@Autowired
	private DogDao dogDao;
	private KakaoPayReadyVO kakaoReady;
	private MemberVO memberVO;

	public KakaoPayReadyVO kakaoPay(MemberVO memberVO, DogVO dogVO) throws Exception {
		dogVO = dogDao.detail(dogVO);
		this.memberVO = memberVO;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "SECRET_KEY " + secretKey);
		headers.set("Content-Type", "application/json");
		Map<String, Object> payParams = new HashMap();
		payParams.put("cid", cid);
		payParams.put("partner_order_id", "KAO20230318001");
		payParams.put("partner_user_id", memberVO.getMemberName());
		payParams.put("item_name", dogVO.getDogNo());
		payParams.put("quantity", 1);
		payParams.put("total_amount", dogVO.getDogPrice());
		payParams.put("tax_free_amount", 0);
		payParams.put("approval_url", "http://localhost:80/dog/success");
		payParams.put("cancel_url", "http://localhost:80/dog/cancel");
		payParams.put("fail_url", "http://localhost:80/dog/fail");

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(payParams, headers);

		RestTemplate template = new RestTemplate();
		String url = "https://open-api.kakaopay.com/online/v1/payment/ready";

		kakaoReady = template.postForObject(url, request, KakaoPayReadyVO.class);

		return kakaoReady;
	}

	public KakaoApproveResponse kakaoPayApprove(String pgToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "SECRET_KEY " + secretKey);
		headers.set("Content-Type", "application/json");

		Map<String, Object> payParams = new HashMap();
		payParams.put("cid", cid);
		payParams.put("tid", kakaoReady.getTid());
		payParams.put("partner_order_id", "KAO20230318001");
		payParams.put("partner_user_id", memberVO.getMemberName());
		payParams.put("pg_token", pgToken);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(payParams, headers);

		RestTemplate template = new RestTemplate();
		String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
		
		KakaoApproveResponse res = template.postForObject(url, request, KakaoApproveResponse.class);
		return res;
	}
}
