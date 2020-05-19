package com.qqri.whatsub.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class testDto {
    String key;
    int id;

    @Builder
    public testDto(String key, int id){
        this.key = key;
        this.id = id;
    }
}
