package com.zkb.ltk.dao;

import com.zkb.ltk.model.traffic_data;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public interface traffic_dataDao extends Dao<traffic_data,String>{
    List<traffic_data> getDataByProvinceDate(String province,String date);
    List<traffic_data> getDataByStartEnd(String dateStart, String dateEnd);
    List<traffic_data> getDataByProvinceStartEnd(String province,String dateStart,String dateEnd);
    List<traffic_data> getDataByProvince(String province);
}
