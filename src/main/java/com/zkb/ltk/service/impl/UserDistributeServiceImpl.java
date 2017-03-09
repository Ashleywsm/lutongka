package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.UserDistributeDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.UserDistributeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/8.
 */
@Service
public class UserDistributeServiceImpl implements UserDistributeService {
    UserDistributeDao userDistributeDao;

    public UserDistributeDao getUserDistributeDao() {
        return userDistributeDao;
    }

    public void setUserDistributeDao(UserDistributeDao userDistributeDao) {
        this.userDistributeDao = userDistributeDao;
    }

    public String getUserNumber(Date dateStart, Date dateEnd){
        List<traffic_data> traffic_datas = userDistributeDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Integer> hashMap = new HashMap<String, Integer>();
        while(it.hasNext()){
            traffic_data trafficData = it.next();

        }
        return null;
    }


    public String getUserConsumeNumber(Date dateStart,Date dateEnd){
        List<traffic_data> traffic_datas = userDistributeDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Double> hashMap = new HashMap<String,Double>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            String province = trafficData.getProvince();
            Double consume = Double.valueOf(trafficData.getLastMoney());
            if(hashMap.containsKey(province)){
                Double consumes = hashMap.get(province)+consume;
                hashMap.put(province,consumes);
            }else{
                hashMap.put(province,consume);
            }
        }


        return null;
    }

    public String getUserConsumeMoney(Date dateStart,Date dateEnd){
        List<traffic_data> traffic_datas = userDistributeDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Integer> hashMap = new HashMap<String,Integer>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            String province = trafficData.getProvince();

            if(hashMap.containsKey(province)){

            }else{

            }
        }
        return  null;
    }



}
