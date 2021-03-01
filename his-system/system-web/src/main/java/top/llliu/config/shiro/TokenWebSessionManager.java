package top.llliu.config.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import top.llliu.constants.Constants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc: 没有token 生成一个返回给前台，有，直接从请求头中获取
 */
public class TokenWebSessionManager extends DefaultWebSessionManager {

  @Override
  protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
    //从头里面得到请求TOKEN 如果不存在就生成一个
    String header = WebUtils.toHttp(request).getHeader(Constants.TOKEN);
    if(StringUtils.hasText(header)){
      return  header;
    }
    return  UUID.randomUUID().toString() ;
  }
}
