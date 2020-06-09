package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "store")
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "store_name")
    private String storeName;

    @OneToOne(mappedBy = "store")
    private Seller seller;

    @OneToMany(mappedBy = "store")
    private List<Product> products;

}
