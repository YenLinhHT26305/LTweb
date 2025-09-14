package Service.impl;

import java.util.List;

import Dao.CategoryDao;
import Dao.impl.CategoryDaoImpl;
import Entity.Category;
import Service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao cateDao = new CategoryDaoImpl();

	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public List<Category> findByName(String catename) {
		return cateDao.findByName(catename);
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);
	}

	@Override
	public void update(Category cate) {
		cateDao.update(cate);
	}

	@Override
	public void insert(Category cate) {
		cateDao.insert(cate);
	}

	@Override
	public List<Category> findByUserId(int userId) {
		return cateDao.findByUserId(userId);
	}
}
