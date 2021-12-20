package com.tianmo.bean;

import com.tianmo.annotation.FieldName;
import lombok.Data;

/**
 * 用户表
 */
@Data
public class User {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;
    /**
     * 角色ID
     */
    @FieldName("role_id")
    private Integer roleId;
}