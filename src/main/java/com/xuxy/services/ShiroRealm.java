package com.xuxy.services;

import com.xuxy.entities.User;
import com.xuxy.mappers.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/30  15:22
 * @Description: shiro的身份认证类
 */
//注入shiro认证类
@Named
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;//注入mapper

    /**
     * 权限认证方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //首先获取到登录传过来的用户名和密码
        UsernamePasswordToken token =
                (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        //通过用户名去查询当前的用户信息  用户名是唯一的
        User user = userMapper.findByName(userName);
        //判断是否可登录
        if(user !=null){
            //判断状态是否为正常
            if(User.STATE_DISABLE.equals(user.getState())){
                throw new UnknownAccountException("账号已经被禁用");
            }
            return new SimpleAuthenticationInfo(user,user.getPassword(),user.getName());
        }
        throw new UnknownAccountException("账号或密码错误");
    }
}
