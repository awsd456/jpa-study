package com.green.jpaexam.provider;

import com.green.jpaexam.entity.ProviderEntity;
import com.green.jpaexam.provider.model.*;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository rep;


    public ProviderInsVo save(ProviderInsDto dto){
        ProviderEntity entity=ProviderEntity.builder()
                .name(dto.getName())
                .build();
        rep.save(entity);

       return  ProviderInsVo.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .name(entity.getName())
                .build();
    }


    public ProviderUpdVo update(ProviderUpdDto dto){

        Optional<ProviderEntity> optEntity = rep.findById(dto.getId());//영속성에서 가져와야 한다
        ProviderEntity entity = optEntity.get();

        entity.setName(dto.getName());

        rep.save(entity); //비영속은 INSERT , 영속은 UPDATE

        return ProviderUpdVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void delete(Long id) {

        rep.deleteById(id);
    }


    public List<ProviderSelVo> select(){
        List<ProviderEntity> findList = rep.findAll(Sort.by(Sort.Order.asc("name")));


       return findList.stream().map(
                item->ProviderSelVo.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .build()
        ).toList();
//        List<ProviderSelVo> list=new ArrayList<>();
//        for (int i = 0; i < findList.size(); i++) {
//            ProviderSelVo vo=ProviderSelVo.builder()
//                    .id(findList.get(i).getId())
//                    .name(findList.get(i).getName())
//                    .build();
//            list.add(vo);
//        }



    }
}
