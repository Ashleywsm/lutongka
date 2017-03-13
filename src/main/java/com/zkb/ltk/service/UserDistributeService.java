package com.zkb.ltk.service;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/8.
 */
public interface UserDistributeService {
    HashMap<String,Integer> getUserNumber(Date dateStart, Date dateEnd);
    HashMap<String,Integer> getUserConsumeNumber(Date dateStart,Date dateEnd);
    HashMap<String,Double> getUserConsumeMoney(Date dateStart,Date dateEnd);
}
