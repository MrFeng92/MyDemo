package com.easymall.service;

import com.easymall.domain.PageInfo;
import com.easymall.domain.ProdFindCond;
import com.easymall.domain.Product;

import java.util.List;

public interface ProdService extends Service
{
    /**
     * 添加商品
     * @param prod
     */
    public void addProd(Product prod);

    /**
     * 查询 所有商品信息
     * @return 所有商品信息组成的集合
     */
    public List<Product> findAllProds();

    /**
     * 根据id查找商品
     * @param id 要查找的商品的id
     * @return 找到的商品信息bean，如果找不到返回null
     */
    public Product findProdById(String id);

    /**
     * 修改商品库存数量
     * @param id
     * @param newNum
     */
    public void updatePnum(String id, int newNum);

    /**
     * 分页查询商品信息
     * @param thispage   当前页码
     * @param rowperpage 每页记录数
     * @return 封装了分页信息和当前页数据的bean
     */
    public PageInfo findProdByPage(int thispage, int rowperpage);

    /**
     * 根据条件查询商品
     * @param pfc        查询的条件bean
     * @param rowperpage
     * @param thispage
     * @return
     */
    public PageInfo findProdByCond(ProdFindCond pfc, int thispage, int rowperpage);
}
