package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.LoginDao;
import com.zkb.ltk.model.User;
import com.zkb.ltk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ashley_wsm on 2017/3/6.
 */
@Service
public class LoginServiceImpl implements LoginService{
    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    LoginDao loginDao;


    public User LoginAsUser(String id,String password){
        System.out.print("23333");
        User user = loginDao.get(id);
        if(null!=user&&user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }
}
