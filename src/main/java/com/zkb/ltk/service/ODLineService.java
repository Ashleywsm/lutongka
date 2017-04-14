package com.zkb.ltk.service;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by 陌上花开 on 2017/3/12.
 */
public interface ODLineService {
    String getODLine (String province,String date);
    String getPOIStationLngLat(String province);
}
