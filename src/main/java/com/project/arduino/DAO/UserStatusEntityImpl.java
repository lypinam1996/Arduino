package com.project.arduino.DAO;

import com.project.arduino.models.UserStatusEntity;
import com.project.arduino.models.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserStatusDAO")
public class UserStatusEntityImpl extends AbstractDAO<Integer,UserStatusEntity> implements UserStatusDAO {
    @Override
    public UserStatusEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(UserStatusEntity.class);
        criteria.add(Restrictions.eq("id", id));
        return (UserStatusEntity) criteria.uniqueResult();
    }

    @Override
    public UserStatusEntity FindByLogin(String title) {
        Criteria criteria = getSession().createCriteria(UserStatusEntity.class);
        criteria.add(Restrictions.eq("title", title));
        return (UserStatusEntity) criteria.uniqueResult();
    }

    @Override
    public List<UserStatusEntity> findAllStatuses() {
        Criteria criteria = getSession().createCriteria(UserStatusEntity.class);
        return (List<UserStatusEntity>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
