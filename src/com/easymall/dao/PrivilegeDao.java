package com.easymall.dao;

import com.easymall.domain.Resource;
import com.easymall.domain.Role;

import java.util.List;

public interface PrivilegeDao extends Dao
{
    /**
     * 根据路径查询资源信息
     * @param path
     * @return
     */
    List<Resource> findResourceByPath(String path);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> getRoles();

    /**
     * 根据角色查询资源
     * @param role_id
     * @return
     */
    List<Resource> findResourceByRoleId(int role_id);

    /**
     * 删除指定角色对应的所有路径
     * @param role_id
     */
    void delResourceByRoleId(int role_id);

    /**
     * 新增角色对应路径
     * @param role_id
     * @param p
     */
    void addresourceByRoleId(int role_id, String p);
}
