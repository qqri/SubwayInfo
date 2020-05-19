package com.qqri.whatsub.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubwayRequest {
    String key;
    String type;
    String service;
    int start_index;
    int end_index;
    String station_cd;

    @Builder
    public SubwayRequest(String key, String type, String service, int start_index, int end_index, String station_cd) {
        this.key = key;
        this.type = type;
        this.service = service;
        this.start_index = start_index;
        this.end_index = end_index;
        this.station_cd = station_cd;
    }
}
