package com.zkb.ltk.dao;

import com.zkb.ltk.model.province_table;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public interface provinceDao extends Dao<province_table,String>{
    String getprovinceidByProvince(String province);
}
