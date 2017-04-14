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
        String hql = "from stations where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        stationsList = super.hqlGetList(hql,values);
        for(int i=0;i<stationsList.size();i++){
            stations station = stationsList.get(i);
            String poi = station.getPoi_id();
            Double lng = station.getLongitude();
            Double lat = station.getLatitude();
            Double temp;
            if(lat>lng){
                temp = lng;
                lng = lat;
                lat = lng;
            }
            Double x = lng,y = lat;
            Double z = Math.sqrt(x*x+y*y)+0.00002*Math.sin(y*Math.PI*3000.0/180.0);
            Double theta = Math.atan2(y, x)+0.000003*Math.cos(x*Math.PI*3000.0/180.0);
            Double bd_lng = z*Math.cos(theta)+0.0065;
            Double bd_lat = z*Math.sin(theta)+0.006;

            String l = String.valueOf(bd_lng)+"|"+String.valueOf(bd_lat);
            station_map.put(poi,l);
        }

        return station_map;
    }
    public HashMap<String,String> getPOIStationLngLatByProvince(String province){
        List<stations> stationsList = null;
        HashMap<String,String> station_map = new HashMap<String, String>();
        String hql = "from stations where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        stationsList = super.hqlGetList(hql,values);
        for(int i=0;i<stationsList.size();i++){
            stations station = stationsList.get(i);
            String poi = station.getPoi_id();
            String station_name = station.getStation_name();
            Double lng = station.getLongitude();
            Double lat = station.getLatitude();
            Double temp;
            if(lng<lat){
                temp = lng;
                lng = lat;
                lat = lng;
            }
            Double x = lng,y = lat;
            Double z = Math.sqrt(x*x+y*y)+0.00002*Math.sin(y*Math.PI*3000.0/180.0);
            Double theta = Math.atan2(y, x)+0.000003*Math.cos(x*Math.PI*3000.0/180.0);
            Double bd_lng = z*Math.cos(theta)+0.0065;
            Double bd_lat = z*Math.sin(theta)+0.006;

            String string1 = poi+"|"+station_name;
            String string2 = String.valueOf(bd_lng)+"|"+String.valueOf(bd_lat);
            station_map.put(string1,string2);
        }
        return station_map;
    }
    public HashMap<String,String> getStationLngLat(){
        List<stations> stationsList = null;
        HashMap<String,String> station_map = new HashMap<String, String>();
        String hql = "from stations";
        stationsList = super.hqlGetList(hql);
        for(int i=0;i<stationsList.size();i++){
            stations station = stationsList.get(i);
            String poi = station.getPoi_id();
            Double lng = station.getLongitude();
            Double lat = station.getLatitude();
            Double temp;
            if(lng<lat){
                temp = lng;
                lng = lat;
                lat = lng;
            }
            Double x = lng,y = lat;
            Double z = Math.sqrt(x*x+y*y)+0.00002*Math.sin(y*Math.PI*3000.0/180.0);
            Double theta = Math.atan2(y, x)+0.000003*Math.cos(x*Math.PI*3000.0/180.0);
            Double bd_lng = z*Math.cos(theta)+0.0065;
            Double bd_lat = z*Math.sin(theta)+0.006;

            String l = String.valueOf(bd_lng)+"|"+String.valueOf(bd_lat);
            station_map.put(poi,l);
        }

        return station_map;
    }
    public HashMap<String,String> getStationNameByProvince(String province){
        List<stations> stationsList = null;
        HashMap<String,String> station_map = new HashMap<String, String>();
        String hql = "from stations where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        stationsList = super.hqlGetList(hql,values);
        for(int i=0;i<stationsList.size();i++) {
            stations station = stationsList.get(i);
            String poi = station.getPoi_id();
            String stationname = station.getStation_name();
            station_map.put(poi,stationname);
        }
        return station_map;
    }
}
