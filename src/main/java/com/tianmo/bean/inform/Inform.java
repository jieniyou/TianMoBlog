package com.tianmo.bean.inform;

import com.tianmo.annotation.FieldName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * inform
 * @author 
 */
@Data
public class Inform implements Serializable {
    private Integer id;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容
     */
    private String inform;

    /**
     * 创建时间
     */
    @FieldName("create_time")
    private Date createTime;

    /**
     * 状态
     */
    private String status;


    private static final long serialVersionUID = 1L;
}