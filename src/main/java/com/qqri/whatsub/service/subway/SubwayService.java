package com.qqri.whatsub.service.subway;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

@Service
public class SubwayService {
    public Object getItemsFromOpenApi(String stationCode) throws UnsupportedEncodingException {

        String serviceKey = "726f494a6763716f3539497552744f";
        String ST_CD = stationCode;
        String url = "http://openapi.seoul.go.kr:8088/" + serviceKey + "/xml/SearchFacilityByIDService/1/5/" + ST_CD;  //출력개수/역코드번호
        String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("KEY", decodeServiceKey)
                .queryParam("TYPE", "json")
                .queryParam("SERVICE", "SearchFacilityByIDService")
                .queryParam("START_INDEX", 111)
                .queryParam("END_INDEX", 222)
                .queryParam("STATION_CD", stationCode)

                .build(false);    //자동으로 encoding 되는 것 막는다

        Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        return response;
    }
}