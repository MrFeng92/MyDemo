package com.easymall.service;

import com.easymall.annotation.Trans;
import com.easymall.domain.Resource;
import com.easymall.domain.Role;

import java.util.List;

public interface PrivilegeService extends Service
{
    /**
     * 当前资源是否需要权限
     * @param path 要检查的资源路径
     * @return 布尔类型，true需要权限，false表示不需要权限
     */
    boolean needPriv(String path);

    /**
     * 检查当前角色 是否 可以访问该资源
     * @param role_id 要检查的角色id
     * @param path    要检查的资源的路径
     * @return
     */
    boolean hasPriv(int role_id, String path);

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
     * 修改角色对应的路径
     * @param role_id
     * @param paths
     */
    @Trans
    void updatePrivilege(int role_id, String[] paths);
}
