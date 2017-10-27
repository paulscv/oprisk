package scv.paul.opriskback.dao.impl;

import scv.paul.opriskback.util.DataAccessUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import scv.paul.opriskback.dao.IGenericDao;
import scv.paul.opriskback.entity.AGenericOpriskEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


@Transactional
@Repository
public abstract class GenericDaoImpl<T extends AGenericOpriskEntity, PK extends Serializable> implements IGenericDao<T,PK> {

    @Autowired
    protected DataAccessUtil dataAccessUtil;
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<? extends T> daoType;

    /**
     * By defining this class as abstract, we prevent Spring from creating
     * instance of this class If not defined as abstract,
     * getClass().getGenericSuperClass() would return Object. There would be
     * exception because Object class does not hav a constructor with parameters.
     */

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    public GenericDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Class<? extends T> getDaoType() {
        return daoType;
    }

    @Override
    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public DataAccessUtil getDataAccessUtil() {
        return dataAccessUtil;
    }

    @Autowired
    public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
        this.dataAccessUtil = dataAccessUtil;
    }

    @Override
    public T create(T entity)  {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public T update(T entity)  {
        getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public T update(PK id, Map<String, Object> fieldValueMap) {
        T entity = get(id);
        dataAccessUtil.setNewValuesToBean(entity, fieldValueMap);
        return update(entity);
    }


    @Override
    public T delete(T entity) {
        getCurrentSession().delete(entity);
        //DRS session.flush();
        return entity;
    }

    @Override
    public T get(PK id)  {
        return getCurrentSession().get(daoType, id);
    }


    @Override
    public List<T> getAll()  {
        return dataAccessUtil.getQueryOfEntity((Class<T>) daoType).getResultList();
    }

}
