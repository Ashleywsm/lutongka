package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.UserDistributeDao;
import com.zkb.ltk.model.traffic_data;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/7.
 */
@Repository("UserDistributeDao")
public class UserDistributeDaoImpl extends DaoImpl implements UserDistributeDao {
    public List<traffic_data> getTraffic_dataByDate(Date dateStart, Date dateEnd){
        String sql = "select * from traffic_data where intime > ? and outtime < ?";
        Object[] values = new Object[2];
        values[0] = dateStart;
        values[1] = dateEnd;
        List<traffic_data> traffic_datas = super.sqlGetList(sql,values);

        return traffic_datas;
    }
}
