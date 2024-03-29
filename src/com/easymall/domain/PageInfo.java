package com.easymall.domain;

import java.util.List;

public class PageInfo
{
    private int countrow;
    private int countpage;
    private int firstpage;
    private int prepage;
    private int thispage;
    private int nextpage;
    private int lastpage;
    private int rowperpage;
    private List list;

    private String baseurl;

    public String getBaseurl()
    {
        return baseurl;
    }

    public void setBaseurl(String baseurl)
    {
        this.baseurl = baseurl;
    }

    public int getCountrow()
    {
        return countrow;
    }

    public void setCountrow(int countrow)
    {
        this.countrow = countrow;
    }

    public int getCountpage()
    {
        return countpage;
    }

    public void setCountpage(int countpage)
    {
        this.countpage = countpage;
    }

    public int getFirstpage()
    {
        return firstpage;
    }

    public void setFirstpage(int firstpage)
    {
        this.firstpage = firstpage;
    }

    public int getPrepage()
    {
        return prepage;
    }

    public void setPrepage(int prepage)
    {
        this.prepage = prepage;
    }

    public int getThispage()
    {
        return thispage;
    }

    public void setThispage(int thispage)
    {
        this.thispage = thispage;
    }

    public int getNextpage()
    {
        return nextpage;
    }

    public void setNextpage(int nextpage)
    {
        this.nextpage = nextpage;
    }

    public int getLastpage()
    {
        return lastpage;
    }

    public void setLastpage(int lastpage)
    {
        this.lastpage = lastpage;
    }

    public int getRowperpage()
    {
        return rowperpage;
    }

    public void setRowperpage(int rowperpage)
    {
        this.rowperpage = rowperpage;
    }

    public List getList()
    {
        return list;
    }

    public void setList(List list)
    {
        this.list = list;
    }
}
