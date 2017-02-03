package mrs.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
public class User {
	
	/**
	 * ID
	 */
	@Id
	private String userId;
	
	/**
	 * パスワード
	 */
	private String password;
	
	/** 
	 * 名前
	 */
	private String firstName;

	/** 名字 */
	private String lastName;

	/**
	 * 役割名（ADMIN,USER)
	 */
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}
	
	
}
