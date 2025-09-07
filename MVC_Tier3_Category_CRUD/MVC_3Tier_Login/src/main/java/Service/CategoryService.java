package Service;

import java.util.List;

import Model.CategoryModel;

public interface CategoryService {
	List<CategoryModel> findAll();
	CategoryModel findById(int id);
	void insert(CategoryModel cate);
	void update(CategoryModel cate);
	void delete(int id);
	List<CategoryModel> findName (String keyword);
}
