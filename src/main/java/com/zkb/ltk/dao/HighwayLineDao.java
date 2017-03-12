package com.zkb.ltk.dao;

import com.zkb.ltk.model.traffic_data;

import java.util.Date;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public interface HighwayLineDao extends Dao{
    List<traffic_data> getHighwayLineByDate(Date date);
}