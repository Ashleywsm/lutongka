package com.zkb.ltk.controller;

import com.zkb.ltk.service.ODLineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ashley_wsm on 2017/3/7.
 */
@Controller
@RequestMapping("/login/workspace/odline")
public class ODController {
    @Resource(name="odLineService")
    ODLineService odLineService;

    @RequestMapping("/od")
    public String getODLine(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String date = request.getParameter("date");
        String province = request.getParameter("province");
        String od = odLineService.getODLine(province,date);

        return od;
    }
    @RequestMapping("/lnglat")
    public  String getPOIStation(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String province = request.getParameter("province");
        String lnglat = odLineService.getPOIStationLngLat(province);
        return lnglat;
    }

}
