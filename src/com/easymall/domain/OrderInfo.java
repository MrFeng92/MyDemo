package com.easymall.domain;

import java.io.Serializable;
import java.util.Map;

public class OrderInfo implements Serializable
{
    private User user;
    private Order order;
    private Map<Product, Integer> map;// 商品和商品购买数量

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Map<Product, Integer> getMap()
    {
        return map;
    }

    public void setMap(Map<Product, Integer> map)
    {
        this.map = map;
    }
}
