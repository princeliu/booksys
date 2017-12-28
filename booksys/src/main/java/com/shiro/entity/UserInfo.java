/**
 * 
 */
package com.shiro.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 2017年7月13日 下午5:06:21
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements java.io.Serializable {

    private static final long serialVersionUID = 40482158759345958L;
    private String id;
    private String userName;
    private String account;
    private String password;
    private Integer age;
    private Integer sex;
    private List<Role> roles;

    /** default constructor */
    public UserInfo() {
    }
    
    

	/**
	 * @param userName
	 * @param password
	 */
	public UserInfo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	/** minimal constructor */
    public UserInfo(String id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;

    }

    /** full constructor */
    public UserInfo(String id, String account, String password,
            String userName, Integer age, Integer sex, List<Role> roles) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.age = age;
        this.sex = sex;
        this.roles = roles;
    }

    // Property accessors
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "account", nullable = false, length = 36)
    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "password", nullable = false, length = 18)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_name", length = 225)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "sex")
    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public List<String> getRoleName() {
        List<Role> roles = getRoles();
        List<String> list = new ArrayList<String>();
        for (Role role : roles) {
            list.add(role.getRoleName());
        }

        return list;
    }

}