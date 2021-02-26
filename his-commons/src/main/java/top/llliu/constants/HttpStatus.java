package top.llliu.constants;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:响应状态码常量
 */
public interface HttpStatus {

    /**
     * 响应成功
     */
    int SUCCESS = 200;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    int BAD_REQUEST = 400;

    /**
     * 没有权限
     */
    int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    int FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    int BAD_METHOD = 405;

    /**
     * 系统内部错误
     */
    int ERROR = 500;
}
