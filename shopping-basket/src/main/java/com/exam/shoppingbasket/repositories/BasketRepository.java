package com.exam.shoppingbasket.repositories;

import com.exam.shoppingbasket.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasketRepository extends JpaRepository<Basket, Long> {


}
