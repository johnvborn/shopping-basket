package com.exam.shoppingbasket.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Basket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "basketName")
    private String basketName;

    @Column(name = "totalAmount")
    private Integer totalAmount;

    @Column(name = "totalItems")
    private Integer totalItems;




}


