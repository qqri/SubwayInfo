package com.qqri.whatsub.web;

import com.qqri.whatsub.service.subway.SubFctService;
import com.qqri.whatsub.service.subway.SubwayService;
import com.qqri.whatsub.web.dto.SubFctRequest;
import com.qqri.whatsub.web.dto.SubwayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RestController
public class SubwayController {
    private final SubwayService subwayService;
    private final SubFctService subFctService;

    @PostMapping("/getData")
    public Object getData(@RequestBody SubwayRequest request) throws UnsupportedEncodingException {
        Object response =  subwayService.getItemsFromOpenApi(request);
        return response;
     //   ResponseEntity response = (ResponseEntity<String>) subwayService.getItemsFromOpenApi(request);
     //   return new ResponseEntity<>(response.getBody() , HttpStatus.OK);
    }

    @PostMapping("/searchFacility")
    public ResponseEntity<?> searchFacility(@RequestBody SubFctRequest request) throws UnsupportedEncodingException {

        ResponseEntity response = (ResponseEntity<String>) subFctService.getFctFromOpenApi(request);
        return new ResponseEntity<>(response.getBody() , HttpStatus.OK);
    }

    /*
        @PostMapping("/getData")
    public ResponseEntity<?> getData(@RequestBody SubwayRequest request) throws UnsupportedEncodingException {

        ResponseEntity response = (ResponseEntity<String>) subwayService.getItemsFromOpenApi(request);
        return new ResponseEntity<>(response.getBody() , HttpStatus.OK);
    }

    * */
}
