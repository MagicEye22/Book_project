package com.zpp.test;

import com.zpp.pojo.Cart;
import com.zpp.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author : zpp
 * @version : 1.0
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"水浒传",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"水浒传",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);

    }

    @Test
    public void clear() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"水浒传",1,new BigDecimal(100),new BigDecimal(100)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"水浒传",1,new BigDecimal(100),new BigDecimal(100)));
        cart.clear();
        cart.addItem(new CartItem(1,"水浒传",1,new BigDecimal(100),new BigDecimal(100)));
        cart.updateCount(1,10);
        System.out.println(cart);
    }
}