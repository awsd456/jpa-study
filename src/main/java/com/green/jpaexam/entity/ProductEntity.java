package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@Table(name = "t_product")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

   @ManyToOne
   @JoinColumn(name = "provider_id")
   @ToString.Exclude //toString 찍을때 provider 제외
   private ProviderEntity providerEntity;


   @OneToOne(mappedBy = "productEntity")
   private ProductDetailEntity productDetailEntity;


  @ManyToOne
  @JoinColumn(name = "category_id")
  @ToString.Exclude
  private CategoryEntity categoryEntity;
}

