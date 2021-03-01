package top.llliu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.llliu.domain.User;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser implements Serializable {
  private User user;
  private List<String> roles = Collections.EMPTY_LIST;//角色
  private List<String> permissions = Collections.EMPTY_LIST;//权限
}
