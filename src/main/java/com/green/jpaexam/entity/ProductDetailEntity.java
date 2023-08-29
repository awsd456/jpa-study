package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "t_product_detail")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetailEntity extends BaseEntity {

     @Id
     private Long number;


     @MapsId
     @OneToOne
     @JoinColumn(name = "product_number",columnDefinition = "BIGINT UNSIGNED")
     private ProductEntity productEntity;



    private String description;


}
