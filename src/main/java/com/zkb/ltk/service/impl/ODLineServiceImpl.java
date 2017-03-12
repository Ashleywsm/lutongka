package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.ODlineDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.ODLineService;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public class ODLineServiceImpl implements ODLineService{
    ODlineDao odLineDao;

    public ODlineDao getOdLineDao() {
        return odLineDao;
    }

    public void setOdLineDao(ODlineDao odLineDao) {
        this.odLineDao = odLineDao;
    }
    public String getODLine(Date dateStart, Date dateEnd){
        List<traffic_data> traffic_datas = odLineDao.getTraffic_dataByDate(dateStart,dateEnd);
        Iterator<traffic_data> it = traffic_datas.iterator();

        while (it.hasNext()){
            traffic_data trafficData = it.next();

        }
        return null;
    }
}
