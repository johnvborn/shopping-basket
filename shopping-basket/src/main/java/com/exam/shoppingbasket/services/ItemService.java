package com.exam.shoppingbasket.services;


import com.exam.shoppingbasket.domain.Basket;
import com.exam.shoppingbasket.domain.Item;
import com.exam.shoppingbasket.repositories.BasketRepository;
import com.exam.shoppingbasket.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BasketRepository basketRepository;

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findAllByBasketId(Long id) {
        return itemRepository.findAllByBasketId(id);
    }

    public Item create(Item item) {
       Item _item = itemRepository.save(item);
       return _item;
    }

    public Optional<Item> update(Item item) {
        Optional<Item> _item = itemRepository.findById(item.getId());

        if (_item.isPresent()) {
            Item itemData = _item.get();
            itemData.setBasketId(item.getBasketId());
            itemData.setItemAmount(item.getItemAmount());
            itemData.setItemName(item.getItemName());

            itemRepository.save(itemData);
        }

        return _item;

    }

    public void delete(Long id) throws Exception {
       try {
           itemRepository.deleteById(id);
       } catch( Exception e ) {
         throw new Exception(e.getMessage());
       }
    }

    public Basket totalAmountByBasketId(Long id) {

        ItemRepository.BookProjection result = itemRepository.totalAmountByBasketId(id);

        Basket basket = new Basket();
        basket.setId(result.getId());
        basket.setBasketName(result.getBasketName());
        basket.setTotalAmount(result.getTotalAmount());
        basket.setTotalItems(result.getTotalItems());

        basketRepository.save(basket);

        return basket;
    }







}
