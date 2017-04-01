package com.yasinyt.cos.util.http;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.common.collect.Maps;

/**
 *httpclient工具类,基于httpclient 4.x
 */
public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * post请求
     * @param url
     * @param formParams
     * @return
     */
    public static String doPost(String url, Map<String, String> formParams) {
        if (MapUtils.isEmpty(formParams)) {
            return doPost(url);
        }
        try {
            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
            for (Entry<String, String> entry : formParams.entrySet()) {
            	requestEntity.add(entry.getKey(), entry.getValue());
            }
            return RestClient.getClient().postForObject(url, requestEntity, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * post请求
     * @param url
     * @return
     */
    public static String doPost(String url) {
        try {
            return RestClient.getClient().postForObject(url, HttpEntity.EMPTY, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * get请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        try {
            return RestClient.getClient().getForObject(url, String.class);
        } catch (Exception e) {
            LOGGER.error("GET请求出错：{}", url, e);
        }
        return StringUtils.EMPTY;
    }

    
    public static void main(String[] args) {
    	ConcurrentMap<String,String> newConcurrentMap = Maps.newConcurrentMap();
    	newConcurrentMap.put("receiver", "Tencent");
    	newConcurrentMap.put("messageType", "INVESTMENT");
    	String doPost = HttpClientUtils.doPost("http://localhost:8083/cos/message/testJson", newConcurrentMap);
    	System.err.println(doPost);
	}
}