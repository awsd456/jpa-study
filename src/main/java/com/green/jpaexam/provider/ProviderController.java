package com.green.jpaexam.provider;

import com.green.jpaexam.provider.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService service;


    @PostMapping
    public ResponseEntity<ProviderInsVo> insProvider(@RequestBody ProviderInsDto dto){
       ProviderInsVo vo=service.save(dto);
        return ResponseEntity.ok(vo);
    }

    @PutMapping
    public ResponseEntity<ProviderUpdVo> putProvider(@RequestBody ProviderUpdDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping
    public ResponseEntity<Integer> delProvider(@RequestParam Long id){
        service.delete(id);
        return ResponseEntity.ok(1);
    }

    @GetMapping
    public ResponseEntity<List<ProviderSelVo>> selProvider(){
       return ResponseEntity.ok(service.select());
    }
}
