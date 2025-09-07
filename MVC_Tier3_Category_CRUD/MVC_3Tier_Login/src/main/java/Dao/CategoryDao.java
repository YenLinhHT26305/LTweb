package Dao;

import java.util.List;

import Model.CategoryModel;

public interface CategoryDao {
	List<CategoryModel> findAll(); //lay all category
	CategoryModel findById(int id);
	void insert(CategoryModel cate);
	void update(CategoryModel cate);
	void delete(int id);
	List<CategoryModel> findByName(String keyword);
	
}
