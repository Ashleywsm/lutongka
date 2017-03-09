package com.zkb.ltk.service;

import java.util.Date;

/**
 * Created by ashley_wsm on 2017/3/8.
 */
public interface UserDistributeService {
    String getUserNumber(Date dateStart, Date dateEnd);
    String getUserConsumeNumber(Date dateStart,Date dateEnd);
    String getUserConsumeMoney(Date dateStart,Date dateEnd);
}
