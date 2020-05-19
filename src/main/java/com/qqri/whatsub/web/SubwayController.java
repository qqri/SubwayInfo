package com.qqri.whatsub.web;

import com.qqri.whatsub.service.subway.SubwayService;
import com.qqri.whatsub.web.dto.SubwayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RestController
public class SubwayController {
    private final SubwayService subwayService;

    @PostMapping("/getData")
    public ResponseEntity<?> getData(@RequestBody SubwayRequest request) throws UnsupportedEncodingException {

        ResponseEntity<String> response = (ResponseEntity<String>) subwayService.getItemsFromOpenApi(request);
        return new ResponseEntity<>(response.getBody() , HttpStatus.OK);
    }
}
