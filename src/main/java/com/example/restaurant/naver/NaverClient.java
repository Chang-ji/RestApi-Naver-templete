package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    // application.yaml 에서 id값 가져오기
    @Value("${naver.client.id}")
    private String naverClientId;

    // application.yaml 에서 secret값 가져오기
    @Value("${naver.client.secret}")
    private String naverSecret;

    // application.yaml 에서 localUrl값 가져오기
    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    // application.yaml 에서 imageUrl값 가져오기
    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        // url 과 queryParams 만드는 부분
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        // header 만드는 부분
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // header 를 통해서 HttpEntity 만드는 부분
        var httpEntitiy = new HttpEntity<>(headers);

        // responseType 만드는 부분
        var responseType = new ParameterizedTypeReference<SearchLocalRes>() {};

        // uri, HttpMethod, HttpEntity, responseType 을 통해서 GET 요청
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntitiy,
                responseType
        );
        return responseEntity.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq) {

        // url 과 queryParams 만드는 부분
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        // header 만드는 부분
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // header 를 통해서 HttpEntity 만드는 부분
        var httpEntitiy = new HttpEntity<>(headers);

        // responseType 만드는 부분
        var responseType = new ParameterizedTypeReference<SearchImageRes>() {};

        // uri, HttpMethod, HttpEntity, responseType 을 통해서 GET 요청
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntitiy,
                responseType
        );

        return responseEntity.getBody();
    }
}
