package com.tianmo.bean;

import lombok.Data;

/**
 * 学员表
 */
@Data
public class Student {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄;0 未知
     */
    private Integer age;

    /**
     * 性别;约定好： 1 男 2女
     */
    private Integer gender;

    /**
     * 手机
     */
    private Long phone;
}