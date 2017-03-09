package com.zkb.ltk.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author mantantan-a handsome boy
 *
 * @param <T>
 * @param <PK>
 */
public interface Dao<T, PK> {

    /**
     * 按照主键取实体
     * 
     * @param 主键
     * @return 实体
     */
    public abstract T get(PK id);

    /**
     * 根据PK列表返回实体类列表
     * 
     * @param List
     *        <主键>
     * @return List<实体>
     */
    public abstract List<T> getList(List<PK> ids);

    /**
     * 获得所有的实体类
     * 
     * @return List<实体>
     */
    public abstract List<T> getAll();
    
    /**
     * 获取所有的实体，并根据字段进行排序（字段为实体字段，不能为表字段）
     * @param asc
     * @param orderProperName
     * @return
     */
    public List<T> getAll(Boolean asc, String orderProperName);
    
    /**
     * 对实体进行分页获取（获取整表的分页）
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<T> page(int pageIndex, int pageSize);
    
    /**
     * 对特定字段排序后的实体进行分页获取（获取整表的分页，字段为实体对象）
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<T> page(int pageIndex, int pageSize, Boolean asc, String orderProperName);
    
    /**
     * 保存或更新实体，cache=true清空本地线程栈缓存后更新
     * @param entity
     * @param cache
     */
    public void saveOrUpdate(T entity, Boolean... cache);
    
    /**
     * 统计本表的记录数
     * @return
     */
    public int Count();
    
    /**
     * 删除实体
     * @param entity
     */
    public void delete(T entity);
    
    /**
     * 删除实体列表
     * @param entities
     */
    public void deleteAll(Collection<T> entities);
    
    /**
     * 根据实体Id删除实体
     * @param id
     */
    public void deleteByKey(PK id);
    
    /**
     * params 的key需要是entity的域
     * @param params
     * @param indexPage
     * @param pageSize
     * @param asc
     * @param orderProperName
     * @param groupName
     * @param cache true使用缓存
     * @return

     */
    public List<T> getPageByCriteria(Map<String, Object> params, int indexPage, int pageSize,
                                     Boolean asc, String orderProperName, List<String> groupName, Boolean... cache);
    public List<T> getCountByCriteria(Map<String, Object> params, List<String> groupName);
    /**
     * 获取实体类列表
     * @param params
     * @param cache
     * @return
     */
    public List<T> getListByCriteria(Map<String, Object> params, Boolean... cache);
    /**
     * 调用带参数的存储过程
     * @param procedureName
     * @param args
     */
    public void Procedure(String procedureName, String[] args);
    /**
     * 调用不带参数的存储过程
     * @param procedureName
     */
    public void Procedure(String procedureName);
    
    public String  addAndCheckOrderParams(String sql, String orderProperName, boolean isSql, boolean desc);

}
