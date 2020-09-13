package com.easymall.dao;

import com.easymall.domain.ProdFindCond;
import com.easymall.domain.Product;
import com.easymall.utils.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdDaoImpl implements ProdDao
{
    public void addProd(Product prod)
    {
        String sql = "insert into products values (?,?,?,?,?,?,?)";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, prod.getId(), prod.getName(), prod.getPrice(), prod.getCategory(), prod.getPnum(), prod.getImgurl(), prod.getDescription());
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAllProds()
    {
        String sql = "select * from products";
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanListHandler<Product>(Product.class));
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product findProdById(String id)
    {
        String sql = "select * from products where id = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanHandler<Product>(Product.class), id);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updatePnum(String id, int newNum)
    {
        String sql = "update products set pnum=? where id = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, newNum, id);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public long getCount()
    {
        String sql = "select count(*) from products";
        try
        {
            QueryRunner runner = new QueryRunner();
            return (Long) runner.query(TransactionManager.getConn(), sql, new ScalarHandler(1));
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> findProdByPage(int begin, int count)
    {
        String sql = "select * from products limit ?,?";
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanListHandler<Product>(Product.class), begin, count);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> findProdByCond(ProdFindCond pfc, int begin, int count)
    {
        String sql = "select * from products where 1=1 ";
        List list = new ArrayList();
        if (pfc.getName() != null && !"".equals(pfc.getName()))
        {
            sql = sql + " and name like ?";
            list.add("%" + pfc.getName() + "%");
        }
        if (pfc.getCategory() != null && !"".equals(pfc.getCategory()))
        {
            sql = sql + " and category = ?";
            list.add(pfc.getCategory());
        }
        if (pfc.getMinprice() != 0)
        {
            sql = sql + " and price > ?";
            list.add(pfc.getMinprice());
        }
        if (pfc.getMaxprice() != 0)
        {
            sql = sql + " and price < ?";
            list.add(pfc.getMaxprice());
        }
        sql = sql + "limit ?,?";
        list.add(begin);
        list.add(count);
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanListHandler<Product>(Product.class), list.toArray());
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public long getCountByCond(ProdFindCond pfc)
    {
        String sql = "select count(*) from products where 1=1 ";
        List list = new ArrayList();
        if (pfc.getName() != null && !"".equals(pfc.getName()))
        {
            sql = sql + " and name like ?";
            list.add("%" + pfc.getName() + "%");
        }
        if (pfc.getCategory() != null && !"".equals(pfc.getCategory()))
        {
            sql = sql + " and category = ?";
            list.add(pfc.getCategory());
        }
        if (pfc.getMinprice() != 0)
        {
            sql = sql + " and price > ?";
            list.add(pfc.getMinprice());
        }
        if (pfc.getMaxprice() != 0)
        {
            sql = sql + " and price < ?";
            list.add(pfc.getMaxprice());
        }
        try
        {
            QueryRunner runner = new QueryRunner();
            return (Long) runner.query(TransactionManager.getConn(), sql, new ScalarHandler(1), list.toArray());
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
