package Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DBConnection;
import Dao.CategoryDao;
import Model.CategoryModel;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> list = new ArrayList<>();
		String sql = "Select * from categories";
		try (Connection conn = new DBConnection().getConnectionW();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(mapCate(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}  

	@Override
	public CategoryModel findById(int id) {
		String sql = "Select * from categories where categoryid=?";
		try (Connection conn = new DBConnection().getConnectionW(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return mapCate(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(CategoryModel cate) {
		String sql = "Insert into categories (categoryname, images, status) VALUES(?,?,?)";
		try (Connection conn = new DBConnection().getConnectionW(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, cate.getCategoryname());
			ps.setString(2, cate.getImages());
			ps.setInt(3, cate.getStatus());
			ps.executeUpdate();
			System.out.println("Insert thành công");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CategoryModel cate) {
		String sql = "Update categories set categoryname=?,images=?,status=? WHERE categoryid=?";
		try (Connection conn = new DBConnection().getConnectionW(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, cate.getCategoryname());
			ps.setString(2, cate.getImages());
			ps.setInt(3, cate.getStatus());
			ps.setInt(4, cate.getCategoryid());
			ps.executeUpdate();
			System.out.println("Update thành công");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "Delete from categories WHERE categoryid=?";
		try (Connection conn = new DBConnection().getConnectionW(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Delete thành công");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CategoryModel> findByName(String keyword) {
		String sql = "Select * from categories where categoryname like ?";
		List<CategoryModel> list = new ArrayList<>();
		try (Connection conn = new DBConnection().getConnectionW(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%"+keyword+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(mapCate(rs));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private CategoryModel mapCate(ResultSet rs) throws SQLException {
		CategoryModel cate = new CategoryModel();
		cate.setCategoryid(rs.getInt("categoryid"));
		cate.setCategoryname(rs.getString("categoryname"));
		cate.setImages(rs.getString("images"));
		cate.setStatus(rs.getInt("status"));
		return cate;
	}

}
