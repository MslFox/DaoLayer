package com.mslfox.daolayer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String date;
    @ManyToOne(optional = false)
    @JoinColumn
    private Customer customer;
    @Column(name = "product_name")
    private String productName;
    @Column
    private int amount;
}
