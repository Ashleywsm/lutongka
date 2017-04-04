package com.zkb.ltk.dao.impl;

import com.zkb.ltk.dao.pathDao;
import com.zkb.ltk.model.shortest_paths;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ashley_wsm on 2017/3/28.
 */
public class pathDaoImpl extends DaoImpl<shortest_paths,String> implements pathDao {
    public HashMap<String,String> getAllPath(){
        HashMap<String,String> shortestPath = new HashMap<String, String>();
        String hql = "from shortest_paths";
        List<shortest_paths> paths = super.hqlGetList(hql);
        System.out.println("paths:"+paths.size());
        for(int i=0;i<paths.size();i++){
            shortest_paths shortpath = paths.get(i);
            String origin = shortpath.getOrigin();
            String destination = shortpath.getDestination();
            String path = shortpath.getPath();
            shortestPath.put(origin+"|"+destination,path);
        }
        return shortestPath;
    }
}
