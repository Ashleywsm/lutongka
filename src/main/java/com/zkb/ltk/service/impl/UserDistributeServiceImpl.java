package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.traffic_dataDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.UserDistributeService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ashley_wsm on 2017/3/8.
 */
@Service
public class UserDistributeServiceImpl implements UserDistributeService {
    traffic_dataDao datadao;

    public traffic_dataDao getTraffic_datadao() {return datadao;}

    public void setTraffic_datadao(traffic_dataDao traffic_datadao) {
        this.datadao = traffic_datadao;
    }

    public String getUser(String[] array,String dateStart, String dateEnd){
        List<traffic_data> traffic_datas = datadao.getDataByStartEnd(dateStart,dateEnd);

        Iterator<traffic_data> it = traffic_datas.iterator();
        HashMap<String ,Integer> AlluserNumber = new HashMap<String, Integer>();
        HashMap<String ,Integer> proIDMap = new HashMap<String, Integer>();//pro+paycard
        HashMap<String ,Integer> AllconsumeNumber = new HashMap<String,Integer>();
        HashMap<String ,Double> AllconsumeMoney = new HashMap<String,Double>();
        while(it.hasNext()){
            traffic_data trafficData = it.next();
            String paycard = trafficData.getPaycard();
            String province = trafficData.getProvince();
            Double consume = trafficData.getLastMoney();
            //用户数统计
            if(AlluserNumber.containsKey(province)){
                if(proIDMap.containsKey(province+paycard)){

                }else {
                    proIDMap.put(province+paycard,1);
                    Integer number = AlluserNumber.get(province);
                    number++;
                    AlluserNumber.put(province,number);
                }
            }else {
                AlluserNumber.put(province,1);
            }
            //用户消费次数统计
            if(AllconsumeNumber.containsKey(province)){
                Integer number = AllconsumeNumber.get(province);
                AllconsumeNumber.put(province,number);
            }else{
                AllconsumeNumber.put(province,1);
            }
            //用户消费金额统计
            if(AllconsumeMoney.containsKey(province)){
                Double consumes = AllconsumeMoney.get(province);
                consumes = consumes +consume;
                AllconsumeMoney.put(province,consumes);
            }else{
                AllconsumeMoney.put(province,consume);
            }

        }

        //计算10个省份用户数
        int sum_usernumber =0;
        for(String key:AlluserNumber.keySet()){
            Integer val = AlluserNumber.get(key);
            sum_usernumber = sum_usernumber + val;
        }
        HashMap<String ,Integer> userNumber = new HashMap<String, Integer>();
        Integer integer1 = AlluserNumber.get(array[0]);
        Integer integer2 = AlluserNumber.get(array[1]);
        Integer integer3 = AlluserNumber.get(array[2]);
        Integer integer4 = AlluserNumber.get(array[3]);
        Integer integer5 = AlluserNumber.get(array[4]);
        Integer integer6 = AlluserNumber.get(array[5]);
        Integer integer7 = AlluserNumber.get(array[6]);
        Integer integer8 = AlluserNumber.get(array[7]);
        Integer integer9 = AlluserNumber.get(array[8]);
        Integer integer10 = AlluserNumber.get(array[9]);
        Integer integer11 = sum_usernumber -integer1-integer2-integer3-integer4-integer5-integer6-integer7-integer8-integer9-integer10;
        userNumber.put(array[0],integer1);
        userNumber.put(array[1],integer2);
        userNumber.put(array[2],integer3);
        userNumber.put(array[3],integer4);
        userNumber.put(array[4],integer5);
        userNumber.put(array[5],integer6);
        userNumber.put(array[6],integer7);
        userNumber.put(array[7],integer8);
        userNumber.put(array[8],integer9);
        userNumber.put(array[9],integer10);
        userNumber.put(array[10],integer11);
        //计算10个省用户消费次数
        int sum_consumenumber =0;
        for(String key:AllconsumeNumber.keySet()){
            Integer val = AllconsumeNumber.get(key);
            sum_consumenumber = sum_consumenumber + val;
        }
        HashMap<String ,Integer> userConsumeNumber = new HashMap<String, Integer>();
        Integer int1 = AllconsumeNumber.get(array[0]);
        Integer int2 = AllconsumeNumber.get(array[1]);
        Integer int3 = AllconsumeNumber.get(array[2]);
        Integer int4 = AllconsumeNumber.get(array[3]);
        Integer int5 = AllconsumeNumber.get(array[4]);
        Integer int6 = AllconsumeNumber.get(array[5]);
        Integer int7 = AllconsumeNumber.get(array[6]);
        Integer int8 = AllconsumeNumber.get(array[7]);
        Integer int9 = AllconsumeNumber.get(array[8]);
        Integer int10 = AllconsumeNumber.get(array[9]);
        Integer int11 = sum_consumenumber -integer1-integer2-integer3-integer4-integer5-integer6-integer7-integer8-integer9-integer10;
        userConsumeNumber.put(array[0],int1);
        userConsumeNumber.put(array[1],int2);
        userConsumeNumber.put(array[2],int3);
        userConsumeNumber.put(array[3],int4);
        userConsumeNumber.put(array[4],int5);
        userConsumeNumber.put(array[5],int6);
        userConsumeNumber.put(array[6],int7);
        userConsumeNumber.put(array[7],int8);
        userConsumeNumber.put(array[8],int9);
        userConsumeNumber.put(array[9],int10);
        userConsumeNumber.put(array[10],int11);
        //计算10个省用户消费金额
        Double sum_cocnsumemoney = 0.0;
        for(String key:AllconsumeMoney.keySet()){
            Double consume = AllconsumeMoney.get(key);
            sum_cocnsumemoney = sum_cocnsumemoney+consume;
        }
        HashMap<String ,Double> userConsumeMoney = new HashMap<String, Double>();
        Double consume1 = AllconsumeMoney.get(array[0])/10000;
        Double consume2 = AllconsumeMoney.get(array[1])/10000;
        Double consume3 = AllconsumeMoney.get(array[2])/10000;
        Double consume4 = AllconsumeMoney.get(array[3])/10000;
        Double consume5 = AllconsumeMoney.get(array[4])/10000;
        Double consume6 = AllconsumeMoney.get(array[5])/10000;
        Double consume7 = AllconsumeMoney.get(array[6])/10000;
        Double consume8 = AllconsumeMoney.get(array[7])/10000;
        Double consume9 = AllconsumeMoney.get(array[8])/10000;
        Double consume10 = AllconsumeMoney.get(array[9])/10000;
        Double consume11 = sum_cocnsumemoney/10000-consume1-consume2-consume3-consume4-consume5-consume6-consume7-consume8-consume9-consume10;
        userConsumeMoney.put(array[0],consume1);
        userConsumeMoney.put(array[1],consume2);
        userConsumeMoney.put(array[2],consume3);
        userConsumeMoney.put(array[3],consume4);
        userConsumeMoney.put(array[4],consume5);
        userConsumeMoney.put(array[5],consume6);
        userConsumeMoney.put(array[6],consume7);
        userConsumeMoney.put(array[7],consume8);
        userConsumeMoney.put(array[8],consume9);
        userConsumeMoney.put(array[9],consume10);
        userConsumeMoney.put(array[10],consume11);

        Double max =0.0;
        StringBuffer buffer = new StringBuffer();
        for(String pro:userNumber.keySet()){
            Integer number = userNumber.get(pro);
            if(number>max)
                max = Double.valueOf(number);
        }
        for(String pro:userConsumeNumber.keySet()){
            Integer number = userConsumeNumber.get(pro);
            if(number>max)
                max = Double.valueOf(number);
        }
        for(String pro:userConsumeMoney.keySet()){
            Double money = userConsumeMoney.get(pro);
            if(money>max)
                max = money;
        }
        buffer.append("[{name:\'"+array[0]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[1]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[2]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[3]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[4]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[5]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[6]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[7]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[8]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[9]+"\',max:"+max+"},");
        buffer.append("{name:\'"+array[10]+"\',max:"+max+"},],");

        buffer.append("["+userNumber.get(array[0])+","+userNumber.get(array[1])+","+userNumber.get(array[2])+","+userNumber.get(array[3])+","+
                userNumber.get(array[4])+","+userNumber.get(array[5])+","+userNumber.get(array[6])+","+userNumber.get(array[7])+","+
                userNumber.get(array[8])+","+userNumber.get(array[9])+","+userNumber.get(array[10])+"],");

        buffer.append("["+userConsumeNumber.get(array[0])+","+userConsumeNumber.get(array[1])+","+userConsumeNumber.get(array[2])+","+userConsumeNumber.get(array[3])+","
                +userConsumeNumber.get(array[4])+","+userConsumeNumber.get(array[5])+","+userConsumeNumber.get(array[6])+","+userConsumeNumber.get(array[7])+","
                +userConsumeNumber.get(array[8])+","+userConsumeNumber.get(array[9])+","+userConsumeNumber.get(array[10])+"],");

        buffer.append("["+userConsumeMoney.get(array[0])+","+userConsumeMoney.get(array[1])+","+userConsumeMoney.get(array[2])+","+userConsumeMoney.get(array[3])+","
                +userConsumeMoney.get(array[4])+","+userConsumeMoney.get(array[5])+","+userConsumeMoney.get(array[6])+","+userConsumeMoney.get(array[7])+","
                +userConsumeMoney.get(array[8])+","+userConsumeMoney.get(array[9])+","+userConsumeMoney.get(array[10])+"],");



        return buffer.toString();
    }



}
