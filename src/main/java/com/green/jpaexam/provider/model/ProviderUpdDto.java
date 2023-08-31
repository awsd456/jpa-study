package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ProviderUpdDto {

    private Long id;
    private String name;

}
