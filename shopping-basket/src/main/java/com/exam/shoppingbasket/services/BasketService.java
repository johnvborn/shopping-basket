package com.exam.shoppingbasket.services;


import com.exam.shoppingbasket.domain.Basket;
import com.exam.shoppingbasket.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    BasketRepository basketRepository;

    public Optional<Basket> findById(Long id) {
        return basketRepository.findById(id);
    }

    public Basket createBasket(Basket basket) {
        return basketRepository.save(basket);
    }

}
