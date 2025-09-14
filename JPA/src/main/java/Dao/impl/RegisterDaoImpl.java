package Dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import Configs.JPAConfig;
import Dao.RegisterDao;
import Entity.Users;

public class RegisterDaoImpl implements RegisterDao {

	@Override
	public void insert(Users user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			if (user.getCreateDate() == null) {
				user.setCreateDate(LocalDateTime.now());
			}
			em.persist(user);
			tx.commit();
			System.out.println("Insert user thành công: " + user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Users u WHERE u.email = :email", Long.class);
			query.setParameter("email", email);
			Long count = query.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Users u WHERE u.username = :username",
					Long.class);
			query.setParameter("username", username);
			Long count = query.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Users u WHERE u.phone = :phone", Long.class);
			query.setParameter("phone", phone);
			Long count = query.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}
}
