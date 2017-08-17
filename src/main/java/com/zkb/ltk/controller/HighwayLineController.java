package com.zkb.ltk.controller;

import com.zkb.ltk.service.HighwayLineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/7.
 */
@Controller
@RequestMapping("/login/workspace/highwayline")
public class HighwayLineController {
    @Resource(name="highwayLineService")
    HighwayLineService highwayLineService;

    @RequestMapping("/two")
    @ResponseBody
    public String getHighwayLine(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String date = request.getParameter("date");
        String province = request.getParameter("province");
        HashMap<Integer,HashMap<String,Integer>> odmax = new HashMap<Integer, HashMap<String, Integer>>();
        odmax = highwayLineService.getHighwayLine(province,date);
        StringBuffer buffer = new StringBuffer("[\r\n");
        for(Integer key:odmax.keySet()){
            HashMap<String,Integer> roadnumber = odmax.get(key);
            buffer.append("[\r\n");
            for(String link:roadnumber.keySet()){
                Integer num = roadnumber.get(link);
                String number = String.valueOf(num);
                String poi1 = link.substring(0,link.indexOf("|"));
                String poi2 = link.substring(link.indexOf("|")+1);
                buffer.append("{o:"+poi1+",d:"+poi2+",flow:"+number+"},\r\n");
            }
            buffer.append("],\r\n");
        }
        buffer.append("]\r\n");
        return buffer.toString();
        //return "2323232";
    }

    @RequestMapping("/one")
    @ResponseBody
    public String getLulian(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String province = request.getParameter("province");
        HashMap<String,String> lulian = highwayLineService.getLulianLngLat(province);
        StringBuffer buffer = new StringBuffer("[\r\n");
        for(String key:lulian.keySet()){
            String s = lulian.get(key);
            String lng = s.substring(0,s.indexOf("|"));
            String lat = s.substring(s.indexOf("|")+1);
            buffer.append("{id:"+key+",name:\""+key+"\",centerX:"+lng+",centerY:"+lat+"},\r\n");
        }
        buffer.append("]\r\n");
        return buffer.toString();
    }
}
