package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.Dao;
import com.zkb.ltk.dao.LoginDao;
import com.zkb.ltk.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ashley_wsm on 2017/3/7.
 */
//@Repository("LoginDao")
public class LoginDaoImpl extends DaoImpl <User,String> implements LoginDao  {
    /*@Autowired
    private SessionFactory sessionFactory;
    public User getUserById(String id){
        User user = (User) sessionFactory.getCurrentSession().get(User.class,id);
        return  user;
    }*/


}
