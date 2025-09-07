package Service.impl;

import java.util.List;
import Dao.CategoryDao;
import Dao.impl.CategoryDaoImpl;
import Model.CategoryModel;
import Service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	public CategoryDao cateDao = new CategoryDaoImpl();

	@Override
	public List<CategoryModel> findAll() {
		return cateDao.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		return cateDao.findById(id);
	}

	@Override
	public void insert(CategoryModel cate) {
		cateDao.insert(cate);
	}

	@Override
	public void update(CategoryModel cate) {
		CategoryModel category = new CategoryModel();
		category = cateDao.findById(cate.getCategoryid());
		if (category != null) {
			cateDao.update(cate);
		}
	}

	@Override
	public void delete(int id) {
		CategoryModel category = new CategoryModel();
		category = cateDao.findById(id);
		if (category != null) {
			cateDao.delete(id);;
		}
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		return cateDao.findByName(keyword);
	}

}
