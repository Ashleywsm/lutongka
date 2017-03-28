package com.zkb.ltk.dao;

import com.zkb.ltk.model.shortest_paths;

import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public interface pathDao extends Dao<shortest_paths,String> {
    HashMap<String,String> getAllPath();
}
