package com.qqri.whatsub.web.dto;

import lombok.Getter;

@Getter
public class SubwayResponseDto {
    String KEY;
    String TYPE;
    String SERVICE;
    int START_INDEX;
    int END_INDEX;
    String STATION_CD;

//    public SubwayResponseDto(ResponseEntity<String> response){
//        this.KEY = response.;
//    }

}
