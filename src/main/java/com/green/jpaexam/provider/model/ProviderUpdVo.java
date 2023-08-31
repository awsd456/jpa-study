package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProviderUpdVo {

    private Long id;
    private String name;
    private LocalDateTime updatedAt;

}
