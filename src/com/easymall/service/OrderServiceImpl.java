package com.easymall.service;

import com.easymall.dao.OrderDao;
import com.easymall.dao.ProdDao;
import com.easymall.dao.UserDao;
import com.easymall.domain.*;
import com.easymall.factory.BasicFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService
{
    OrderDao order_dao = BasicFactory.getFactory().getInstance(OrderDao.class);
    ProdDao prod_dao = BasicFactory.getFactory().getInstance(ProdDao.class);
    UserDao userDao = BasicFactory.getFactory().getInstance(UserDao.class);

    public void addOrder(Order order, List<OrderItem> list)
    {
        //1.增加订单信息
        order_dao.addOrder(order);
        //2.检查商品的库存是否充足
        for (OrderItem item : list)
        {
            String prod_id = item.getProduct_id();
            Product prod = prod_dao.findProdById(prod_id);
            if (prod.getPnum() >= item.getBuynum())
            {
                //3.如果充足扣除商品库存数量
                prod_dao.updatePnum(prod.getId(), prod.getPnum() - item.getBuynum());

                //TODO 事务控制	int i = 1/0;

                //4.增加订单项的信息
                order_dao.addOrderItem(item);
            } else
            {
                throw new RuntimeException("商品" + prod.getName() + "库存不足！");
            }
        }
    }

    public List<OrderInfo> findOrderInfoByUserId(int user_id)
    {
        User user = userDao.findUserByUserId(user_id);// 根据登录的UserId查询User信息

        List<Order> order_list = order_dao.findOrderByUserId(user.getId());// 根据userId查询订单集合
        List<OrderInfo> orderInfo_list = new ArrayList<OrderInfo>();

        for (Order order : order_list)
        {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUser(user);
            orderInfo.setOrder(order);

            Map<Product, Integer> map = new LinkedHashMap<Product, Integer>();
            List<OrderItem> orderItem_list = order_dao.findOrderItemByOrderId(order.getId());
            for (OrderItem orderItem : orderItem_list)
            {
                Product prod = prod_dao.findProdById(orderItem.getProduct_id());// 根据商品Id查询商品信息
                map.put(prod, orderItem.getBuynum());
            }
            orderInfo.setMap(map);// 封装到orderInfo对象中
            orderInfo_list.add(orderInfo);// 添加到OrderInfo集合中

        }

        return orderInfo_list;
    }

    @Override
    public void delOrder(String orderId)
    {
        // 根据订单id查询所有订单详情
        List<OrderItem> orderItem_list = order_dao.findOrderItemByOrderId(orderId);
        // 遍历订单详情，把所有商品库存加上去
        for (OrderItem orderItem : orderItem_list)
        {
            Product prod = prod_dao.findProdById(orderItem.getProduct_id());// 根据商品Id查库存商品信息
            prod_dao.updatePnum(prod.getId(), prod.getPnum() + orderItem.getBuynum());
        }
        // 删除订单项
        order_dao.delOrderItemByOrderId(orderId);
        // 删除订单
        order_dao.delOrderById(orderId);
    }

    @Override
    public List<SaleInfo> saleList()
    {
        return order_dao.saleList();
    }

    @Override
    public Order findOrderById(String orderid)
    {
        return order_dao.findOrderById(orderid);
    }

    @Override
    public void updatePayState(String id, int state)
    {
        order_dao.updatePayState(id, state);
    }
}
