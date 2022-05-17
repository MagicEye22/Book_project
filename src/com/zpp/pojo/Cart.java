package com.zpp.pojo;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zpp
 * @version : 1.0
 */
public class Cart {
//    private  Integer totalCount;
//    private BigDecimal totalPrice;
    //key是商品编号，value是商品信息
    private Map<Integer,CartItem> items=new LinkedHashMap<>();


    /**
     * 添加商品项
     * @param cartItem
     */
    public void  addItem(CartItem cartItem){
        //先查看购物车当中是否已添加商品: 已添加，则数量累加，总金额更新， 未添加过，直接放到集合中
        CartItem item = items.get(cartItem.getId());

        if (item==null){
            //未添加过
            items.put(cartItem.getId(),cartItem);
        }else {
            //已添加过
            item.setCount(item.getCount()+1);//数量累加
            //multiply()方法 是bigDecimal中的乘法方法
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }
    }

    /**
     *  删除商品项
     * @param id
     */
    public void  deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public  void  clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否有此商品，如果有，修改商品数量，更新总金额
        CartItem item = items.get(id);

        if (item!=null){
            item.setCount(count);//修改商品数量
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }

    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice()+
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
       Integer totalCount=0;
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }



    public BigDecimal getTotalPrice() {
       BigDecimal totalPrice=new BigDecimal(0);
       //遍历方式？
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            //getValue() ??? add()??
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
