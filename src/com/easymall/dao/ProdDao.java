package com.easymall.dao;

import com.easymall.domain.ProdFindCond;
import com.easymall.domain.Product;

import java.util.List;

public interface ProdDao extends Dao
{
    /**
     * 增加商品信息
     * @param prod
     */
    void addProd(Product prod);

    /**
     * 查询所有商品信息
     * @return
     */
    List<Product> findAllProds();

    /**
     * 根据id查找商品信息
     * @param id 商品id
     * @return 查找到的商品信息 如果找不到返回null
     */
    Product findProdById(String id);

    /**
     * 设置商品库存数量
     * @param id     商品的id
     * @param newNum 要设置为的数量
     */
    void updatePnum(String id, int newNum);

    /**
     * 查询商品总记录数
     * @return
     */
    long getCount();

    /**
     * 查询指定页的数据
     * @param i
     * @param rowperpage
     * @return
     */
    List<Product> findProdByPage(int begin, int count);

    /**
     * 根据条件查询商品
     * @param pfc   查询条件bean
     * @param count
     * @param begin
     * @return 查到的商品的集合
     */
    List<Product> findProdByCond(ProdFindCond pfc, int begin, int count);

    /**
     * 查询符合条件的记录有多少条
     * @param pfc
     * @return
     */
    long getCountByCond(ProdFindCond pfc);

}
