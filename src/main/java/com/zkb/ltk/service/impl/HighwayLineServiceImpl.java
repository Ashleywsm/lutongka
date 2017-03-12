package com.zkb.ltk.service.impl;

import com.zkb.ltk.dao.HighwayLineDao;
import com.zkb.ltk.model.traffic_data;
import com.zkb.ltk.service.HighwayLineService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
@Service
public class HighwayLineServiceImpl implements HighwayLineService{
    HighwayLineDao highwayLineDao;

    public HighwayLineDao getHighwayLineDao() {
        return highwayLineDao;
    }

    public void setHighwayLineDao(HighwayLineDao highwayLineDao) {
        this.highwayLineDao = highwayLineDao;
    }
    public String getHighwayLine(Date date){
        List<traffic_data> traffic_datas = highwayLineDao.getHighwayLineByDate(date);
        Iterator<traffic_data> it = traffic_datas.iterator();
        while (it.hasNext()){
            traffic_data trafficData = it.next();

        }
        return null;
    }
}
