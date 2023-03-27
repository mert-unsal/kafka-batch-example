package com.munsal.kafkabatchexample.domain.event;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductEvent {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
}
