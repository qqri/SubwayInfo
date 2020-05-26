package com.qqri.whatsub.service.subway;

import com.qqri.whatsub.web.dto.SubFctRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

@Service
public class SubFctService {
    public Object getFctFromOpenApi(SubFctRequest request) throws UnsupportedEncodingException {

        String serviceKey = request.getKey();
        String url = "http://openAPI.seoul.go.kr:8088/"+ serviceKey + "/xml/SearchFacilityByFRCodeService/1/5/"
                + request.getFr_nm() + "/";
        String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));    //Response Header to UTF-8

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("KEY", decodeServiceKey)
                .queryParam("TYPE", request.getType())
                .queryParam("SERVICE", "SearchFacilityByFRCodeService")
                .queryParam("START_INDEX", request.getStart_index())
                .queryParam("END_INDEX", request.getEnd_index())
                .queryParam("STATION_CD", request.getFr_nm())
                .build(false);    //자동으로 encode해주는 것을 막기 위해 false

        Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        return response;

    }

}
