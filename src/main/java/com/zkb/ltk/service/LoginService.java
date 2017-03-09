package com.zkb.ltk.service;

import com.zkb.ltk.model.User;

/**
 * Created by ashley_wsm on 2017/3/6.
 */
public interface LoginService {
    User LoginAsUser(String id,String password);
}
