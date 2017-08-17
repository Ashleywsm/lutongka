package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.comparisonDao;
import com.zkb.ltk.dao.provinceDao;
import com.zkb.ltk.dao.stationsDao;
import com.zkb.ltk.dao.traffic_dataDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.ODLineService;

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

    public traffic_dataDao getTraffic_datadao() {return traffic_datadao;}
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

    public String getODLine(String province,String date){
        String proID = provincedao.getprovinceidByProvince(province);
        List<traffic_data> traffic_datas = traffic_datadao.getThirtyDataByProvinceDate(province,date);
        HashMap<String,String> comparisons = comparisondao.getComparisonByprovinceid(proID);
        HashMap<String,String> stationname_map = stationdao.getStationNameByProvince(province);
        Iterator<traffic_data> it = traffic_datas.iterator();

        HashMap<String,HashMap<String,Integer>> in_map = new HashMap<String, HashMap<String,Integer>>();
        HashMap<String,Integer> in_map_count = new HashMap<String, Integer>();
        HashMap<String,HashMap<String,Integer>> out_map = new HashMap<String, HashMap<String,Integer>>();
        HashMap<String,Integer> out_map_count = new HashMap<String, Integer>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            String innetID = trafficData.getInNetID();
            String inStationID = trafficData.getInStationID();
            String origin = trafficData.getOrigin();
            String poi_in = comparisons.get(proID+"|"+innetID+"|"+inStationID+"|"+origin+"|入");
            String inStation = stationname_map.get(poi_in);
            String outnetID = trafficData.getOutNetID();
            String outStationID = trafficData.getOutStationID();
            String destination = trafficData.getDestination();
            String poi_out = comparisons.get(proID+"|"+outnetID+"|"+outStationID+"|"+destination+"|出");
            String outStation = stationname_map.get(poi_out);
            if(poi_in==null||poi_out==null||inStation==null||outStation==null){

            }else {
                String in = poi_in+"|"+inStation;
                String out = poi_out+"|"+outStation;
                if(in_map.containsKey(in)){
                    HashMap<String,Integer> in_map_value = in_map.get(in);
                    if(in_map_value.containsKey(out)){
                        Integer number = in_map_value.get(out);
                        number++;
                        in_map_value.put(out,number);
                        in_map.put(in,in_map_value);
                    }else{
                        in_map_value.put(out,1);
                        in_map.put(in,in_map_value);
                    }
                    Integer sum_in = in_map_count.get(in);
                    sum_in++;
                    in_map_count.put(in,sum_in);

                }else{
                    HashMap<String,Integer> in_map_value = new HashMap<String, Integer>();
                    in_map_value.put(out,1);
                    in_map.put(in,in_map_value);

                    in_map_count.put(in,1);
                }

                if(out_map.containsKey(out)){
                    HashMap<String,Integer> out_map_value = out_map.get(out);
                    if(out_map_value.containsKey(in)){
                        Integer number = out_map_value.get(in)+1;
                        out_map_value.put(in,number);
                        out_map.put(out,out_map_value);
                    }else {
                        out_map_value.put(in,1);
                        out_map.put(out,out_map_value);
                    }
                    Integer sum_out = out_map_count.get(out);
                    sum_out++;
                    out_map_count.put(out,sum_out);
                }else{
                    HashMap<String,Integer> out_map_value = new HashMap<String, Integer>();
                    out_map_value.put(in,1);
                    out_map.put(out,out_map_value);

                    out_map_count.put(out,1);
                }


            }



        }

        //比较出最大的value
        String max_in_key = null;
        Integer max_in_value = 0;
        for(String in:in_map_count.keySet()){
            Integer number = in_map_count.get(in);
            if(number >= max_in_value){
                max_in_key = in;
            }
        }
        String max_out_key = null;
        Integer max_out_value = 0;
        for(String out:out_map_count.keySet()){
            Integer number = out_map_count.get(out);
            if(number>=max_out_value){
                max_out_key = out;
            }
        }


        //转成string
        String s=null;
        if(max_in_key==null||max_out_key==null){

        }else {
            StringBuffer buffer = new StringBuffer("[\r\n");
            HashMap<String,Integer> max_in_map = in_map.get(max_in_key);
            HashMap<String,Integer> max_out_map = out_map.get(max_out_key);
            for(String key_in:max_in_map.keySet()){
                Integer value_in = max_in_map.get(key_in);
                buffer.append("[{name:\'"+max_in_key+"\'},{name:\'"+key_in+"\',value:"+value_in+"}],\r\n");
            }
            buffer.append("]@[\r\n");
            for(String key_out:max_out_map.keySet()){
                Integer value_out = max_out_map.get(key_out);
                buffer.append("[{name:\'"+key_out+"\'},{name:\'"+max_out_key+"\',value:"+value_out+"}],\r\n");
            }
            buffer.append("]");
            s = buffer.toString();
        }

        return s;
    }
    public String getPOIStationLngLat(String province){
        HashMap<String,String> map = new HashMap<String, String>();
        map = stationdao.getPOIStationLngLatByProvince(province);
        StringBuffer geolnglat = new StringBuffer("{");
        for(String key:map.keySet()){
              String value = map.get(key);

              String lng = value.substring(0,value.indexOf("|"));
              String lat = value.substring(value.indexOf("|")+1);
              geolnglat.append("\'"+key+"\':["+lng+","+lat+"],\r\n");
        }
        geolnglat.append("}");
        return geolnglat.toString();
    }
}
