package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.traffic_dataDao;
import com.zkb.ltk.model.traffic_data;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public class traffic_dataDaoImpl extends DaoImpl<traffic_data,String> implements traffic_dataDao {
    public List<traffic_data> getDataByProvinceDate(String province, String date){
        List<traffic_data> traffic_datas = null;
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat timeWithDetail=new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
            Date date_start = sdf.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(date_start);
            c.add(Calendar.DAY_OF_MONTH,1);
            Date date_end = c.getTime();
            Timestamp time_start = new Timestamp(date_start.getTime());
            Timestamp time_end = new Timestamp(date_end.getTime());

            String hql = "from traffic_data where province = ? and inTime >= ? and outTime < ?";
            Object[] values = new Object[3];
            values[0] = province;
            values[1] = time_start;
            values[2] = time_end;
            traffic_datas = super.hqlGetList(hql,values);

        }catch (ParseException e){
            e.printStackTrace();
        }

        return traffic_datas;
    }


    public List<traffic_data> getDataByStartEnd(String dateStart, String dateEnd){
        List<traffic_data> traffic_datas = null;
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date_start = sdf.parse(dateStart);
            Date date_end = sdf.parse(dateEnd);
            Timestamp time_start = new Timestamp(date_start.getTime());
            Timestamp time_end = new Timestamp(date_end.getTime());

            String sql = "select * from traffic_data where inTime >= ? and outTime < ?";
            Object[] values = new Object[2];
            values[0] = time_start;
            values[1] = time_end;
            traffic_datas = super.sqlGetList(sql,values);

        }catch (ParseException e){
            e.printStackTrace();
        }


        return traffic_datas;
    }
    public List<traffic_data> getDataByProvinceStartEnd(String province,String dateStart,String dateEnd){
        List<traffic_data> traffic_datas = null;
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date_start = sdf.parse(dateStart);
            Date date_end = sdf.parse(dateEnd);
            Timestamp time_start = new Timestamp(date_start.getTime());
            Timestamp time_end = new Timestamp(date_end.getTime());

            String sql = "select * from traffic_data where province = ? and inTime >= ? and outTime < ?";
            Object[] values = new Object[3];
            values[0] = province;
            values[1] = time_start;
            values[2] = time_end;
            traffic_datas = super.sqlGetList(sql,values);
        }catch (ParseException e){
            e.printStackTrace();
        }


        return traffic_datas;
    }
    public List<traffic_data> getDataByProvince(String province){
        String sql = "select * from traffic_data where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        List<traffic_data> traffic_datas = super.sqlGetList(sql,values);
        return traffic_datas;
    }
}
