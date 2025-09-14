package Dao;

import java.util.List;
import Entity.Category;

public interface CategoryDao {

	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> findAll();

	List<Category> findByName(String catename);

	Category findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category cate);

	void insert(Category cate);
	
	public List<Category> findByUserId(int userId);
}
