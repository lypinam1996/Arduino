package com.project.arduino.DAO;

import com.project.arduino.models.LocksEntity;
import com.project.arduino.models.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("LocksDAO")
public class LocksDAOImpl extends AbstractDAO<Integer,LocksEntity> implements LocksDAO{
    @Override
    public LocksEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(LocksEntity.class);
        criteria.add(Restrictions.eq("idLock", id));
        return (LocksEntity) criteria.uniqueResult();
    }

    @Override
    public LocksEntity FindByTitle(String title) {
        Criteria criteria = getSession().createCriteria(LocksEntity.class);
        criteria.add(Restrictions.eq("title", title));
        return (LocksEntity) criteria.uniqueResult();
    }

    @Override
    public List<LocksEntity> findAllLocks() {
        Criteria criteria = getSession().createCriteria(LocksEntity.class);
        return (List<LocksEntity>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
    @Override
    public List<LocksEntity> findLocks(UsersEntity user){
        Query query = getSession().createQuery("from LocksEntity where usersByUser = :paramName");
        query.setParameter("paramName", user);
        List list = query.list();
        return list;
    }

    @Override
    public void saveLock(LocksEntity lock) {
        getSession().save(lock);
    }

    @Override
    public void deleteLock(int id_lock) {
        Query query = getSession().createSQLQuery("DELETE from locks where id_lock=:id_lock");
        query.setInteger("id_lock", id_lock);
        query.executeUpdate();
    }
}
