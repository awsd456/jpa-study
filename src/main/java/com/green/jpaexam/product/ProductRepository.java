package com.green.jpaexam.product;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductSelAllParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    @Query(" select new com.green.jpaexam.product.model.ProductRes(p.number,p.name,p.price,p.stock,d.description,c.name,v.name,p.createdAt) " +
            " from ProductEntity p " +
            " join p.productDetailEntity d " +
            " join p.categoryEntity c " +
            " join p.providerEntity v" +
            " where p.name =:#{#param.productName} and p.price>=:#{#param.price} ")
    List<ProductRes> selProductAll(Pageable pageable,@Param("param") ProductSelAllParam param);

}



