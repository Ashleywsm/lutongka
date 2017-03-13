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
    public HashMap<String, Integer> getUserNumber(Date dateStart, Date dateEnd){
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
        int sum =0;

        for(String key:hashMap.keySet()){
            Integer val = hashMap.get(key);
            sum = sum + val;
        }

        HashMap<String ,Integer> userNumber = new HashMap<String, Integer>();
        String string1 = "山东省"; Integer integer1 = hashMap.get(string1);
        String string2 = "江苏省";Integer integer2 = hashMap.get(string2);
        String string3 = "广东省";Integer integer3 = hashMap.get(string3);
        String string4 = "河北省";Integer integer4 = hashMap.get(string4);
        String string5 = "浙江省";Integer integer5 = hashMap.get(string5);
        String string6 = "北京市";Integer integer6 = hashMap.get(string6);
        String string7 = "上海市";Integer integer7 = hashMap.get(string7);
        String string8 = "天津市";Integer integer8 = hashMap.get(string8);
        String string9 = "安徽省";Integer integer9 = hashMap.get(string9);
        String string10 = "湖北省";Integer integer10 = hashMap.get(string10);
        String string11 = "其他省";Integer integer11 = sum -integer1-integer2-integer3-integer4-integer5-integer6-integer7-integer8-integer9-integer10;
        userNumber.put(string1,integer1);
        userNumber.put(string2,integer2);
        userNumber.put(string3,integer3);
        userNumber.put(string4,integer4);
        userNumber.put(string5,integer5);
        userNumber.put(string6,integer6);
        userNumber.put(string7,integer7);
        userNumber.put(string8,integer8);
        userNumber.put(string9,integer9);
        userNumber.put(string10,integer10);
        userNumber.put(string11,integer11);

        return userNumber;
    }

    //各省的用户消费次数
    public HashMap<String,Integer> getUserConsumeNumber(Date dateStart,Date dateEnd){
        List<traffic_data> traffic_datas = userDistributeDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Integer> hashMap = new HashMap<String,Integer>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            String province = trafficData.getProvince();

            if(hashMap.containsKey(province)){
                Integer number = hashMap.get(province);
                hashMap.put(province,number);
            }else{
                hashMap.put(province,1);
            }
        }
        int sum =0;

        for(String key:hashMap.keySet()){
            Integer val = hashMap.get(key);
            sum = sum + val;
        }

        HashMap<String ,Integer> userConsumeNumber = new HashMap<String, Integer>();
        String string1 = "山东省";Integer integer1 = hashMap.get(string1);
        String string2 = "江苏省";Integer integer2 = hashMap.get(string2);
        String string3 = "广东省";Integer integer3 = hashMap.get(string3);
        String string4 = "河北省";Integer integer4 = hashMap.get(string4);
        String string5 = "浙江省";Integer integer5 = hashMap.get(string5);
        String string6 = "北京市";Integer integer6 = hashMap.get(string6);
        String string7 = "上海市";Integer integer7 = hashMap.get(string7);
        String string8 = "天津市";Integer integer8 = hashMap.get(string8);
        String string9 = "安徽省";Integer integer9 = hashMap.get(string9);
        String string10 = "湖北省";Integer integer10 = hashMap.get(string10);
        String string11 = "其他省";Integer integer11 = sum -integer1-integer2-integer3-integer4-integer5-integer6-integer7-integer8-integer9-integer10;
        userConsumeNumber.put(string1,integer1);
        userConsumeNumber.put(string2,integer2);
        userConsumeNumber.put(string3,integer3);
        userConsumeNumber.put(string4,integer4);
        userConsumeNumber.put(string5,integer5);
        userConsumeNumber.put(string6,integer6);
        userConsumeNumber.put(string7,integer7);
        userConsumeNumber.put(string8,integer8);
        userConsumeNumber.put(string9,integer9);
        userConsumeNumber.put(string10,integer10);
        userConsumeNumber.put(string11,integer11);

        return userConsumeNumber;
    }
    //各省的用户消费金额
    public HashMap<String,Double> getUserConsumeMoney(Date dateStart,Date dateEnd){
        List<traffic_data> traffic_datas = userDistributeDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Double> hashMap = new HashMap<String,Double>();
        while (it.hasNext()){
            traffic_data trafficData = it.next();
            String province = trafficData.getProvince();
            Double consume = trafficData.getLastMoney();
            if(hashMap.containsKey(province)){
                Double consumes = hashMap.get(province);
                consumes = consumes +consume;
                hashMap.put(province,consumes);
            }else{
                hashMap.put(province,consume);
            }
        }

        Double sum = 0.0;
        for(String key:hashMap.keySet()){
            Double consume = hashMap.get(key);
            sum = sum+consume;
        }
        HashMap<String ,Double> userConsumeMoney = new HashMap<String, Double>();
        String string1 = "山东省";Double consume1 = hashMap.get(string1);
        String string2 = "江苏省";Double consume2 = hashMap.get(string2);
        String string3 = "广东省";Double consume3 = hashMap.get(string3);
        String string4 = "河北省";Double consume4 = hashMap.get(string4);
        String string5 = "浙江省";Double consume5 = hashMap.get(string5);
        String string6 = "北京市";Double consume6 = hashMap.get(string6);
        String string7 = "上海市";Double consume7 = hashMap.get(string7);
        String string8 = "天津市";Double consume8 = hashMap.get(string8);
        String string9 = "安徽省";Double consume9 = hashMap.get(string9);
        String string10 = "湖北省";Double consume10 = hashMap.get(string10);
        String string11 = "其他省";Double consume11 = hashMap.get(string11);
        userConsumeMoney.put(string1,consume1);
        userConsumeMoney.put(string2,consume2);
        userConsumeMoney.put(string3,consume3);
        userConsumeMoney.put(string4,consume4);
        userConsumeMoney.put(string5,consume5);
        userConsumeMoney.put(string6,consume6);
        userConsumeMoney.put(string7,consume7);
        userConsumeMoney.put(string8,consume8);
        userConsumeMoney.put(string9,consume9);
        userConsumeMoney.put(string10,consume10);
        userConsumeMoney.put(string11,consume11);

        return  userConsumeMoney;
    }



}
