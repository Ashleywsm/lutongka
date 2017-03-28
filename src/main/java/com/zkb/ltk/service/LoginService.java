package com.zkb.ltk.service;

import com.zkb.ltk.model.user;

/**
 * Created by ashley_wsm on 2017/3/6.
 */
public interface LoginService {
    user LoginAsUser(String id, String password);
}
