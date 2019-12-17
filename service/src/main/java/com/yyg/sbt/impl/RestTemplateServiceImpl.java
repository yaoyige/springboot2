package com.yyg.sbt.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yyg.sbt.service.RestTemplateService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

//@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    @Resource
    RestTemplate restTemplate;

    public <T> T get(JSONObject p2,String appId,String appKey,String appSercert,String sysurl,String api,T t){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId",appId);
        jsonObject.put("appKey",appKey);
        jsonObject.put("appSercert",appSercert);
        String realUrl =sysurl+ api;
        Object o = preRequest(p2, JSON.parseObject(jsonObject.toJSONString(), Map.class));
        Object body = restTemplate.postForEntity(realUrl, o, t.getClass()).getBody();
        return (T) body;
    }

    private  Object preRequest(JSONObject bizParams, Map<String,String> extendParams){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("app-key",extendParams.get("appKey"));

        Date  date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(date);
        String sign = DigestUtils.md5Hex(extendParams.get("appSecret") + format + bizParams.toJSONString());

        JSONObject postData = new JSONObject();
        postData.put("app_id",extendParams.get("appId"));
        postData.put("timestamp",format);
        postData.put("sign_type","md5");
        postData.put("sign",sign);
        postData.put("version","1");
        postData.put("charset","utf-8");
        postData.put("biz_params",bizParams.toJSONString());

        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(postData,headers);
        return httpEntity;
    }
}
