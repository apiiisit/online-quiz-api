package th.co.cdgs.user.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
    @SequenceGenerator(name = "userRoleSequence", sequenceName = "user_role_id_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "userRoleSequence")
    @Column(name = "user_role_id")
	private Integer userRoleId;
	
	@Column(name = "user_role_name", length = 1)
	private String userRoleName;
	
	@Column(name = "user_role_description")
	private String userRoleDescription;
	

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getUserRoleDescription() {
		return userRoleDescription;
	}

	public void setUserRoleDescription(String userRoleDescription) {
		this.userRoleDescription = userRoleDescription;
	}
}
