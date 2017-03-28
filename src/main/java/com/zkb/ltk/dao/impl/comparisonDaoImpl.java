package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.comparisonDao;
import com.zkb.ltk.model.comparison;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public class comparisonDaoImpl extends DaoImpl<comparison,String> implements comparisonDao {
    public HashMap<String,String> getComparisonByprovinceid(String provinceid){
        HashMap<String,String> comprsion_map = new HashMap<String, String>();
        String sql = "select * from comparison where provinceID = ?";
        Object[] values = new Object[1];
        values[0] = provinceid;
        List<comparison> comparisons = super.sqlGetList(sql,values);
        for(int i=0;i<comparisons.size();i++){
            comparison table = comparisons.get(i);
            String id = table.getId();
            String station = table.getStation();
            String type = table.getStationType();
            String poi_id = table.getPoi_id();
            comprsion_map.put(id+"|"+station+"|"+type,poi_id);
        }
        return comprsion_map;
    }
}
