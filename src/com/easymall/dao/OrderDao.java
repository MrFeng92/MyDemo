package com.easymall.dao;

import com.easymall.domain.Order;
import com.easymall.domain.OrderItem;
import com.easymall.domain.SaleInfo;

import java.util.List;

public interface OrderDao extends Dao
{
    /**
     * 增加订单信息
     * @param order
     */
    void addOrder(Order order);

    /**
     * 增加订单项
     * @param item
     */
    void addOrderItem(OrderItem item);

    /**
     * 根据UserId查询订单
     * @param id UserId
     * @return 订单集合
     */
    List<Order> findOrderByUserId(int user_id);

    /**
     * 根据OrderId查询订单详情
     * @param id OrderId
     * @return 订单详情集合
     */
    List<OrderItem> findOrderItemByOrderId(String order_id);

    /**
     * 根据订单id删除所有订单项
     * @param orderId 要删除的订单项ID
     */
    void delOrderItemByOrderId(String orderId);

    /**
     * 删除指定订单
     * @param orderId
     */
    void delOrderById(String orderId);

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
     * 修改订单支付状态
     * @param id
     * @param state
     */
    void updatePayState(String id, int state);
}
