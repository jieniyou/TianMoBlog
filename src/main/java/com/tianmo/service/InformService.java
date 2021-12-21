package com.tianmo.service;

import com.tianmo.bean.inform.Inform;

import java.util.List;

public interface InformService {

    List<Inform> selectQuery();

    Inform select();
}
