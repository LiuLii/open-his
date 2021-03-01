package top.llliu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.llliu.domain.User;
import top.llliu.mapper.UserMapper;
import top.llliu.service.UserService;
 /**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */

@Service
public class UserServiceImpl  implements UserService{

  @Autowired
  private UserMapper userMaper;

  @Override
  public User queryUserByPhone(String phone) {
   QueryWrapper<User> qw = new QueryWrapper<>();
   qw.eq(User.COL_PHONE,phone);
   User user = this.userMaper.selectOne(qw);
   return user;
  }

  @Override
  public User getOne(Long userId) {
   return this.userMaper.selectById(userId);
  }
 }
