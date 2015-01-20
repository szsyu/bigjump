package com.ect.bigjump.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 用户实体类
 * 
 * @author shawn
 * @since  2014-12-01
 * @version 1.0
 */
@Entity
@Table(name = "BJ_USER")
public class User extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 5435260849730429342L;

	/**
	 * 用户名
	 */
	@Column(length = 64, nullable = false, unique = true)
	private String userName;

	/**
	 * First Name
	 */
	@Column(length = 64)
	private String firstName;

	/**
	 * Last Name
	 */
	@Column(length = 64)
	private String lastName;

	/**
	 * 密码
	 */
	@Column(length = 64)
	private String password;

	/**
	 * 是否可用
	 */
	@Column(length = 1)
	private String isActive;

	/**
	 * 邮件地址
	 */
	@Column(length = 100)
	private String email;

	/**
	 * Session Id
	 */
	@Column(length = 64)
	private String sessionId;

	/**
	 * 最后一次登陆时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDate;

	/**
	 * 用户所拥有的角色集合
	 */
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BJ_USER_ROLE", 
	           joinColumns = { @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false) }, 
	           inverseJoinColumns = { @JoinColumn(name = "roleId", referencedColumnName = "id", nullable = false) })
	private List<Role> roles;

	/**
	 * 外键字段:organizationId,对应organization的主键id,表示用户所在的组织/部门
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organizationId")
	private Organization organization;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
