/**
 * 
 */
package com.shiro.dao.impl;

import com.common.dao.impl.BaseDaoHibernate4;
import com.shiro.dao.UserInfoDao;
import com.shiro.entity.UserInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl extends BaseDaoHibernate4<UserInfo> implements UserInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return session
     */
    public Session getSession(){
        Session session = this.sessionFactory.getCurrentSession();
        return session;
    }


    @Override
    public UserInfo findAccount(String account) {
        String hql = "from UserInfo where 1 = 1 and account = ?";
        Query query = this.getSession().createQuery(hql);
        query.setParameter(0, account);
        UserInfo userInfo = (UserInfo) query.uniqueResult();
        return userInfo;
    }

}
