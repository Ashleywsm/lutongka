package com.zkb.ltk.service;

import java.util.HashMap;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public interface HighwayLineService {
    HashMap<Integer,HashMap<String,Integer>> getHighwayLine(String province, String date);
    HashMap<String,String> getLulianLngLat(String province);
}
