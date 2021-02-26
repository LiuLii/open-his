package top.llliu.vo;

import java.util.HashMap;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc: 通用返回对象类
 */
public class AjaxResult extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 数据总条数
     */
    public static final String DATA_TOTAL = "total";

    /**
     * 初始化一个AjaxResult 空对象，使其表示一个空消息
     */
    public AjaxResult(){

    }


}
