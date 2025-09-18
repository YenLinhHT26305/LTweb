package Dao.impl;

import java.util.List;

import Configs.JPAConfig;
import Dao.UserDao;
import Entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UserDaoImpl implements UserDao {

    @Override
    public List<Users> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM Users u";
            TypedQuery<Users> query = enma.createQuery(jpql, Users.class);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public Users findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.find(Users.class, id);
        } finally {
            enma.close();
        }
    }

    @Override
    public void insert(Users user) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(user);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Users findByUserName(String username) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM Users u WHERE u.username = :username";
            TypedQuery<Users> query = enma.createQuery(jpql, Users.class);
            query.setParameter("username", username);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            enma.close();
        }
    }

    @Override
    public Users findByEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM Users u WHERE u.email = :email";
            TypedQuery<Users> query = enma.createQuery(jpql, Users.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            enma.close();
        }
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Users user = enma.find(Users.class, userId);
            if (user != null) {
                user.setPassword(newPassword);
                enma.merge(user);
            }
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }


	@Override
	public void update(Users user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProfile(int userId, String fullname, String phone, String avatarPath) {
		 EntityManager enma = JPAConfig.getEntityManager();
		    EntityTransaction trans = enma.getTransaction();
		    try {
		        trans.begin();
		        Users user = enma.find(Users.class, userId);
		        if (user != null) {
		            user.setFullname(fullname);
		            user.setPhone(phone);
		            if (avatarPath != null && !avatarPath.isEmpty()) {
		                user.setAvatar("uploads/" + avatarPath);
		            }
		            enma.merge(user);
		        }
		        trans.commit();
		    } catch (Exception e) {
		        if (trans.isActive()) trans.rollback();
		        throw e;
		    } finally {
		        enma.close();
		    }		
	}

}
