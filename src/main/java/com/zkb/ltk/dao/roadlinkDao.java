package com.zkb.ltk.dao;

import com.zkb.ltk.model.roadlink;

import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public interface roadlinkDao extends Dao <roadlink,String> {
    HashMap<String,String> getLulianLngLatByprovince(String province);
}
