package com.zkb.ltk.dao;

import com.zkb.ltk.model.traffic_data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/7.
 */
public interface UserDistributeDao extends Dao{
    List<traffic_data> getTraffic_dataByDate(Date dateStart, Date dateEnd);

}
