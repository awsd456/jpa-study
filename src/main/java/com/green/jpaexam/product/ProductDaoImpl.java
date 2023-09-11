package com.green.jpaexam.product;


import com.green.jpaexam.category.CategoryRepository;
import com.green.jpaexam.entity.ProductDetailEntity;
import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.provider.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;
    private final ProductDetailRepository detailRepository;


    @Override
    public ProductRes saveProduct(ProductEntity p) {
        ProductEntity result = productRepository.save(p);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
    }

    @Override
    public ProductDetailEntity saveProductDetail(ProductDetailEntity p) {
        return detailRepository.save(p);

    }


    @Override
    public Page<ProductRes> getProductAll(Pageable page) {
        Page<ProductEntity> totalList = productRepository.findAll(page);
        long totalSize = totalList.getTotalElements();
        List<ProductRes> contents = totalList.getContent().stream().map(item ->
                ProductRes.builder()
                        .number(item.getNumber())
                        .name(item.getName())
                        .price(item.getPrice())
                        .stock(item.getStock())
                        .build()
        ).toList();
        return new PageImpl<>(contents, page, totalSize);
    }

    @Override
    public ProductRes getProduct(Long number) {
        Optional<ProductEntity> opt = productRepository.findById(number);
        if(!opt.isPresent()) {
            return null;
        }
        ProductEntity entity = opt.get();
        return ProductRes.builder()
                .number(entity.getNumber())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }

    @Override
    public ProductRes updProduct(ProductEntity p) {
        Optional<ProductEntity> opt = productRepository.findById(p.getNumber());
        if(!opt.isPresent()) {
            return null;
        }
        ProductEntity entity = opt.get();
        entity.setName(p.getName());
        entity.setPrice(p.getPrice());
        entity.setStock(p.getStock());

        ProductEntity result = productRepository.save(entity);
        return ProductRes.builder()
                .number(result.getNumber())
                .name(result.getName())
                .price(result.getPrice())
                .stock(result.getStock())
                .build();
    }

    @Override
    public void delProduct(Long number) {
        productRepository.deleteById(number);
    }
}
