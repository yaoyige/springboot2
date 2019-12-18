package com.yyg.sbt.service.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NoHttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *  调用外部接口类
 */
@Slf4j
public class RestemplateUtil {
    @Autowired
    private RestTemplate restTemplate;
    @Value(value = "${ams.sysUrl}")
    private String sysUrl;

    @Value(value = "${ams.getByEid}")
    private String getByEid;

    private static final String EMAILSUFFIX = "@163.com";

    private static final String CODE = "code";

    private static final String SUCCESS = "200";
    /**
     * 调用外部接口通用方法
     * @param p2 条件
     * @param appId 标识
     * @param appSecret 密码
     * @param sysUrl 路径
     * @param api 后半截路径
     * @param t  返回类型
     * @param <T> 泛型
     * @return
     */
    public <T> T  get(JSONObject p2, String appId, String appSecret, String sysUrl, String api, T t){
           //真实路径
            String realUrl = sysUrl+api;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String format = simpleDateFormat.format(date);
            //日期，密码，参数 一起加密
            String s = DigestUtils.md5Hex(appSecret + format + p2.toJSONString());



        return (T)p2;
    }

    @Retryable(value = {NoHttpResponseException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000L, multiplier = 1))
    private Object post(String realUrl, HttpEntity request, Class<?> clazz) {
        Object object = null;
        long beginTime = System.currentTimeMillis();
        try {
            object = restTemplate.postForEntity(realUrl, request, clazz).getBody();
            log.info("call interface " + realUrl + " ,param:{},return:{},time:{}", request.getBody(), JSON.toJSONString(object), System.currentTimeMillis() - beginTime);
        } catch (RestClientException e) {
            log.error("call interface " + realUrl + " error,param:{},exception:{},time:{}", request.getBody(), e, System.currentTimeMillis() - beginTime);
            if (!"".equals(realUrl)) {
                throw new ServerException(String.format(I18nMessageUtil.getMessage("call.interface.error"), realUrl), e);
            }
        }
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            if (!SUCCESS.equals(jsonObject.getString(CODE))) {
                log.error("call interface " + realUrl + " error,param:{},return:{},time:{}", request.getBody(), jsonObject.toString(), System.currentTimeMillis() - beginTime);
                throw new ServerException(String.format(I18nMessageUtil.getMessage("call.interface.error"), realUrl), null);
            }
        }
        return object;
    }
}
