package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column
    private int price;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Customer customer;

}
