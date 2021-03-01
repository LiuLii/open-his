package top.llliu.service;

import top.llliu.domain.User;
     /**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */

public interface UserService{
     /**
      * 根据手机号查询用户
      * @param phone 手机号
      * @return
      */
     User queryUserByPhone(String phone);

     /**
      * 根据用户ID查询用户
      * @param userId 用户编号
      * @return
      */
     User getOne(Long userId);
}
