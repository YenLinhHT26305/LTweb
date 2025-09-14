package Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "Users")
@NamedQuery(name="Users.findAll", query="select u from Users u")
public class Users implements Serializable {

    private static final long serialVersionUID = 424520984206432379L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "avatar", length = 500)
    private String avatar;

    @Column(name = "fullname", length = 200)
    private String fullname;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "roleid", nullable = false)
    private int roleId;

    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    // ✅ Quan hệ 1-N: 1 user có nhiều category
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    public Users() {}

    public Users(String username, String password, String avatar, String fullname,
                 String email, String phone, int roleId, LocalDateTime createDate) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
