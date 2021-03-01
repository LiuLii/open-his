package top.llliu.config.controller.system;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.llliu.constants.Constants;
import top.llliu.constants.HttpStatus;
import top.llliu.domain.Menu;
import top.llliu.domain.SimpleUser;
import top.llliu.dto.LoginBodyDto;
import top.llliu.service.MenuService;
import top.llliu.vo.ActiverUser;
import top.llliu.vo.AjaxResult;
import top.llliu.vo.MenuTreeVo;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */
@RestController
@Log4j2
public class LoginController {

  @Autowired
  private MenuService menuService;

  @PostMapping("login/doLogin")
  public AjaxResult login(@RequestBody LoginBodyDto loginBodyDto, HttpServletRequest request){
    AjaxResult ajax = AjaxResult.success();
    String username = loginBodyDto.getUsername();
    String password = loginBodyDto.getPassword();
    Subject subject = SecurityUtils.getSubject();
    // 构造用户名 、 密码的token
    UsernamePasswordToken token = new UsernamePasswordToken(username,password);
    try {
      subject.login(token);
      //得到会话的token==也就是redis里面存的
      Serializable webToken = subject.getSession().getId();
      ajax.put(Constants.TOKEN,webToken);
    } catch (Exception e) {
      log.error("用户名或密码不正确", e);
      ajax = AjaxResult.error(HttpStatus.ERROR, "用户名或密码不正确");
    }
    return ajax;
  }

  /**
   * 获取用户信息
   *
   * @return 用户信息
   */
  @GetMapping("login/getInfo")
  public AjaxResult getInfo()
  {
    Subject subject = SecurityUtils.getSubject();
    ActiverUser activerUser= (ActiverUser) subject.getPrincipal();
    AjaxResult ajax = AjaxResult.success();
    ajax.put("username", activerUser.getUser().getUserName());
    ajax.put("picture", activerUser.getUser().getPicture());
    ajax.put("roles", activerUser.getRoles());
    ajax.put("permissions", activerUser.getPermissions());
    return ajax;
  }

  /**
   * 用户退出
   */
  public AjaxResult logout(){
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return AjaxResult.success("用户退出成功");
  }

  public AjaxResult getMenus(){
    Subject subject = SecurityUtils.getSubject();
    ActiverUser activerUser= (ActiverUser) subject.getPrincipal();
    boolean isAdmin=activerUser.getUser().getUserType().equals(Constants.USER_ADMIN);
    SimpleUser simpleUser=null;
    if(isAdmin){
      simpleUser=new SimpleUser(activerUser.getUser().getUserId(),activerUser.getUser().getUserName());
    }
    List<Menu> menus = menuService.selectMenuTree(isAdmin,simpleUser);
    List<MenuTreeVo> menuVos=new ArrayList<>();
    for (Menu menu : menus) {
      menuVos.add(new MenuTreeVo(menu.getMenuId().toString(),menu.getPath()));
    }
    return AjaxResult.success(menuVos);
  }
}
