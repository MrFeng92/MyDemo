package com.easymall.service;

import com.easymall.annotation.Trans;
import com.easymall.domain.Order;
import com.easymall.domain.OrderInfo;
import com.easymall.domain.OrderItem;
import com.easymall.domain.SaleInfo;

import java.util.List;

public interface OrderService extends Service
{
    /**
     * 增加订单
     * @param order 订单信息bean
     * @param list  当前订单的订单项组成的集合
     */
    @Trans
    void addOrder(Order order, List<OrderItem> list);

    /**
     * 查询指定用户的所有订单信息
     * @param user_id 要查询的用户id
     * @return 查找到的该用户的所有订单信息组成的集合
     */
    List<OrderInfo> findOrderInfoByUserId(int id);

    /**
     * 根据订单Id删除订单,需要添加数据库事务管理，因为删除订单设计多表操作，不添加事务容易造成数据紊乱
     * @param orderId
     */
    @Trans
    void delOrder(String orderId);

    /**
     * 查询销售榜单
     * @return
     */
    List<SaleInfo> saleList();

    /**
     * 根据id查询订单
     * @param orderid
     * @return
     */
    Order findOrderById(String orderid);

    /**
     * 修改支付状态
     * @param r6_Order
     * @param i
     */
    void updatePayState(String id, int state);
}
