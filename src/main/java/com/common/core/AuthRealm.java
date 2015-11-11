package com.common.core;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * SysUser: xupeng
 * Date: 13-11-27
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class AuthRealm extends AuthorizingRealm {

//	private SysResourceMapper sysResourceMapper;
//	private SysRoleMapper sysRoleMapper;
//	private SysUserMapper sysUserMapper;

    /**
     * 授权
     * @param principals
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        SysUser sysUser = (SysUser) principals.fromRealm(getName()).iterator().next();
//		for (SysRole sysRole : sysUser.getSysRoleList()) {
//		    //基于Role的权限信息
//		    info.addRole(sysRole.getRoleName());//获得用户的角色
//		}
//        List<SysResource> resourceList = sysResourceMapper.findResourceByRoleId(sysUser.getSysRoleList());
//        for(SysResource resource:resourceList){
//            if(resource.getPermissionName()!=null && !resource.getPermissionName().equals(""))
//                info.addStringPermission(resource.getPermissionName());//获得角色的访问权限
//        }
        info.addStringPermission("common:click");
        return info;
	}

    /**
     * 验证
     * @param token
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		 UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//	        SysUser sysUser=new SysUser();
//	        try {
//	            String loginName=upToken.getUsername();
//	            sysUser = sysUserMapper.findUserByLoginName(loginName);
//                if (sysUser == null) {
//                    throw new UnknownAccountException("No account found for sysUser [" + upToken.getUsername() + "]");
//                } else if (sysUser.getStatus().equals(1)) {
//                    throw new DisabledAccountException();
//                }
//                List<SysRole> sysRole=sysRoleMapper.findRoleByUserId(sysUser.getId());
//                sysUser.setSysRoleList(sysRole);
//	        }catch (Exception ex){
//	            ex.printStackTrace();
//	        }
        SimpleAuthenticationInfo authenticationInfo =new SimpleAuthenticationInfo("admin","111111", getName());
        return authenticationInfo;
	}

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

//    @Override
//    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
//        super.clearCachedAuthenticationInfo(principals);
//    }
//
//    public SysResourceMapper getSysResourceMapper() {
//        return sysResourceMapper;
//    }
//
//    public void setSysResourceMapper(SysResourceMapper sysResourceMapper) {
//        this.sysResourceMapper = sysResourceMapper;
//    }
//
//    public SysRoleMapper getSysRoleMapper() {
//        return sysRoleMapper;
//    }
//
//    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
//        this.sysRoleMapper = sysRoleMapper;
//    }
//
//    public SysUserMapper getSysUserMapper() {
//        return sysUserMapper;
//    }
//
//    public void setSysUserMapper(SysUserMapper sysUserMapper) {
//        this.sysUserMapper = sysUserMapper;
//    }
}
