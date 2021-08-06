package com.xiaosecond.shop.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RestTemplateUtil {


    /**
     * 不带请求头post请求 - application/json
     * @param url
     * @param params
     * @return
     */
    public  String post(String url ,Map<String ,Object> params ){
        return post(url  ,null ,params );
    }

    /**
     * 带请求头post请求 - application/json
     * @param url
     * @param heards
     * @param params
     * @return
     */
    public  String post(String url , Map<String ,String> heards ,Map<String ,Object> params ){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Connection", "keep-alive");
        if(heards != null ){
            for (String key : heards.keySet()) {
                httpHeaders.add( key , heards.get(key));
            }
        }
        return doPost(url , httpHeaders , params);
    }

    /**
     * 不带请求头post请求 - application/x-www-form-urlencoded
     * @param url
     * @param params
     * @return
     */
    public  String postForm(String url ,Map<String ,Object> params ){
        return postForm(url  ,null ,params );
    }

    /**
     * 带请求头post请求 - application/x-www-form-urlencoded
     * @param url
     * @param heards
     * @param params
     * @return
     */
    public  String postForm(String url , Map<String ,String> heards ,Map<String ,Object> params ){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.add("Connection", "keep-alive");
//        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        if(heards != null ){
            for (String key : heards.keySet()) {
                httpHeaders.add( key , heards.get(key));
            }
        }
        return doPost(url , httpHeaders , params);
    }

    /**
     * 带请求头post请求
     * @param url
     * @param httpHeaders
     * @param params
     * @return
     */
    private  String doPost(String url , HttpHeaders httpHeaders ,Map<String ,Object> params ){

        //设置参数
        MultiValueMap<String, Object> hashMap = new LinkedMultiValueMap<String, Object>();
        if(params != null ){
            for (String key : params.keySet()) {
                hashMap.add( key , params.get(key).toString());
            }
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(hashMap, httpHeaders);
        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(url , HttpMethod.POST,requestEntity, String.class);
        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        //获得返回值
        String body = resp.getBody();
        log.info("doPost:{}>>>>>>>>>result:{}" , url , body);
        return body;
    }

}
