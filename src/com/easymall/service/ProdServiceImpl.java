package com.easymall.service;

import com.easymall.dao.ProdDao;
import com.easymall.domain.PageInfo;
import com.easymall.domain.ProdFindCond;
import com.easymall.domain.Product;
import com.easymall.factory.BasicFactory;

import java.util.List;

public class ProdServiceImpl implements ProdService
{
    ProdDao dao = BasicFactory.getFactory().getInstance(ProdDao.class);

    public void addProd(Product prod)
    {
        dao.addProd(prod);
    }

    public List<Product> findAllProds()
    {
        return dao.findAllProds();
    }

    public Product findProdById(String id)
    {
        return dao.findProdById(id);
    }

    public void updatePnum(String id, int newNum)
    {
        dao.updatePnum(id, newNum);
    }

    public PageInfo findProdByPage(int thispage, int rowperpage)
    {
        PageInfo pi = new PageInfo();
        //--总记录数
        int countrow = (int) dao.getCount();
        pi.setCountrow(countrow);
        //--总页数
        int countpage = (countrow / rowperpage) + (countrow % rowperpage == 0 ? 0 : 1);
        pi.setCountpage(countpage);
        //--首页
        int firstpage = 1;
        pi.setFirstpage(firstpage);
        //--上一页
        int prepage = thispage == 1 ? 1 : thispage - 1;
        pi.setPrepage(prepage);
        //--当前页
        pi.setThispage(thispage);
        //--下一页
        int nextpage = thispage == countpage ? countpage : thispage + 1;
        pi.setNextpage(nextpage);
        //--尾页
        int lastpage = countpage;
        pi.setLastpage(countpage);
        //--每页记录数
        pi.setRowperpage(rowperpage);
        //--当前页的数据
        List<Product> list = dao.findProdByPage((thispage - 1) * rowperpage, rowperpage);
        pi.setList(list);

        return pi;
    }

    public PageInfo findProdByCond(ProdFindCond pfc, int thispage, int rowperpage)
    {
        PageInfo pi = new PageInfo();
        //--总记录数
        int countrow = (int) dao.getCountByCond(pfc);
        pi.setCountrow(countrow);
        //--总页数
        int countpage = (countrow / rowperpage) + (countrow % rowperpage == 0 ? 0 : 1);
        pi.setCountpage(countpage);
        //--首页
        int firstpage = 1;
        pi.setFirstpage(firstpage);
        //--上一页
        int prepage = thispage == 1 ? 1 : thispage - 1;
        pi.setPrepage(prepage);
        //--当前页
        pi.setThispage(thispage);
        //--下一页
        int nextpage = thispage == countpage ? countpage : thispage + 1;
        pi.setNextpage(nextpage);
        //--尾页
        int lastpage = countpage;
        pi.setLastpage(countpage);
        //--每页记录数
        pi.setRowperpage(rowperpage);
        //--当前页的数据
        List list = dao.findProdByCond(pfc, (thispage - 1) * rowperpage, rowperpage);
        pi.setList(list);

        return pi;
    }
}
