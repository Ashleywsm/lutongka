package com.zkb.ltk.dao;

import com.zkb.ltk.model.comparison;

import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public interface comparisonDao extends Dao <comparison,String>{
    HashMap<String,String> getComparisonByprovinceid(String provinceid);
}
