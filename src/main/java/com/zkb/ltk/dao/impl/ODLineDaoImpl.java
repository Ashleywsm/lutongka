package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.ODlineDao;
import com.zkb.ltk.model.traffic_data;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
@Repository("ODLineDao")
public class ODLineDaoImpl extends DaoImpl implements ODlineDao {
    public List<traffic_data> getTraffic_dataByDate(Date dateStart, Date dateEnd){
        String sql = "select * from traffic_data where intime > ? and outtime < ?";
        Object[] values = new Object[2];
        values[0] = dateStart;
        values[1] = dateEnd;
        List<traffic_data> traffic_datas = super.sqlGetList(sql,values);
        return traffic_datas;
    }
}
