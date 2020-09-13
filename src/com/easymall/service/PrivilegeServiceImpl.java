package com.easymall.service;

import com.easymall.dao.PrivilegeDao;
import com.easymall.domain.Resource;
import com.easymall.domain.Role;
import com.easymall.factory.BasicFactory;

import java.util.List;

public class PrivilegeServiceImpl implements PrivilegeService
{
    PrivilegeDao dao = BasicFactory.getFactory().getInstance(PrivilegeDao.class);

    public boolean needPriv(String path)
    {
        return dao.findResourceByPath(path).size() != 0;
    }

    public boolean hasPriv(int role_id, String path)
    {
        List<Resource> rs = dao.findResourceByPath(path);
        for (Resource r : rs)
        {
            if (r.getRole_id() == role_id)
            {
                return true;
            }
        }
        return false;
    }

    public List<Role> getRoles()
    {
        return dao.getRoles();
    }

    public List<Resource> findResourceByRoleId(int role_id)
    {
        return dao.findResourceByRoleId(role_id);
    }

    public void updatePrivilege(int role_id, String[] paths)
    {
        // 1.删除该角色所有对应路径
        dao.delResourceByRoleId(role_id);
        // 2.重新插入新的路径
        for (String p : paths)
        {
            dao.addresourceByRoleId(role_id, p);
        }
    }
}
