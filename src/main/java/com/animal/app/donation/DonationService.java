package com.animal.app.donation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakao.pay.host}")
    private String host;

    @Value("${kakao.pay.cid}")
    private String cid;

    @Value("${kakao.pay.admin-key}")
    private String adminKey;

    /**
     * ✅ 결제 준비 (결제창 호출)
     */
    public KakaoPayReadyResponseVO ready(Long memberNo, Long dogNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", cid);
        params.add("partner_order_id", "order_" + dogNo);
        params.add("partner_user_id", "member_" + memberNo);
        params.add("item_name", "유기견 후원");
        params.add("quantity", "1");
        params.add("total_amount", "5000"); // 고정 후원 금액
        params.add("tax_free_amount", "0");

        // 결제 성공/취소/실패 리다이렉트 URL
        params.add("approval_url", "http://localhost:8080/donation/success?dogNo=" + dogNo);
        params.add("cancel_url", "http://localhost:8080/donation/cancel");
        params.add("fail_url", "http://localhost:8080/donation/fail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        KakaoPayReadyResponseVO response = restTemplate.postForObject(
                host + "/v1/payment/ready",
                body,
                KakaoPayReadyResponseVO.class
        );

        // ✅ tid는 approve 단계에서 꼭 필요 → 세션이나 DB에 저장
        // session.setAttribute("tid", response.getTid());

        return response;
    }

    /**
     * ✅ 결제 승인 (DB 저장 포함)
     */
    public KakaoPayApproveResponseVO approve(Long memberNo, Long dogNo, String tid, String pgToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", cid);
        params.add("tid", tid);
        params.add("partner_order_id", "order_" + dogNo);
        params.add("partner_user_id", "member_" + memberNo);
        params.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        KakaoPayApproveResponseVO response = restTemplate.postForObject(
                host + "/v1/payment/approve",
                body,
                KakaoPayApproveResponseVO.class
        );

        // ✅ 결제 성공 시 DB 저장
        // 예: donationMapper.insertDonation(memberNo, dogNo, response.getAmount().getTotal());

        return response;
    }

}
