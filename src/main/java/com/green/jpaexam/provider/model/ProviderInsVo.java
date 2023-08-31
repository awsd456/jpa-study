package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProviderInsVo {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
