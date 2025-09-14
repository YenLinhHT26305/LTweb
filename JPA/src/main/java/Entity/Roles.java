package Entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")

public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1157959252232900860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // SQL Server: IDENTITY
	@Column(name = "roleid")
	private int roleId;

	@Column(name = "rolename", nullable = false, unique = true, length = 100)
	private String roleName;

	public Roles() {}

	public Roles(String roleName) {
	        this.roleName = roleName;
	    }

	public int getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
