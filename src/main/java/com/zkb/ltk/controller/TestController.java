package com.zkb.ltk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zkb.ltk.service.testService;
/**
 * Created by zkb on 2017/2/26.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Resource(name = "testService")
    testService testService ;

    @RequestMapping("/controller/{id}")
    @ResponseBody
    public String getResourceInfo(@PathVariable(value="id")String id,
            HttpServletRequest request, HttpServletResponse response, HttpSession session){

        return testService.getDataById(id);
       // return id;

    }


}
