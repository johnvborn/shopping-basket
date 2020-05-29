package com.exam.shoppingbasket.controllers;



import com.exam.shoppingbasket.domain.Basket;
import com.exam.shoppingbasket.domain.Item;
import com.exam.shoppingbasket.services.BasketService;
import com.exam.shoppingbasket.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(BasketController.BASE_URL)
public class BasketController {

    public static final String BASE_URL = "/api/basket";


    @Autowired
    BasketService basketService;

    @Autowired
    ItemService itemService;

    @GetMapping("/items/{id}")
    public ResponseEntity<List<Item>> getAllItemsByBasketId(@PathVariable Long id) {

        List<Item> itemList = itemService.findAllByBasketId(id);

        if ( itemList.isEmpty() ) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(itemList,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("")
    public ResponseEntity<Basket> createBasket(@RequestBody Basket basket) {

        try {
            Basket _basket = basketService.createBasket(basket);
            return  new ResponseEntity<>(_basket, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable("id") Long id) {
        try {
            Optional<Basket> basket = basketService.findById(id);
            if(basket.isPresent()) {
                return new ResponseEntity<>(basket.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


    @GetMapping("/total/{id}")
    public ResponseEntity<Basket> totalAmountByBasketId(@PathVariable Long id) {
        try {
            Basket basket = itemService.totalAmountByBasketId(id);
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {
        ResponseEntity<Item> responseEntity = null;

        Optional<Item> item = itemService.findById(id);

        if ( item.isPresent() ) {
            responseEntity = new ResponseEntity<>(item.get(), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("/item")
    public ResponseEntity<Item> createBasketItem(@RequestBody Item item) {
        try {

            Optional<Basket> basket = basketService.findById(item.getBasketId());

            if(basket.isPresent()) {
                Item _item = itemService.create(item);
                return new ResponseEntity<>(_item, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<Item> updateBasketItem(@RequestBody Item item) {
        try {

            Optional<Item> _item = itemService.update(item);

            if ( _item.isPresent() ) {
                return new ResponseEntity<>(_item.get(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/item/{id}")
    //@RequestMapping(value = "/item/{id}", method = { RequestMethod.DELETE, RequestMethod.GET})
    public ResponseEntity<Item> deleteBasketItem(@PathVariable Long id) {
        try {
            itemService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }




}
