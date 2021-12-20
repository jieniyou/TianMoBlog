package com.tianmo.bean;

import lombok.Data;

/**
 * 菜单表
 */
@Data
public class Menu {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父级菜单的ID;0代表一级菜单
     */
    private Integer parentId;
}