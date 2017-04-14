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
        for(int i=0;i<paths.size();i++){
            shortest_paths shortpath = paths.get(i);
            String id =shortpath.getId();
            String path = shortpath.getPath();
            shortestPath.put(id,path);
        }
        return shortestPath;
    }
    public HashMap<String,String> getPathByProvinceID(String province){
        HashMap<String,String> shortestPath = new HashMap<String, String>();
        String hql = "from shortest_paths where province = ?";
        Object[] values = new Object[1];
        values[0] = province;
        List<shortest_paths> paths = super.hqlGetList(hql,values);
        for(int i=0;i<paths.size();i++){
            shortest_paths shortpath = paths.get(i);
            String path = shortpath.getPath();
            String id = shortpath.getId();
            shortestPath.put(id,path);
        }
        return shortestPath;
    }
}
