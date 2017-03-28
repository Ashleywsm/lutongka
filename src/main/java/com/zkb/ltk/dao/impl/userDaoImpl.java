package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.userDao;
import com.zkb.ltk.model.user;

import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public class userDaoImpl extends DaoImpl <user,String> implements userDao {
    public String getUserByID (String userid){
        String sql = "select * from user where user_id = ?";
        Object[] values = new Object[1];
        values[0] = userid;
        List<user> users= super.sqlGetList(sql,values);
        String pass = null;
        if(users.size()==1){
            pass = users.get(0).getPassword();
        }
        return pass;
    }
}
