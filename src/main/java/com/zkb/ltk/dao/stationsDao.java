package com.zkb.ltk.dao;

import com.zkb.ltk.model.stations;

import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public interface stationsDao extends Dao<stations,String> {
    HashMap<String,String> getStationLngLatByProvince(String province);
}
