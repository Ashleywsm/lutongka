package com.zkb.ltk.service;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/8.
 */
public interface UserDistributeService {
    HashMap<String,Integer> getUserNumber(String dateStart, String dateEnd);
    HashMap<String,Integer> getUserConsumeNumber(String dateStart,String dateEnd);
    HashMap<String,Double> getUserConsumeMoney(String dateStart,String dateEnd);
}
