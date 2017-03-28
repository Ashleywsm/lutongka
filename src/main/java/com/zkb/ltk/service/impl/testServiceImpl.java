package com.zkb.ltk.service.impl;


import com.zkb.ltk.service.testService;
import com.zkb.ltk.dao.testDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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


//        JSONArray aa = new JSONArray();
//        JSONObject b = new JSONObject();
//
//
//        b.put("o","29383948");
//        aa.add(b);
        return testDao.get(id).getUser_id();
    }
    //依赖注入
}
