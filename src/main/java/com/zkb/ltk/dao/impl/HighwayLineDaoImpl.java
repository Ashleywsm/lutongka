package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.HighwayLineDao;
import com.zkb.ltk.model.traffic_data;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
@Repository("HighwayLineDao")
public class HighwayLineDaoImpl extends DaoImpl implements HighwayLineDao{
    public List<traffic_data> getHighwayLineByDate(Date date){
        String sql = "select * from traffic_data where intime >= ? and outtime < (?+1)";
        Object[] values = new Object[2];
        values[0] = date;
        values[1] = date;
        List<traffic_data> traffic_datas = super.sqlGetList(sql,values);
        return traffic_datas;
    }
}
