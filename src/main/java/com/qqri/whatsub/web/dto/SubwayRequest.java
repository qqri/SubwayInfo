package com.qqri.whatsub.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubwayRequest {
    String KEY;
    String TYPE;
    String SERVICE;
    int START_INDEX;
    int END_INDEX;
    String STATION_CD;

    @Builder
    public SubwayRequest(String KEY, String TYPE, String SERVICE, int START_INDEX, int END_INDEX, String STATION_CD) {
        this.KEY = KEY;
        this.TYPE = TYPE;
        this.SERVICE = SERVICE;
        this.START_INDEX = START_INDEX;
        this.END_INDEX = END_INDEX;
        this.STATION_CD = STATION_CD;
    }
}
