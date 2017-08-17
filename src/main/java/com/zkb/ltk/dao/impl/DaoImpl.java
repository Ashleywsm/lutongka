package com.zkb.ltk.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zkb.ltk.model.shortest_paths;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zkb.ltk.common.annotation.EnableLog;
import com.zkb.ltk.common.customercontext.CustomerContextHolder;
import com.zkb.ltk.dao.Dao;
/**
 * 声明：
 * 本类是该系统持久化操作的最底层接口，其余的dao使用必须继承此类，无需xmL注入sessionFactory和logger。
 * 约定如下:
 * 	      如果需要添加接口到此类中，请遵循原则，涉及到sql语句、hql语句操作的请定义为protected，否则定义
 * 为public，public的需要在DAO接口声明。
 *
 *
 * @author manta
 *
 * @param <T>
 * @param <PK>
 */


@SuppressWarnings("unchecked")
public class DaoImpl<T, PK extends Serializable> implements Dao<T, PK> {

    /* private HibernateTransactionManager transactionManager; */
    private String         noSessionException        = "No Session found for current thread";
    private String         sessionClosed             = "Session is closed";
    private String  	   transactionCloseException = "This TransactionCoordinator has been closed";
    /* 缓存更新标志位 */
    private String 		   isClearCache = "1"; //更新之后需要清理缓存
    private String         notClearCache = "0"; //更新之后不需要清理缓存

    @EnableLog
    protected Logger logger;//日志输出,允许子类直接访问
    @Autowired
    private SessionFactory sessionFactory;//sessionFactory
    private Session        session;//session
    private Class<T>       entityClass;//泛型T

    public DaoImpl() {
        setEntityClass();//设置泛型T的类
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
    public void setEntityClass(){
        this.entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    protected Session getSession(Boolean...cache) {
        //清空关联缓存
        if(cache == null || cache.length == 0 || cache[0] == true){
            CustomerContextHolder.setCustomerType(isClearCache);
        }
        //不清空
        else if(cache.length > 0 && cache[0] == false){
            CustomerContextHolder.setCustomerType(notClearCache);
        }
        try {
            // 先尝试事务中获取会话
            session = this.sessionFactory.getCurrentSession();
            return session;
        } catch (Exception e) {

            if (e.getMessage().equals(noSessionException)
                    || e.getMessage().equals(this.sessionClosed)) {
                // 如果没有开启事务则开启一个新的会话
                session = this.sessionFactory.openSession();
                return session;
            } else {
                throw new RuntimeException("getSession报错:"+e.getMessage());
            }
        }
    }

    protected void refresh(T entity, Boolean...cache){
        getSession(cache).refresh(entity);
    }

    public T get(PK id) {
        return (T) getSession().get(entityClass, id);
    }

    public List<T> getList(List<PK> ids) {
        List<T> list = new ArrayList<T>();
        for (PK key : ids) {
            list.add(this.get(key));
        }
        return list;
    }

    public List<T> getAll() {
        Criteria criteria = getSession().createCriteria(entityClass);
        //criteria.setCacheable(true);设在缓存读取，没有则加入缓存
        return criteria.list();
    }

    public List<T> getAll(Boolean asc, String orderProperName) {
        Criteria criteria = getSession().createCriteria(entityClass);
        if (asc)
            criteria.addOrder(Order.asc(orderProperName));
        else
            criteria.addOrder(Order.desc(orderProperName));
        return criteria.list();
    }

    public List<T> page(int pageIndex, int pageSize) {
        int firstResultIndex = (pageIndex - 1) * pageSize;
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setCacheable(true);
        List<T> result = criteria.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
        return result;
    }

    public List<T> page(int pageIndex, int pageSize, Boolean asc, String orderProperName) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setCacheable(true);
        if (asc)
            criteria.addOrder(Order.asc(orderProperName));
        else
            criteria.addOrder(Order.desc(orderProperName));

        int firstResultIndex = (pageIndex - 1) * pageSize;
        return criteria.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }

    public PK save(T entity) {
        return (PK) getSession().save(entity);
    }

    public void saveOrUpdate(T entity, Boolean... cache) {
        //不清缓存
        if(cache != null && cache.length > 0 && cache[0] == false){
            getSession(false).saveOrUpdate(entity);
        }
        //清空本地线程栈缓存后更新
        else{
            getSession().saveOrUpdate(entity);
        }
    }


    public int Count() {
        String hql = "select count(*) from " + this.entityClass.getSimpleName();
        Query query = getSession().createQuery(hql);
        return ((Long) query.iterate().next()).intValue();
    }

    public void delete(T entity) {
        getSession().delete(entity);
        this.flush();
    }

    public void deleteAll(Collection<T> entities) {
        for (T entity : entities)
            this.delete(entity);
    }

    public void deleteByKey(PK id) {
        T entity = this.get(id);
        getSession().delete(entity);
    }

    public void flush() {
        getSession().flush();
    }

    /**
     * params 的key需要是entity的域名
     * @param params
     * @param indexPage
     * @param pageSize
     * @param asc
     * @param orderProperName
     * @param groupName
     * @param cache
     * @return
     */
    public List<T> getPageByCriteria(Map<String, Object> params, int indexPage, int pageSize,
                                     Boolean asc, String orderProperName, List<String> groupName, Boolean... cache) {

        Criteria criteria = getSession().createCriteria(this.entityClass);
        if(cache == null || cache.length == 0 || cache[0] == false){
            criteria.setCacheable(false);
        }else if(cache.length > 0 && cache[0] == true){
            criteria.setCacheable(true);
        }

        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                criteria.add(Restrictions.eq(key, params.get(key)));
            }
        }
        ProjectionList pList = Projections.projectionList();
        if (groupName != null && groupName.size() > 0) {
            for (String name : groupName)
                pList.add(Projections.groupProperty(name));
        }
        criteria.setProjection(pList);
        int firstResultIndex = (indexPage - 1) * pageSize;
        criteria.setFirstResult(firstResultIndex);
        criteria.setMaxResults(pageSize);
        if (asc) {
            criteria.addOrder(Order.asc(orderProperName));
        } else {
            criteria.addOrder(Order.desc(orderProperName));
        }
        return criteria.list();
    }

    public List<T> getCountByCriteria(Map<String, Object> params, List<String> groupName) {
        Criteria criteria = getSession().createCriteria(this.entityClass);
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                criteria.add(Restrictions.eq(key, params.get(key)));
            }
        }
        ProjectionList pList = Projections.projectionList();
        if (groupName != null && groupName.size() > 0) {
            for (String name : groupName) {
                pList.add(Projections.groupProperty(name));
                pList.add(Projections.rowCount(), name);
            }
            criteria.setProjection(pList);
        }
        return criteria.list();
    }

    public List<T> getListByCriteria(Map<String, Object> params,Boolean... cache) {
        // TODO Auto-generated method stub
        Criteria criteria = getSession().createCriteria(this.entityClass);
        if(cache == null || cache.length == 0 || cache[0] == false){
            criteria.setCacheable(false);
        }else if(cache.length > 0 && cache[0] == false){
            criteria.setCacheable(true);
        }
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                criteria.add(Restrictions.eq(key, params.get(key)));
            }
        }
        criteria.setFirstResult(0);
        criteria.setMaxResults(0);
        return criteria.list();
    }


    public void Procedure(String procedureName, String[] args) {
        String procedure = "{call " + procedureName + "(";
        int i = 0;
        if (args != null)
            while (i++ < args.length)
                procedure += ((i == args.length) ? "?)" : "?,");
        SQLQuery query = getSession().createSQLQuery(procedure);
        if (args != null)
            for (int j = 0; j < args.length; j++)
                query.setString(j, args[j]);
        query.executeUpdate();
    }

    public void Procedure(String procedureName) {
        String procedure = "{call " + procedureName + "()}";
        SQLQuery query = getSession().createSQLQuery(procedure);
        query.executeUpdate();
    }

    // 说明：一下为hql和sql操作，为了安全考虑，仅限制在子类中使用，如果子类需要特殊处理请单独声明方法调用下面方法
    /**
     * hql更新
     *
     * @param hql
     * @return
     */
    protected int hqlUpdate(String hql) {
        Query q = getSession().createQuery(hql);
        return q.executeUpdate();
    }

    /**
     * @param带参数hql更新
     * @param values
     * @return
     */
    protected int hqlUpdate(String hql, Object[] values) {;
        Query q = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            q.setParameter(i, values[i]);
        }
        return q.executeUpdate();
    }

    /**
     * hql找实体
     *
     * @paramhsql
     * @return List<实体>
     */
    protected List<T> hqlGetList(String hql) {
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    /**
     * hql获取实体List
     * 默认不使用缓存
     * @param hql
     * @return List<实体>
     */
    protected List<T> hqlGetList(String hql, Object[] values, Boolean... cache) {
        Query query = getSession().createQuery(hql);
        if(cache == null || cache.length == 0 || cache[0] == false){
            CustomerContextHolder.setCustomerType(isClearCache);
            query.setCacheable(false);
        }else if(cache.length > 0 && cache[0] == true){
            CustomerContextHolder.setCustomerType("0");
            query.setCacheable(true);
        }
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.list();
    }
    /**
     * 包含参数为数组类型的query查询
     * http://blessht.iteye.com/blog/1051421
     *
     * hql类型如：FROM Login login WHERE login.id in(:ids)
     * @paramquery
     * @parammap
     * @return
     */
    protected List<T> hqlGetList(String hql, Map<String, Object> map) {
        Query query = getSession().createQuery(hql);
        if (map != null || map.size() > 0) {
            Set<String> keySet = map.keySet();
            for (String string : keySet) {
                Object obj = map.get(string);
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if(obj instanceof Collection<?>){
                    query.setParameterList(string, (Collection<?>)obj);
                }else if(obj instanceof Object[]){
                    query.setParameterList(string, (Object[])obj);
                }else{
                    query.setParameter(string, obj);
                }
            }
        }
        return query.list();
    }
    /**
     * hql分页找实体们
     *
     * @param hql
     * @return List<实体>
     */
    protected List<T> hqlPage(String hql, int pageIndex, int pageSize) {
        int firstResultIndex = (pageIndex - 1) * pageSize;
        Query query = getSession().createQuery(hql);
        return query.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }

    /**
     * hql分页找实体们（带参）
     *
     * @param hql
     * @return List<实体>
     */
    protected List<T> hqlPage(String hql, int pageIndex, int pageSize, Object[] values) {
        int firstResultIndex = (pageIndex - 1) * pageSize;
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }

    /**
     * hql分页排序找实体们（带参）
     *
     * @param hql
     *
     * @param pageIndex
     * @param pageSize
     * @param desc
     *        bool变量，true为desc,false为asc
     * @param orderProperName
     *        排序的属性
     * @param values
     *        hql语句的参数，数组形式
     * @return
     */
    protected List<T> hqlPage(String hql, int pageIndex, int pageSize, Boolean desc,
                              String orderProperName, Object[] values) {
        int firstResultIndex = (pageIndex - 1) * pageSize;

        hql=addAndCheckOrderParams(hql, orderProperName, false, desc);

        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }

    /**
     * hql根据主键集合获取特定的数据行并进行分页和排序
     *
     * @param entityName
     *        实体名称
     * @param ids
     *        主键集合
     * @param pageIndex
     * @param pageSize
     * @param desc
     *        bool变量，true为desc,false为asc
     * @param orderProperName
     *        排序的属性
     * @return
     */
    protected List<T> hqlPage(String entityName, List<String> ids, int pageIndex, int pageSize,
                              Boolean desc, String orderProperName) {
        int firstResultIndex = (pageIndex - 1) * pageSize;
        if (ids == null || ids.size() == 0)
            return null;
        String hqlList = GetListString(ids);
        String hql = "from " + entityName + " where id in (" + hqlList + ")";
        hql=addAndCheckOrderParams(hql, orderProperName, false, desc);
        Query query = getSession().createQuery(hql);
        //query.setCacheable(true);
        return query.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }



    /**
     * hql查询数量
     *
     * @param hql
     * @return 数量
     */
    protected int hqlCount(String hql) {
        Query query = getSession().createQuery(hql);
        return ((Long) query.iterate().next()).intValue();
    }

    /**
     * hsql查询数量(带参数)
     *
     * @param hql
     * @return 数量
     */
    protected int hqlCount(String hql, Object[] values) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return ((Long) query.iterate().next()).intValue();
    }

    /**
     * sql 查找
     *
     * @param sql
     * @return
     */
    protected List<T> sqlGetList(String sql) {
        SQLQuery query = getSession().createSQLQuery(sql);
        //query.setCacheable(true);
        return query.addEntity(entityClass).list();
    }


    /**
     * 带参数sql 查找
     *
     * @param sql
     * @return
     */
    protected List<T> sqlGetList(String sql, Object[] values,Boolean... cachable) {
        SQLQuery query = getSession().createSQLQuery(sql);
        //query.setCacheable(true);
        if(cachable!=null&&cachable.length>0)
            query.setCacheable(cachable[0]);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.addEntity(entityClass).list();
    }

    protected List<T> sqlGetList(String sql, List<Object> values) {
        SQLQuery query = getSession().createSQLQuery(sql);
        //query.setCacheable(true);
        for (int i = 0; i < values.size(); i++) {
            query.setParameter(i, values.get(i));
        }
        return query.addEntity(entityClass).list();
    }

    /**
     * sql分页找实体们
     *
     * @param sql
     * @return List<实体>
     */
    protected List<T> sqlPage(String sql, int pageIndex, int pageSize) {
        int firstResultIndex = (pageIndex - 1) * pageSize;
        Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
        //query.setCacheable(true);
        return query.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }

    /**
     * sql分页找实体们（带参）
     * @param
     * @return List<实体>
     */
    protected List<T> sqlPage(String sql, int pageIndex, int pageSize, Object[] values) {
        int firstResultIndex = (pageIndex - 1) * pageSize;
        Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }

    protected List<T> sqlPage(String sql, int pageIndex, int pageSize, Boolean desc,
                              String orderProperName, Object[] values) {

        int firstResultIndex = (pageIndex - 1) * pageSize;
        sql=addAndCheckOrderParams(sql, orderProperName, true, desc);

        Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
        //query.setCacheable(false);
        if(values!=null){
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }

    /**
     * sql更新
     *
     * @param
     * @return
     */
    protected int sqlUpdate(String sql) {
        SQLQuery q = getSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    /**
     * @param
     * @param values
     * @return
     * 这个虽然写的是更新  但是增删改查都可以做 第一个参数是sql语句，第二个参数是参数。。。
     */
    protected int sqlUpdate(String sql, Object[] values) {
        SQLQuery q = getSession().createSQLQuery(sql);
        for (int i = 0; i < values.length; i++) {
            q.setParameter(i, values[i]);
        }
        q.setCacheMode(CacheMode.IGNORE);
        return q.executeUpdate();
    }

    /**
     * 获取单列投影查询结果
     *
     * @param例："select 列名 from 表名",不能用*，必须指定单列名
     * @return
     */
    protected List<String> GetShadowResult(String hql) {
        SQLQuery query = getSession().createSQLQuery(hql);
        List<String> theList = query.list();
        return theList;
    }

    /**
     * 获取单列投影查询结果
     *
     * @paramsql语句 例："select 列名 from 表名",不能用*，必须指定单列名
     * @param param
     *        参数数组
     * @return 某一列结果的集合
     */
    protected List<String> GetShadowResult(String hql, Object[] param) {
        SQLQuery q = getSession().createSQLQuery(hql);
        for (int i = 0; i < param.length; i++) {
            q.setString(i, param[i].toString());
        }
        return q.list();
    }

    /**
     * sql获取 标量结果（不带列名，带参数）
     * @param sql
     * @param values
     * @return
     */
    protected List<Object[]> sqlScalarResults(String sql, Object[] values) {
        SQLQuery q = getSession().createSQLQuery(sql);
        for (int i = 0; i < values.length; i++) {
            q.setParameter(i, values[i]);
        }

        return q.list();
    }


    /**
     * 分页sql获得结果,结果带列名(无参数)
     *
     * @param查询结果的列名们
     * @return List<Map<列名，列值>>
     */
    protected List<Map<String, String>> sqlScalarResultsByPage(String sql,
                                                               List<String> dataBaseColumnNames, List<String> outPutColumnNames, int pageIndex,
                                                               int pageSize) {
        SQLQuery q = getSession().createSQLQuery(sql);
        for (int i = 0; i < dataBaseColumnNames.size(); i++)
            q.addScalar(dataBaseColumnNames.get(i));
        int firstResultIndex = (pageIndex - 1) * pageSize;
        List<Object[]> data = q.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
        // 转换数据
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (Object[] r : data) {
            Map<String, String> row = new LinkedHashMap<String, String>();
            for (int i = 0; i < r.length; i++) {
                if (r[i] == null)
                    r[i] = "";
                row.put(outPutColumnNames.get(i), r[i].toString());
            }
            result.add(row);
        }
        return result;
    }

    /**
     * 分页sql获得标量结果，结果不带列名(带参数)
     *
     * @param sql
     * @param
     * @return List<行数据>
     */
    protected List<Object[]> sqlScalarResultsByPage(String sql, String[] ColumnNames,
                                                    Object[] values, int pageIndex, int pageSize) {
        SQLQuery q = getSession().createSQLQuery(sql);
        for (int i = 0; i < values.length; i++)
            q.setParameter(i, values[i]);
        for (int i = 0; i < ColumnNames.length; i++)
            q.addScalar(ColumnNames[i]);
        int firstResultIndex = (pageIndex - 1) * pageSize;
        return q.setFirstResult(firstResultIndex).setMaxResults(pageSize).list();
    }



    /**
     * 根据sql查询唯一结果（无参数）
     *
     * @param sql
     * @return
     */
    protected Object sqlUniqueResult(String sql) {
        Query query = getSession().createSQLQuery(sql);
        Object r = query.uniqueResult();
        return r;
    }

    /**
     * 根据sql查询唯一结果（有参数）
     *
     * @param sql
     * @return
     */
    protected Object sqlUniqueResult(String sql, Object[] values) {
        Query query = getSession().createSQLQuery(sql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        Object r = query.uniqueResult();
        return r;
    }



    /**
     * sql查询数量
     *
     * @param sql
     * @return 数量
     */
    protected int sqlCount(String sql) {
        Query query = getSession().createSQLQuery(sql);
        Object r = query.uniqueResult();
        return ((BigDecimal) r).intValue();
    }

    /**
     * sql查询数量(带参数)
     *
     * @param sql
     * @return 数量
     */
    protected int sqlCount(String sql, Object[] values) {
        Query query = getSession().createSQLQuery(sql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        Object r = query.uniqueResult();
        return ((BigDecimal) r).intValue();
    }

    /**
     * 添加排序和检查字段的合法性
     * @param sql
     * @param orderProperName
     * @param isSql
     * @param desc
     * @return
     */
    public String addAndCheckOrderParams(String sql,String orderProperName,boolean isSql,boolean desc){

        if(isSql){
            if(StringUtils.isNotBlank(orderProperName)){//严重排序字段
                orderProperName=orderProperName.trim();
                String[] orderArrays=orderProperName.split(",");
                Pattern pattern = Pattern.compile("^([a-zA-Z0-9]|_)+$");
                boolean flag0=true;
                for(String col:orderArrays){
                    Matcher matcher = pattern.matcher(col);
                    if(!matcher.matches()){
                        flag0=false;
                        break;
                    }
                }
                if(flag0){
                    sql += " order by  " + orderProperName;
                    sql += GetSortString(desc);
                }
            }
        }else{
            if(StringUtils.isNotBlank(orderProperName)){
                orderProperName=orderProperName.trim();
                String [] orderArry=orderProperName.split(",");
                Field[] fields = getEntityClass().getDeclaredFields();
                boolean flag0=true;
                for(String orderCol:orderArry){
                    boolean flag1=false;
                    for (Field field : fields) {
                        if(field.getName().equals(orderCol)){
                            flag1=true;
                            break;
                        }
                    }
                    if(!flag1){
                        flag0=false;
                        break;
                    }
                }

                if(flag0){
                    sql += " order by  ";
                    sql += GetMulSortString(orderArry,desc);
                }
            }
        }

        return sql;
    }

    private static String GetMulSortString(String [] orderArry,boolean desc){
        String sortString="";
        if(desc){//降序
            for(int i=0;i<orderArry.length;i++){
                if(i==0){
                    sortString+=orderArry[i]+" desc";
                }else{
                    sortString+=","+orderArry[i]+" desc";
                }
            }

        }else{//升序
            for(int i=0;i<orderArry.length;i++){
                if(i==0){
                    sortString+=orderArry[i]+" asc";
                }else{
                    sortString+=","+orderArry[i]+" asc";
                }
            }
        }
        return sortString;
    }
    /**
     * 将主键集合转化为in子句的查询条件，形式为A,B,C
     *
     * @param ids
     *        主键的集合
     * @return
     */
    private String GetListString(List<String> ids) {
        String list = "";
        for (int i = 0; i < ids.size(); i++) {
            list += ids.get(i) + ",";
        }
        list = list.substring(0, list.length() - 1);
        return list;
    }

    /**
     * 获取排序规则
     *
     * @param desc
     * @return
     */
    private String GetSortString(boolean desc) {
        if (desc)
            return " desc";
        else
            return " asc";
    }

    public HashMap<String,String> myTest(String hql, Object[] values){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query  = session.createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        ScrollableResults itemCursor=query.scroll();
        int count = 0;
        HashMap<String,String> shortestPath = new HashMap<String, String>();
        String path="";
        String id = "";
        shortest_paths shortpath=null;
        while(itemCursor.next()){
            shortpath =(shortest_paths)itemCursor.get(0);
            path = shortpath.getPath();
            id = shortpath.getId();
            shortestPath.put(id,path);

            if(++count % 100 == 0){
                session.flush();
                session.clear();
            }
            if(count>120000 && count%10000==0){
                System.out.print("2333333333");
            }
        }
        tx.commit();
        session.close();
        return shortestPath;
    }
}
