package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.comparisonDao;
import com.zkb.ltk.dao.provinceDao;
import com.zkb.ltk.dao.stationsDao;
import com.zkb.ltk.dao.traffic_dataDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.ODLineService;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public class ODLineServiceImpl implements ODLineService{
    traffic_dataDao traffic_datadao;
    comparisonDao comparisondao;
    stationsDao stationdao;
    provinceDao provincedao;

    public traffic_dataDao getTraffic_datadao() {
        return traffic_datadao;
    }

    public void setTraffic_datadao(traffic_dataDao traffic_datadao) {
        this.traffic_datadao = traffic_datadao;
    }

    public comparisonDao getComparisondao() {
        return comparisondao;
    }

    public void setComparisondao(comparisonDao comparisondao) {
        this.comparisondao = comparisondao;
    }

    public stationsDao getStationdao() {
        return stationdao;
    }

    public void setStationdao(stationsDao stationdao) {
        this.stationdao = stationdao;
    }

    public provinceDao getProvincedao() {
        return provincedao;
    }

    public void setProvincedao(provinceDao provincedao) {
        this.provincedao = provincedao;
    }

    public HashMap<String,String> getODLine(String province){
        String proID = provincedao.getprovinceidByProvince(province);
        List<traffic_data> traffic_datas = traffic_datadao.getDataByProvince(province);
        HashMap<String,String> comparisons = comparisondao.getComparisonByprovinceid(proID);
        HashMap<String,String> station_map = stationdao.getStationLngLatByProvince(province);
        Iterator<traffic_data> it = traffic_datas.iterator();

        HashMap<String,HashMap<String,Integer>> in_map = new HashMap<String, HashMap<String,Integer>>();
        HashMap<String,HashMap<String,Integer>> out_map = new HashMap<String, HashMap<String,Integer>>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            String innetID = trafficData.getInNetID();
            String inStationID = trafficData.getInStationID();
            String origin = trafficData.getOrigin();
            String poi_in = comparisons.get(proID+"|"+innetID+"|"+inStationID+"|"+origin+"|入");
            String inStation = station_map.get(poi_in);
            String outnetID = trafficData.getOutNetID();
            String outStationID = trafficData.getOutStationID();
            String destination = trafficData.getDestination();
            String poi_out = comparisons.get(proID+"|"+outnetID+"|"+outStationID+"|"+destination+"|出");
            String outStation = station_map.get(poi_out);
            if(inStation.equals(null)||outStation.equals(null)){

            }else {

            }

        }
        return null;
    }
    public HashMap<String,String> getStationLngLat(String province){

        return null;
    }
}
