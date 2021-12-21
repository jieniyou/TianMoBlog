package com.tianmo.service.impl;

import com.tianmo.bean.User;
import com.tianmo.bean.inform.Inform;
import com.tianmo.service.InformService;
import com.tianmo.util.JdbcUtil;

import java.util.List;

public class InformServiceImpl implements InformService {
    JdbcUtil jdbcUtil=new JdbcUtil();
    @Override
    public List<Inform> selectQuery() {

        return null;
    }

    @Override
    public Inform select() {
        // 编写SQL
        String sql = "select * from inform";
        // 执行SQL
        List<Inform> informs = jdbcUtil.executeQuery(sql, Inform.class);
        if (informs == null || informs.size() <= 0){
            return null;
        }
        //对结果进行处理
        return informs.get(0);
    }
}
