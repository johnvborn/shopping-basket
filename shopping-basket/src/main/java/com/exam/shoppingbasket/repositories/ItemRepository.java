package com.exam.shoppingbasket.repositories;

import com.exam.shoppingbasket.domain.Basket;
import com.exam.shoppingbasket.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByBasketId(Long id);

    @Query(value = "select SUM(i.itemAmount) as totalAmount, COUNT(i.id) as totalItems, b.basketName as " +
            " basketName, b.id as id from Item i LEFT JOIN Basket b ON b.id = i.basketId WHERE i.basketId = ?1")
    BookProjection totalAmountByBasketId(Long id);

    interface BookProjection {
        Long getId();
        String getBasketName();
        Integer getTotalAmount();
        Integer getTotalItems();
    }


}


