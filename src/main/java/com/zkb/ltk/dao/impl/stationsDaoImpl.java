package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.stationsDao;
import com.zkb.ltk.model.stations;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public class stationsDaoImpl extends DaoImpl<stations,String> implements stationsDao {
    public HashMap<String,String> getStationLngLatByProvince(String province){
        List<stations> stationsList = null;
        HashMap<String,String> station_map = new HashMap<String, String>();
        String sql = "select * from stations where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        stationsList = super.sqlGetList(sql,values);
        for(int i=0;i<stationsList.size();i++){
            stations station = stationsList.get(i);
            String poi = station.getPoi_id();
            String lng = String.valueOf(station.getLongitude());
            String lat = String.valueOf(station.getLatitude());
            String l = lng+"|"+lat;
            station_map.put(poi,l);
        }

        return station_map;
    }
}
