package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.userDao;
import com.zkb.ltk.model.user;
import com.zkb.ltk.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * Created by ashley_wsm on 2017/3/6.
 */
@Service
public class LoginServiceImpl implements LoginService{

    public userDao getUserdao() {
        return userdao;
    }

    public void setUserdao(userDao userdao) {
        this.userdao = userdao;
    }

    userDao userdao;


    public user LoginAsUser(String id, String password){
        String pass = userdao.getUserByID(id);
        System.out.println(pass);


        return null;
    }
}
