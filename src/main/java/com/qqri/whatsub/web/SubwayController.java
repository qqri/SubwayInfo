package com.qqri.whatsub.web;

import com.qqri.whatsub.service.subway.SubwayService;
import com.qqri.whatsub.web.dto.SubwayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RestController
public class SubwayController {
    private final SubwayService subwayService;

    @PostMapping("/getData")
    @ResponseBody
    public Object getData(@RequestBody SubwayRequest request) throws UnsupportedEncodingException {
        String stationCode = request.getSTATION_CD();
        System.out.println(stationCode);
        Object response = subwayService.getItemsFromOpenApi(stationCode);
        return response;
    }
}
