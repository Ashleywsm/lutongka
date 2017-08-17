package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.traffic_dataDao;
import com.zkb.ltk.service.ImportService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by ashley_wsm on 2017/5/31.
 */
public class ImportServiceImpl implements ImportService{

    public traffic_dataDao getTraffic_datadao() {
        return traffic_datadao;
    }

    public void setTraffic_datadao(traffic_dataDao traffic_datadao) {
        this.traffic_datadao = traffic_datadao;
    }

    traffic_dataDao traffic_datadao;

    public String importcsvfile(File file){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                String[] array_line = line.split(",");
                String[] array = new String[18];
                String wasteID = array_line[0];//1
                String exStation = array_line[3];//2
                String paycard = array_line[12];//3
                String lastmoney = array_line[28];//4
                String dealaddr = array_line[50];
                String vehinfo = array_line[52];
                String province = null;//18
                String intime=null;//5
                String innetid=null;//6
                String instationid=null;//7
                String origin=null;//8
                String outtime=null;//9
                String outnetid=null;//10
                String outstationid=null;//11
                String destination=null;//12
                String weight=null;//13
                String overlimit=null;//14
                String platecolor=null;//15
                String vehicleplate=null;//16
                String vehiclemodel=null;//17

                if(dealaddr.contains("|")||dealaddr.contains("【")){
                    String p = exStation.substring(2,4);
                    if(p.equals("11")){province = "北京市";}
                    else if(p.equals("12")){province = "天津市";}
                    else if(p.equals("13")){province = "河北省";}
                    else if(p.equals("14")){province = "山西省";}
                    else if(p.equals("15")){province = "内蒙古自治区";}
                    else if(p.equals("21")){province = "辽宁省";}
                    else if(p.equals("22")){province = "吉林省";}
                    else if(p.equals("23")){province = "黑龙江省";}
                    else if(p.equals("31")){province = "上海市";}
                    else if(p.equals("32")){province = "江苏省";}
                    else if(p.equals("33")){province = "浙江省";}
                    else if(p.equals("34")){province = "安徽省";}
                    else if(p.equals("35")){province = "福建省";}
                    else if(p.equals("36")){province = "江西省";}
                    else if(p.equals("37")){province = "山东省";}
                    else if(p.equals("41")){province = "河南省";}
                    else if(p.equals("42")){province = "湖北省";}
                    else if(p.equals("43")){province = "湖南省";}
                    else if(p.equals("44")){province = "广东省";}
                    else if(p.equals("45")){province = "广西自治区";}
                    else if(p.equals("46")){province = "海南省";}
                    else if(p.equals("50")){province = "重庆市";}
                    else if(p.equals("51")){province = "四川省";}
                    else if(p.equals("52")){province = "贵州省";}
                    else if(p.equals("53")){province = "云南省";}
                    else if(p.equals("54")){province = "西藏自治区";}
                    else if(p.equals("61")){province = "陕西省";}
                    else if(p.equals("62")){province = "甘肃省";}
                    else if(p.equals("63")){province = "青海省";}
                    else if(p.equals("64")){province = "宁夏自治区";}
                    else if(p.equals("65")){province = "新疆维吾尔自治区";}
                    else if(p.equals("71")){province = "台湾省";}
                    else if(p.equals("81")){province = "香港特别行政区";}
                    else if(p.equals("82")){province = "澳门特别行政区";}
                    String[] detail = dealaddr.split("\\|");
                    if(detail.length==13){
                        origin = detail[0];
                        destination = detail[1];
                        outnetid = detail[4];
                        outstationid = detail[5];
                        String time_out = detail[7];
                        if(time_out.length()==15){
                            outtime = time_out.substring(0,4)+"-"+time_out.substring(4,6)+"-"+time_out.substring(6,8)+"-"+time_out.substring(9,11)+"."+time_out.substring(11,13)+"."+time_out.substring(13,15);
                        }
                        innetid = detail[9];
                        instationid = detail[10];
                        String time_in = detail[12];
                        if(time_in.length()==15){
                            intime = time_in.substring(0,4)+"-"+time_in.substring(4,6)+"-"+time_in.substring(6,8)+"-"+time_in.substring(9,11)+"."+time_in.substring(11,13)+"."+time_in.substring(13,15);
                        }
                        String[] vehcle = vehinfo.substring(1).split("\\|");
                        platecolor = vehcle[0];
                        vehicleplate = vehcle[1];
                        vehiclemodel = vehcle[2];
                        array[0] = wasteID;
                        array[1] = exStation;
                        array[2] = paycard;
                        array[3] = lastmoney;
                        array[4] = intime;
                        array[5] = innetid;
                        array[6] = instationid;
                        array[7] = origin;
                        array[8] = outtime;
                        array[9] = outnetid;
                        array[10] = outstationid;
                        array[11] = destination;
                        array[12] = weight;
                        array[13] = overlimit;
                        array[14] = platecolor;
                        array[15] = vehicleplate;
                        array[16] = vehiclemodel;
                        array[17] = province;
                        int truth = traffic_datadao.importFile(array);
                        if(truth==0){
                            System.out.println(line);
                        }

                    }else {
                        System.out.println(line);
                    }

                }else {
                    province = "山东省";
                    intime = dealaddr.substring(0,dealaddr.indexOf(" "));
                    innetid = "null";
                    instationid = "null";
                    origin = dealaddr.substring(dealaddr.indexOf(" ")+1,dealaddr.indexOf("，"));
                    outtime = dealaddr.substring(dealaddr.indexOf(",")+1,dealaddr.indexOf(" ",dealaddr.indexOf(",")));
                    outnetid="null";
                    outstationid = "null";
                    destination = dealaddr.substring(dealaddr.indexOf(" ",dealaddr.indexOf(",")+1)+1,dealaddr.indexOf("出")+1);
                    String[] vehcle = vehinfo.substring(1).split("\\|");
                    platecolor = vehcle[0];
                    vehicleplate = vehcle[1];
                    vehiclemodel = vehcle[2];
                    array[0] = wasteID;
                    array[1] = exStation;
                    array[2] = paycard;
                    array[3] = lastmoney;
                    array[4] = intime;
                    array[5] = innetid;
                    array[6] = instationid;
                    array[7] = origin;
                    array[8] = outtime;
                    array[9] = outnetid;
                    array[10] = outstationid;
                    array[11] = destination;
                    array[12] = weight;
                    array[13] = overlimit;
                    array[14] = platecolor;
                    array[15] = vehicleplate;
                    array[16] = vehiclemodel;
                    array[17] = province;
                    int truth = traffic_datadao.importFile(array);
                    if(truth==0){
                        System.out.println(line);
                    }
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return "success";
    }
}
