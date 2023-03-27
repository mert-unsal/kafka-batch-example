package com.munsal.kafkabatchexample.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "product")
@SequenceGenerator(name = "product_seq_gen", allocationSize = 1, sequenceName = "product_seq")
public class ProductEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name", unique = true)
    private String name;

    @Min(message = "price cannot be less than 0.04", value = 1)
    private Double price;
    private Integer quantity;
}
