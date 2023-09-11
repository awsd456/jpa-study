package com.green.jpaexam.product;


import com.green.jpaexam.category.CategoryRepository;
import com.green.jpaexam.entity.CategoryEntity;
import com.green.jpaexam.entity.ProductDetailEntity;
import com.green.jpaexam.entity.ProviderEntity;
import com.green.jpaexam.product.model.*;
import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.provider.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao dao;
    private final CategoryRepository categoryRepository;
    private final ProviderRepository providerRepository;
    private final ProductRepository productRep;
    private final ProductQdsl productQdsl;

    public ProductRes saveProduct2(ProductDto dto) {
        CategoryEntity categoryEntity = categoryRepository.findById(dto.getCateId()).get();
        ProviderEntity providerEntity = providerRepository.findById(dto.getProviderId()).get();

        ProductDetailEntity productDetailEntity=ProductDetailEntity.builder()
                .description(dto.getDescription())
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .categoryEntity(CategoryEntity.builder().id(dto.getCateId()).build())
                .providerEntity(ProviderEntity.builder().id(dto.getProviderId()).build())
                .build();

        productEntity.setProductDetailEntity(productDetailEntity);
        productDetailEntity.setProductEntity(productEntity);

        dao.saveProduct(productEntity);
        return null;
    }


    public ProductRes saveProduct(ProductDto dto) {
        CategoryEntity categoryEntity = categoryRepository.findById(dto.getCateId()).get();
        ProviderEntity providerEntity = providerRepository.findById(dto.getProviderId()).get();


        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .categoryEntity(CategoryEntity.builder().id(dto.getCateId()).build())
                .providerEntity(ProviderEntity.builder().id(dto.getProviderId()).build())
                .build();
        ProductRes res = dao.saveProduct(productEntity);


        ProductDetailEntity productDetailEntity=ProductDetailEntity.builder()
                .productEntity(productEntity)
                .description(dto.getDescription())
                .build();
        ProductDetailEntity productDetailEntity1 = dao.saveProductDetail(productDetailEntity);

        return ProductRes.builder()
                .number(res.getNumber())
                .name(res.getName())
                .price(res.getPrice())
                .stock(res.getStock())
                .cateNm(categoryEntity.getName())
                .providerNm(providerEntity.getName())
                .description(productDetailEntity1.getDescription())
                .build();
    }

    public Page<ProductRes> getProductAll(Pageable page) {
        return dao.getProductAll(page);
    }


    public List<ProductRes> getProductAllJpql(Pageable page,ProductSelAllParam param){
        List<ProductRes> list=productRep.selProductAll(page, param);
        return list;
    }


    public List<ProductResQdsl> getProductAllQdsl(Pageable pageable,String search){
       return productQdsl.selProductAll(pageable,search);

    }


    public ProductRes getProduct(Long number) {
       return dao.getProduct(number);
    }

    public ProductRes updProduct(ProductUpdDto dto) {
        ProductEntity entity = ProductEntity.builder()
                .number(dto.getNumber())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        return dao.updProduct(entity);
    }

    public void delProduct(Long number) {
        dao.delProduct(number);
    }
}
