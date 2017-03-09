package com.zkb.ltk.service.impl;


import com.zkb.ltk.service.testService;
import com.zkb.ltk.dao.testDao;
/**
 * Created by zkb on 2017/2/26.
 */
public class testServiceImpl implements testService {
    testDao testDao;
    public com.zkb.ltk.dao.testDao getTestDao() {
        return testDao;
    }

    public void setTestDao(com.zkb.ltk.dao.testDao testDao) {
        this.testDao = testDao;
    }

    public String getDataById(String id) {
        return testDao.get(id).getUser_id();
    }
    //依赖注入
}
