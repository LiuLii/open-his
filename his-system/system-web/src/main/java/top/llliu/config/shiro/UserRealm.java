package top.llliu.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import top.llliu.domain.User;
import top.llliu.service.UserService;
import top.llliu.vo.ActiverUser;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */
public class UserRealm extends AuthorizingRealm {

  /**
   * @Lazy 有冲突，用懒加载
   */
  @Autowired
  @Lazy
  private UserService userService;

//  获取realm 的名称
  @Override
  public String getName(){
    return this.getClass().getSimpleName();
  }

  /**
   * 做认证
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    // 获取用户名
    String phone = token.getPrincipal().toString();
//    根据用户名查询用户是否存在
    User user = userService.queryUserByPhone(phone);
    if(null != user){
//      查找的用户如果存在，组装好存放到redis中
      ActiverUser activeUser = new ActiverUser();
      activeUser.setUser(user);
      SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),getName());
      return info;
    }else{
      // 表示用户不存在
      return null;
    }

  }

  /**
   * 做授权 登录成功之后，判断用户是否有某个菜单或者按钮的权限
   * @param principalCollection
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//    返回值得到的 是 登录认证方法中 的第一个参数
    ActiverUser activeUser = (ActiverUser)principalCollection.getPrimaryPrincipal();
//    暂时先返回一个空对象
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    return info;
  }
}
