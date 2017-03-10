package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.UserDistributeDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.UserDistributeService;
import org.springframework.stereotype.Service;

import java.util.*;

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
    //各省的用户数目
    public String getUserNumber(Date dateStart, Date dateEnd){
        List<traffic_data> traffic_datas = userDistributeDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Integer> hashMap = new HashMap<String, Integer>();
        HashMap<String ,Integer> proIDMap = new HashMap<String, Integer>();
        while(it.hasNext()){
            traffic_data trafficData = it.next();
            String paycard = trafficData.getPaycard();
            String province = trafficData.getProvince();
            if(hashMap.containsKey(province)){
                if(proIDMap.containsKey(province+paycard)){

                }else {
                    proIDMap.put(province+paycard,1);
                    Integer number = hashMap.get(province);
                    number++;
                    hashMap.put(province,number);
                }
            }else {
                hashMap.put(province,1);
            }
        }
        List<HashMap.Entry<String,Integer>> list = new ArrayList<HashMap.Entry<String,Integer>>(hashMap.entrySet());
        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int i=0;
        int sum = 0;
        int top10 = 0;
        for(HashMap.Entry<String,Integer> mapping: list){
            i++;
            String province = mapping.getKey();
            Integer num = mapping.getValue();
            if(i<=10){
                top10+=num;
            }
            sum+=num;
        }

        return null;
    }

    //各省的用户消费次数
    public String getUserConsumeNumber(Date dateStart,Date dateEnd){
        List<traffic_data> traffic_datas = userDistributeDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Double> hashMap = new HashMap<String,Double>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            String province = trafficData.getProvince();
            Double consume = trafficData.getLastMoney();
            if(hashMap.containsKey(province)){
                Double consumes = hashMap.get(province)+consume;
                hashMap.put(province,consumes);
            }else{
                hashMap.put(province,consume);
            }
        }


        return null;
    }
    //各省的用户消费金额
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
