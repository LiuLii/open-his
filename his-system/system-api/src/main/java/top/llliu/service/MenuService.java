package top.llliu.service;

import top.llliu.domain.Menu;
import top.llliu.domain.SimpleUser;

import java.util.List;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */

public interface MenuService{

     /**
      * 查询菜单信息
      * 如果用户是超级管理员，那么查询所有菜单权限
      * 如果是普通用户，根据用户id关联 角色 权限
      * @param isAdmin 是否是超级管理员
      * @param simpleUser 如果isAdmin为true，simpleUser可以为空
      */
     public List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser);

}
