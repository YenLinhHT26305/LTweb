package Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PasswordResetToken")
public class PasswordResetToken implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5878336446865281867L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_time", nullable = false)
    private LocalDateTime expiryTime;

    // Constructors
    public PasswordResetToken() {}

    public PasswordResetToken(int userId, String token, LocalDateTime expiryTime) {
        this.userId = userId;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
