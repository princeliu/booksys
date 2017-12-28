/**
 * 
 */
package com.shiro.service;

import com.shiro.entity.UserInfo;

/**
 * @date 2017年7月13日 下午5:13:32
 */
public interface UserInfoService {
	public UserInfo userLogin(String account, String pwd);
}
