package com.green.jpaexam.product;

import com.green.jpaexam.entity.*;
import com.green.jpaexam.product.model.ProductResQdsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import static com.green.jpaexam.entity.QCategoryEntity.categoryEntity;
import static com.green.jpaexam.entity.QProductDetailEntity.productDetailEntity;
import static com.green.jpaexam.entity.QProductEntity.*;
import static com.green.jpaexam.entity.QProviderEntity.providerEntity;
import static com.green.jpaexam.entity.ProductDetailEntity.*;
import static com.querydsl.core.types.ExpressionUtils.count;
import static com.querydsl.jpa.JPAExpressions.select;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQdsl {

    private final JPAQueryFactory jpaQueryFactory;
    private final QProductEntity p=productEntity;
    private final QCategoryEntity c=categoryEntity;
    private final QProviderEntity pv=providerEntity;
    private final QProductDetailEntity pd= productDetailEntity;
    private final EntityManager em;


    public List<ProductResQdsl> selProductAll(Pageable pageable,String search){


        BooleanBuilder whereBuilder1=new BooleanBuilder();
        if(search!=null){
            whereBuilder1.and(p.name.contains(search)).or(pd.description.contains(search));
        }

        whereBuilder1.and(p.number.goe(JPAExpressions.select(p.number.count()).from(p)));


        JPQLQuery<ProductResQdsl> query =jpaQueryFactory.select(Projections.bean(ProductResQdsl.class,
                        p.number, p.name, p.price, p.stock, productDetailEntity.description,categoryEntity.name.as("cateNm")
                         ,providerEntity.name.as("providerNm"), p.createdAt,ExpressionUtils.as(JPAExpressions.select(count(p)).from(p), "totalCnt")))
               .from(p)
               .join(p.productDetailEntity, productDetailEntity)
               .join(p.categoryEntity, c)
               .join(p.providerEntity, pv)
               .where(whereBuilder1)
               .orderBy(p.number.desc())
               .offset(pageable.getOffset())
               .limit(pageable.getPageSize());

         return query.fetch();
    }





   private OrderSpecifier[] getAllOrderSpecifiers(Pageable pageable){
       List<OrderSpecifier> orders=new LinkedList<>();
       if(!pageable.getSort().isEmpty()){
          for(Sort.Order order:pageable.getSort()){
              Order direction=order.getDirection().isAscending()?Order.ASC:Order.DESC;

              switch (order.getProperty().toLowerCase()){
                  case  "number":orders.add(new OrderSpecifier(direction, p.number));break;
                  case  "product_name": orders.add(new OrderSpecifier(direction, p.name));break;
                  case  "price" : orders.add(new OrderSpecifier(direction,p.price));
                  case  "category_name" : orders.add(new OrderSpecifier(direction,c.name));
              }
          }
       }
       return orders.stream().toArray(OrderSpecifier[]::new);
   }
}
