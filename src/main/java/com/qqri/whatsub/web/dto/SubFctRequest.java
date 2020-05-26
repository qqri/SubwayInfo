package com.qqri.whatsub.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubFctRequest {
    String key;
    String type;
    String service;
    int start_index;
    int end_index;
    String fr_nm;

    @Builder
    public SubFctRequest(String key, String type, String service, int start_index, int end_index, String fr_nm) {
        this.key = key;
        this.type = type;
        this.service = service;
        this.start_index = start_index;
        this.end_index = end_index;
        this.fr_nm = fr_nm;
    }
}
