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
        HashMap<String,String> comparison_map = new HashMap<String, String>();
        String hql = "from comparison where provinceID = ?";
        Object[] values = new Object[1];
        values[0] = provinceid;
        List<comparison> comparisons = super.hqlGetList(hql,values);
        System.out.println("comparison:"+comparisons.size());
        for(int i=0;i<comparisons.size();i++){
            comparison table = comparisons.get(i);
            String id = table.getId();
//            String station = table.getStation();
//            String type = table.getStationType();
            String poi_id = table.getPoi_id();
            comparison_map.put(id,poi_id);
        }
        return comparison_map;
    }
}
