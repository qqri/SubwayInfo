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
    String station_nm;

    @Builder
    public SubwayRequest(String key, String type, String service, int start_index, int end_index, String station_nm) {
        this.key = key;
        this.type = type;
        this.service = service;
        this.start_index = start_index;
        this.end_index = end_index;
        this.station_nm = station_nm;
    }
}
