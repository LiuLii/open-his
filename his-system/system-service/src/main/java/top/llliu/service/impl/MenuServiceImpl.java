package top.llliu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.llliu.constants.Constants;
import top.llliu.domain.Menu;
import top.llliu.domain.SimpleUser;
import top.llliu.mapper.MenuMapper;
import top.llliu.service.MenuService;

import java.util.List;
 /**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */

@Service
public class MenuServiceImpl implements MenuService{

  @Autowired
  private MenuMapper menuMapper;

  @Override
  public List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser) {
   // mybatisplus 构建 查询条件
   QueryWrapper<Menu> qw=new QueryWrapper<>();
   qw.eq(Menu.COL_STATUS, Constants.STATUS_TRUE);
   qw.in(Menu.COL_MENU_TYPE,Constants.MENU_TYPE_M,Constants.MENU_TYPE_C);
   qw.orderByAsc(Menu.COL_PARENT_ID);
   if(isAdmin){
     return this.menuMapper.selectList(qw);
   }else{
    return this.menuMapper.selectList(qw);
   }
  }
 }
