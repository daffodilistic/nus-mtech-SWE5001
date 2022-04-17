package com.nus.team3.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.nus.team3.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpSender {

    public boolean IS_LOCAL = Boolean.parseBoolean(System.getProperty("isLocal"));
    public final String ROOT_URL = IS_LOCAL ? "http://localhost:9091": "http://<your_gcp_host>:<your_gcp_port>";
    public final String SAVE_TXN_URL = "/transaction/saveTxn";

    private static final Logger logger = LoggerFactory.getLogger(HttpSender.class);

    public HttpSender() {
    }

    public void sendOrder(Order o){
        String url = ROOT_URL + SAVE_TXN_URL;
        logger.info("Running on JVM system property: isLocal={}", System.getProperty("isLocal"));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity<>(o.toString(), httpHeaders);
        ResponseEntity result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, JsonNode.class);
    }
}
