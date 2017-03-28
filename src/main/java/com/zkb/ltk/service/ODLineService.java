package com.zkb.ltk.service;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public interface ODLineService {
    HashMap<String,String> getODLine (String province);
    HashMap<String,String> getStationLngLat(String province);
}
