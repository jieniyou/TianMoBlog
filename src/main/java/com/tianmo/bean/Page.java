package com.tianmo.bean;

import lombok.Data;


@Data
public class Page {

  /**
   * 第几页
   */
  private Integer page;

  /**
   * 查看多少条
   */
  private Integer limit;

  /**
   * 总页数： count/limit = 是不是整数
   */
  private Integer totalPage;

  /**
   * 数据总条数
   */
  private Integer count;


  public Integer getTotalPage(){
    return count % limit == 0 ? count / limit : count / limit + 1;
  }

  public Integer getStartRow(){
    return (page - 1) * limit;
  }

}
