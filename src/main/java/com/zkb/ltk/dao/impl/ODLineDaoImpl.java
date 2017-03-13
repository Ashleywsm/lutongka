package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.ODlineDao;
import com.zkb.ltk.model.comparison;
import com.zkb.ltk.model.stations;
import com.zkb.ltk.model.traffic_data;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
@Repository("ODLineDao")
public class ODLineDaoImpl extends DaoImpl implements ODlineDao {
    public List<traffic_data> getTraffic_dataByDate(String province){
        String sql = "select * from traffic_data where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        List<traffic_data> traffic_datas = super.sqlGetList(sql,values);
        return traffic_datas;
    }

    public HashMap<String,String> getComparison(){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        String sql = "select * from comparison";
        List<comparison> comparisons = super.sqlGetList(sql);
        for(int i=0;i<comparisons.size();i++){
            comparison table = comparisons.get(i);
            String id = table.getId();
            String station = table.getStation();
            String type = table.getStationType();
            String poi_id = table.getPoi_id();
            hashMap.put(id+"|"+station+"|"+type,poi_id);
        }
        return hashMap;
    }
    public HashMap<String,String> getStation(String province){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        String sql = "select * from stations where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        List<stations> stationsList = super.sqlGetList(sql,values);
        for(int i=0;i<stationsList.size();i++){
            stations station_table = stationsList.get(i);
            String poi_id = station_table.getPoi_id();
            String station_name = station_table.getStation_name();
            hashMap.put(poi_id,station_name);
        }
        return hashMap;
    }
}
