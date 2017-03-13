package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.HighwayLineDao;
import com.zkb.ltk.model.comparison;
import com.zkb.ltk.model.shortest_paths;
import com.zkb.ltk.model.traffic_data;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
@Repository("HighwayLineDao")
public class HighwayLineDaoImpl extends DaoImpl implements HighwayLineDao{
    public List<traffic_data> getHighwayLineByDate(String province,Date date){
        String sql = "select * from traffic_data where province = ? and inTime >= ? and outTime < ?";
        Date date1 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);
        date1 = calendar.getTime();
        Object[] values = new Object[3];
        values[0] = province;
        values[1] = date;
        values[2] = date1;
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
    public HashMap<String,String> getShortestPath(){
        HashMap<String,String> shortestPath = new HashMap<String, String>();
        String sql = "select * from shortest_paths";
        List<shortest_paths> paths = super.sqlGetList(sql);
        for(int i=0;i<paths.size();i++){
            shortest_paths shortpath = paths.get(i);
            String origin = shortpath.getPath();
            String destination = shortpath.getDestination();
            String path = shortpath.getPath();
            shortestPath.put(origin+"|"+destination,path);
        }
        return shortestPath;
    }
}
