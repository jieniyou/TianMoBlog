package com.tianmo.bean;

import lombok.Data;

/**
 * 权限表
 */
@Data
public class RoleMenu {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;
}