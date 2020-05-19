package com.qqri.whatsub.service.subway;

import com.qqri.whatsub.web.dto.SubwayRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

@Service
public class SubwayService {

    @Transactional
    public Object getItemsFromOpenApi(SubwayRequest request) throws UnsupportedEncodingException {

        String url = "http://openapi.seoul.go.kr:8088/" + request.getKey() + "/xml/SearchFacilityByIDService/1/5/" + request.getStation_cd();  //출력개수/역코드번호
        String decodeServiceKey = URLDecoder.decode(request.getKey(), "UTF-8");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("KEY", decodeServiceKey)
                .queryParam("TYPE", "json")
                .queryParam("SERVICE", "SearchFacilityByIDService")
                .queryParam("START_INDEX", request.getStart_index())
                .queryParam("END_INDEX", request.getEnd_index())
                .queryParam("STATION_CD", request.getStation_cd())

                .build(false);    //자동으로 encoding 되는 것 막는다

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        return response;
    }
}