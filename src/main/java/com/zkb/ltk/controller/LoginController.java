package com.zkb.ltk.controller;

import com.zkb.ltk.model.user;
import com.zkb.ltk.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ashley_wsm on 2017/3/3.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource(name="loginService")
    LoginService service;

    /*@RequestMapping("/controller/{username}/{password}")
    public String loginAction(@PathVariable(value = "username")String username,
                              @PathVariable(value = "password") String password,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session){
        boolean success = false;

        user user = service.LoginAsUser(username,password);
        success = (user!=null);
        if(success){
            return "success";
        }else return "error";
    }*/

    @RequestMapping("/login_action")
    @ResponseBody
    public String loginAction(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        boolean success = false;

        user user = service.LoginAsUser(username,password);
        success = (user!=null);
        if(success){
            return "success";
        }else return "error";
    }

    @RequestMapping("/Workspace")
    public ModelAndView toIndex(HttpSession session, HttpServletRequest request){

        ModelAndView mv=new ModelAndView("workspace");
        return mv;

    }

}
