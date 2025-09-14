package Dao.impl;

import java.util.List;
import jakarta.persistence.Query;
import Configs.JPAConfig;
import Dao.CategoryDao;
import Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CategoryDaoImpl implements CategoryDao {
	@Override
	public void insert(Category cate) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// CRUD
			enma.persist(cate);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category cate) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// CRUD
			enma.merge(cate);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// CRUD
			Category category = enma.find(Category.class, cateid);
			if (category != null) {
				enma.remove(category);
			} else {
				throw new Exception("Khong tim thay!");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int cateid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class, cateid);
		return category;
	}

	@Override
	public List<Category> findByName(String catename) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select c from Category c where c.categoryName like :catename";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename", "%" + catename + "%");
		return query.getResultList();
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select count(c) from Category c";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Category> findByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.user.id = :userId",
					Category.class);
			query.setParameter("userId", userId);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

}
