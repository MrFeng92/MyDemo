package com.easymall.dao;

import com.easymall.domain.Order;
import com.easymall.domain.OrderItem;
import com.easymall.domain.SaleInfo;
import com.easymall.utils.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao
{
    public void addOrder(Order order)
    {
        String sql = "insert into orders values (?,?,?,?,null,?)";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, order.getId(), order.getMoney(), order.getReceiverinfo(), order.getPaystate(), order.getUser_id());
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void addOrderItem(OrderItem item)
    {
        String sql = "insert into orderitem values (?,?,?)";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, item.getOrder_id(), item.getProduct_id(), item.getBuynum());
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Order> findOrderByUserId(int user_id)
    {
        try
        {
            String sql = "select * from orders where user_id=?";
            QueryRunner runner = new QueryRunner();
            List<Order> order_list = runner.query(TransactionManager.getConn(), sql, new BeanListHandler<Order>(Order.class), user_id);
            return order_list;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<OrderItem> findOrderItemByOrderId(String order_id)
    {
        try
        {
            String sql = "select * from orderitem where order_id=?";
            QueryRunner runner = new QueryRunner();
            List<OrderItem> orderItem_list = runner.query(TransactionManager.getConn(), sql, new BeanListHandler<OrderItem>(OrderItem.class), order_id);
            return orderItem_list;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void delOrderItemByOrderId(String orderId)
    {
        try
        {
            String sql = "delete  from orderitem where order_id=?";
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, orderId);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void delOrderById(String orderId)
    {
        try
        {
            String sql = "delete  from orders where id=?";
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, orderId);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public List<SaleInfo> saleList()
    {
        String sql = "select products.id prod_id,products.name prod_name,sum(orderitem.buynum) sale_num "
                + "from orders,products,orderitem "
                + "where  orders.id = orderitem.order_id and products.id = orderitem.product_id "
                + "and orders.paystate=1 " + "group by  products.id " + "order by sale_num desc";
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanListHandler<SaleInfo>(SaleInfo.class));
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Order findOrderById(String orderid)
    {
        try
        {
            String sql = "select *  from orders where id=?";
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanHandler<Order>(Order.class), orderid);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updatePayState(String id, int state)
    {
        String sql = "update orders set paystate = ? where id = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, state, id);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}