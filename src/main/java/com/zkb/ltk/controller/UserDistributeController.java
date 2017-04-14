package com.zkb.ltk.controller;

import com.zkb.ltk.service.UserDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/7.
 */
@Controller
@RequestMapping("/login/workspace")
public class UserDistributeController {
    @Resource(name = "userDistributeService")
    UserDistributeService userDistributeService;

    @RequestMapping("/user")
    public String getUserDistribute(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String dateStart = request.getParameter("date_start");
        String dateEnd = request.getParameter("date_end");
        String[] array = new String[11];
        array[0] = "山东省";array[1] = "江苏省";array[2] = "广东省";array[3] = "河北省";array[4] = "浙江省";
        array[5] = "北京市";array[6] = "上海市";array[7] = "天津市";array[8] = "安徽省";array[9] = "湖北省";
        array[10] = "其他省";
        String string = userDistributeService.getUser(array,dateStart,dateEnd);

        return string;
    }


}
