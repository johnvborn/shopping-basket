package com.exam.shoppingbasket.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "basketId")
    private Long basketId;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemAmount")
    private Integer itemAmount;


}
