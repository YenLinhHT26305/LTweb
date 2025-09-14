package Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "select c from Category c")

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6944610315666905610L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private int categoryId;

	@Column(name = "CategoryName", columnDefinition = "NVARCHAR(200) NOT NULL")
	private String categoryname;

	@Column(name = "Images", columnDefinition = "NVARCHAR(MAX)")
	private String images;

	@Column(name = "Status")
	private int status;

	// Quan hệ N-1: nhiều Category thuộc 1 User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	@OneToMany(mappedBy = "category")
	private List<Video> videos;

	public Category() {

	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setCategory(this);
		return video;
	}

	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setCategory(null);
		return video;
	}

}
