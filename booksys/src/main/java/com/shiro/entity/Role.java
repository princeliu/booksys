/**
 * 
 */
package com.shiro.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 2017年7月13日 下午5:07:10
 */
@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

    private static final long serialVersionUID = 6439880715734914582L;
    private String id;
    private String roleName;
    private String parentId;
    private String describe;
    private List<UserInfo> userInfos;
    private List<Authority> authorities;

    // Constructors

    /** default constructor */
    public Role() {
    }

    /** minimal constructor */
    public Role(String id) {
        this.id = id;
    }

    /** full constructor */
    public Role(String id, String roleName, String parentId, String describe,
                List<UserInfo> userInfos, List<Authority> authorities) {
        this.id = id;
        this.roleName = roleName;
        this.parentId = parentId;
        this.describe = describe;
        this.userInfos = userInfos;
        this.authorities = authorities;
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

    @Column(name = "role_name", length = 225)
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "parent_id", length = 26)
    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(name = "role_describe", length = 225)
    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    @ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
    @JoinTable(name = "role_authority",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Transient
    public List<String> getAuthorityName() {
        List<String> list = new ArrayList<String>();
        List<Authority> authorities = getAuthorities();
        for (Authority auth : authorities) {
            list.add(auth.getAuthName());
        }

        return list;
    }

}