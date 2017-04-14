package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.roadlinkDao;
import com.zkb.ltk.model.roadlink;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public class roadlinkDaoImpl extends DaoImpl<roadlink,String> implements roadlinkDao {
    public HashMap<String,String> getLulianLngLatByprovince(String province){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        String sql = "select * from roadlink where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        List<roadlink> roadlinks = super.sqlGetList(sql,values);
        for(int i=0;i<roadlinks.size();i++){
            roadlink lulian = roadlinks.get(i);
            String id = lulian.getId();
            Double lng = lulian.getLongitude();
            Double lat = lulian.getLatitude();
            //gcj转换百度
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

            String center = String.valueOf(bd_lng)+"|"+String.valueOf(bd_lat);
            hashMap.put(id,center);
        }
        return hashMap;
    }
}
