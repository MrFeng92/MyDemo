package com.easymall.domain;

import java.io.Serializable;

public class ProdFindCond implements Serializable
{
    private String name;
    private String category;
    private double minprice;
    private double maxprice;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public double getMinprice()
    {
        return minprice;
    }

    public void setMinprice(double minprice)
    {
        this.minprice = minprice;
    }

    public double getMaxprice()
    {
        return maxprice;
    }

    public void setMaxprice(double maxprice)
    {
        this.maxprice = maxprice;
    }

    @Override
    public String toString()
    {
        String str = "";
        if (name != null && !"".equals(name))
        {
            str = str + "&name=" + name;
        }
        if (category != null && !"".equals(category))
        {
            str = str + "&category=" + category;
        }
        if (minprice != 0)
        {
            str = str + "&minprice=" + minprice;
        }
        if (maxprice != 0)
        {
            str = str + "&maxprice=" + maxprice;
        }

        return str;
    }
}
