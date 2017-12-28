/**
 * 
 */
package com.shiro.realm;

import com.shiro.entity.Role;
import com.shiro.entity.UserInfo;
import com.shiro.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ShiroDbRealm extends AuthorizingRealm {


    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;

    public static final String SESSIOIN_USER_KEY = "userInfo";

    /**
     * 授权查询回调函数，进行鉴权但缓存中无用户的授权信息时调用，负责在应用程序中决定用户访问控制的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录时输入的帐号
        //String account = (String) principalCollection.fromRealm(getName()).iterator().next();
        // 从session中获取用户
        // 注意：该 session 并不是 客户端 session，而是 Shiro 的session
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSIOIN_USER_KEY);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 保存角色
        info.addRoles(userInfo.getRoleName());

        List<String> permis = new ArrayList<String>();
        List<Role> roles = userInfo.getRoles();
        for (Role role : roles) {
            List<String> auths = role.getAuthorityName();
            auths.removeAll(permis);// 权限去重
            permis.addAll(auths);
        }
        // 保存权限
        info.addStringPermissions(permis);

        return info;
    }

    /**
     * 认证回调函数，登录信息和用户信息验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        // 把 token 转成 user
        UserInfo userInfo = tokenToUser((UsernamePasswordToken)authToken);
        // 验证用户是否可以登录
        UserInfo user = userInfoService.userLogin(userInfo.getAccount(), userInfo.getPassword());
        if (user == null)
            return null;// 找不到数据
        // 设置 session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(ShiroDbRealm.SESSIOIN_USER_KEY, user);
        // 当前 Realm 的 name
        String realmName = this.getName();
        // 登录的主要信息：可以是一个实体类对象，但实体类的对象一定是根据 token 的 username 查询得到的
        // Object principal = user.getAccount();
        Object principal = authToken.getPrincipal();
        ByteSource credentialsSalt = ByteSource.Util.bytes("test");
        return new SimpleAuthenticationInfo(principal, userInfo.getPassword(),credentialsSalt, realmName);
    }


    private UserInfo tokenToUser(UsernamePasswordToken authToken) {
        UserInfo user = new UserInfo();
        user.setAccount(authToken.getUsername());
        user.setPassword(String.valueOf(authToken.getPassword()));

        return user;
    }
}
