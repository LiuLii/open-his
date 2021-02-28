package top.llliu.dto;

import org.springframework.format.annotation.DateTimeFormat;
import top.llliu.domain.SimpleUser;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:
 */
public class BaseDto implements Serializable {
  /**
   * 页码 默认1
   */
  private Integer pageNum = 1;

  /**
   * 每页显示条数 默认10
   */
  private Integer pageSize = 10;

  /**
   * 当前操作对象
   */
  private SimpleUser simpleUser;

  /**
   * 开始时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date beginTime;
  /**
   * 结束时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endTime;

}
