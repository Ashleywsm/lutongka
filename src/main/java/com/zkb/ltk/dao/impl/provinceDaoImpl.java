package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.provinceDao;
import com.zkb.ltk.model.province_table;

import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public class provinceDaoImpl extends DaoImpl<province_table,String> implements provinceDao {
    public String getprovinceidByProvince(String province){
        String id = null;
        String hql = "from province_table where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        List<province_table> provinces = super.hqlGetList(hql,values);
        if(provinces==null){
            System.out.println("blabla error");
        }else {
            System.out.println("yes");
        }
        if(provinces.size() == 1){
            id = provinces.get(0).getProvinceID();
        }
        return id;
    }
}
