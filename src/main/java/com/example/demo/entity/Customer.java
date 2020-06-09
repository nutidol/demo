package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(mappedBy = "customer")
    private List<Product> products;
}
