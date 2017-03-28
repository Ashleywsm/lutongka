package com.zkb.ltk.dao;

import com.zkb.ltk.model.user;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public interface userDao extends Dao<user,String>{
    String getUserByID (String userid);
}
