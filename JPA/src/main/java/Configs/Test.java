package Configs;

import Entity.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		//entity
		Roles cate = new Roles();
		cate.setRoleName("admin");
		try {
			trans.begin();
			//CRUD
			enma.persist(cate); //add dl vao entity
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}
