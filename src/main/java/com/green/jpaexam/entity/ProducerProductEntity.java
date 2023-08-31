package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;



@Entity
@Table(name = "t_producer_product")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@ToString(callSuper = true)
public class ProducerProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "i_producer",columnDefinition = "BIGINT UNSIGNED")
    @ToString.Exclude
    private ProducerEntity producerEntity;

    @ManyToOne
    @JoinColumn(name = "i_product",columnDefinition = "BIGINT UNSIGNED")
    @ToString.Exclude
    private ProductEntity productEntity;

}
