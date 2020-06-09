package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "seller")
@Getter
@Setter
public class Seller {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "seller_name")
    private String sellerName;

    @Column
    private int age;

    @OneToOne
    private Store store;

}