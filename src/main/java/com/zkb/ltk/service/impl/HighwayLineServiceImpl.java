package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.*;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.HighwayLineService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
@Service
public class HighwayLineServiceImpl implements HighwayLineService{
    traffic_dataDao traffic_datadao;
    provinceDao provincedao;
    comparisonDao  comparisondao;
    pathDao pathdao;
    roadlinkDao roadlinkdao;

    public traffic_dataDao getTraffic_datadao() {
        return traffic_datadao;
    }

    public void setTraffic_datadao(traffic_dataDao traffic_datadao) {
        this.traffic_datadao = traffic_datadao;
    }

    public provinceDao getProvincedao() {
        return provincedao;
    }

    public void setProvincedao(provinceDao provincedao) {
        this.provincedao = provincedao;
    }

    public comparisonDao getComparisondao() {
        return comparisondao;
    }

    public void setComparisondao(comparisonDao comparisondao) {
        this.comparisondao = comparisondao;
    }

    public pathDao getPathdao() {
        return pathdao;
    }

    public void setPathdao(pathDao pathdao) {
        this.pathdao = pathdao;
    }

    public roadlinkDao getRoadlinkdao() {
        return roadlinkdao;
    }

    public void setRoadlinkdao(roadlinkDao roadlinkdao) {
        this.roadlinkdao = roadlinkdao;
    }

    public HashMap<Integer, HashMap<String,Integer>> getHighwayLine(String province, String date){
        String proID = provincedao.getprovinceidByProvince(province);
        List<traffic_data> traffic_datas = traffic_datadao.getDataByProvinceDate(province,date);
        HashMap<String,String> comparisons = comparisondao.getComparisonByprovinceid(proID);
        HashMap<String,String> paths = pathdao.getPathByProvinceID(proID);

        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<Integer,HashMap<String,Integer>> odmax = new HashMap<Integer, HashMap<String, Integer>>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            Timestamp intime = trafficData.getInTime();
            String innetID = trafficData.getInNetID();
            String inStationID = trafficData.getInStationID();
            String origin = trafficData.getOrigin();
            String poi_in = comparisons.get(proID+"|"+innetID+"|"+inStationID+"|"+origin+"|入");
            String outnetID = trafficData.getOutNetID();
            String outStationID = trafficData.getOutStationID();
            String destination = trafficData.getDestination();
            String poi_out = comparisons.get(proID+"|"+outnetID+"|"+outStationID+"|"+destination+"|出");
            if(poi_in==null||poi_out==null){

            }else {
                String path = paths.get(poi_in+"|"+poi_out);
                if(path==null){

                }else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(intime);
                    Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
                    String[] string_path = path.split("\\|");
                    for(int i=0;i<string_path.length-1;i++){
                        String id1 = string_path[i];
                        String id2 = string_path[i+1];
                        String id = id1+"|"+id2;
                        if(odmax.containsKey(hour)){
                            HashMap<String,Integer> luliannumber = odmax.get(hour);
                            if(luliannumber.containsKey(id)){
                                Integer number = luliannumber.get(id);
                                number++;
                                luliannumber.put(id,number);
                                odmax.put(hour,luliannumber);
                            }else {
                                Integer number = 1;
                                luliannumber.put(id,number);
                                odmax.put(hour,luliannumber);
                            }
                        }else {
                            HashMap<String,Integer> luliannumber = new HashMap<String, Integer>();
                            Integer number = 1;
                            luliannumber.put(id,number);
                            odmax.put(hour,luliannumber);
                        }
                    }
                }


            }

        }

        return odmax;
    }
    public HashMap<String,String> getLulianLngLat(String province){
        HashMap<String,String> lulian = roadlinkdao.getLulianLngLatByprovince(province);
        return lulian;
    }
}
