package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.HighwayLineDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.HighwayLineService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
@Service
public class HighwayLineServiceImpl implements HighwayLineService{
    HighwayLineDao highwayLineDao;

    public HighwayLineDao getHighwayLineDao() {
        return highwayLineDao;
    }

    public void setHighwayLineDao(HighwayLineDao highwayLineDao) {
        this.highwayLineDao = highwayLineDao;
    }

    public String getHighwayLine(String province,Date date){
        List<traffic_data> traffic_datas = highwayLineDao.getHighwayLineByDate(province,date);
        HashMap<String,String> comparisons = highwayLineDao.getComparison();
        HashMap<String,String> paths = highwayLineDao.getShortestPath();

        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<Integer,HashMap<String,Integer>> hashMap = new HashMap<Integer, HashMap<String, Integer>>();
        String proID = "null";
        if(province.equals("北京市")){ proID = "11"; }
        else if(province.equals("天津市")){  proID = "12"; }
        else if(province.equals("河北省")){  proID = "13"; }
        else if(province.equals("山西省")){  proID = "14"; }
        else if(province.equals("内蒙古自治区")){  proID = "15"; }
        else if(province.equals("辽宁省")){  proID = "21"; }
        else if(province.equals("吉林省")){  proID = "22"; }
        else if(province.equals("黑龙江省")){  proID = "23"; }
        else if(province.equals("上海市")){  proID = "31"; }
        else if(province.equals("江苏省")){  proID = "32"; }
        else if(province.equals("浙江省")){  proID = "33"; }
        else if(province.equals("安徽省")){  proID = "34"; }
        else if(province.equals("福建省")){  proID = "35"; }
        else if(province.equals("江西省")){  proID = "36"; }
        else if(province.equals("山东省")){  proID = "37"; }
        else if(province.equals("河南省")){  proID = "41"; }
        else if(province.equals("湖北省")){  proID = "42"; }
        else if(province.equals("湖南省")){  proID = "43"; }
        else if(province.equals("广东省")){  proID = "44"; }
        else if(province.equals("广西壮族自治区")){  proID = "45"; }
        else if(province.equals("海南省")){  proID = "46"; }
        else if(province.equals("重庆市")){  proID = "50"; }
        else if(province.equals("四川省")){  proID = "51"; }
        else if(province.equals("贵州省")){  proID = "52"; }
        else if(province.equals("云南省")){  proID = "53"; }
        else if(province.equals("西藏自治区")){  proID = "54"; }
        else if(province.equals("陕西省")){  proID = "61"; }
        else if(province.equals("甘肃省")){  proID = "62"; }
        else if(province.equals("青海省")){  proID = "63"; }
        else if(province.equals("宁夏回族自治区")){  proID = "64"; }
        else if(province.equals("新疆维吾尔自治区")){  proID = "65"; }
        else if(province.equals("台湾省")){  proID = "71"; }
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            Date intime = trafficData.getInTime();
            String innetID = trafficData.getInNetID();
            String inStationID = trafficData.getInStationID();
            String origin = trafficData.getOrigin();
            String poi_in = comparisons.get(proID+"|"+innetID+"|"+inStationID+"|"+origin+"|入");
            String outnetID = trafficData.getOutNetID();
            String outStationID = trafficData.getOutStationID();
            String destination = trafficData.getDestination();
            String poi_out = comparisons.get(proID+"|"+outnetID+"|"+outStationID+"|"+destination+"|出");
            if(poi_in.equals(null)||poi_out.equals(null)){

            }else {
                String path = paths.get(poi_in+"|"+poi_out);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(intime);
                Integer hour = calendar.get(Calendar.HOUR_OF_DAY);


            }

        }
        return null;
    }
}
