/**
 * 
 */
package com.shiro.dao;

import com.shiro.entity.UserInfo;

/**
 * @date 2017年7月13日 下午5:09:30
 */
public interface UserInfoDao {
	UserInfo findAccount(String account);
}
