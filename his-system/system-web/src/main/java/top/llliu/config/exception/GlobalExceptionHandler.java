package top.llliu.config.exception;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.llliu.vo.AjaxResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuli
 * @version 1.0
 * Filename:ApiAdminConfig.java
 * Desc:全局异常处理,用于处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 当系统出现 MethodArgumentNotValidException参数异常的时候，会调用下面这个方法
   * @param e
   * @return
   */
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public AjaxResult jsonErrorHandler(MethodArgumentNotValidException e){
    List <Map <String,Object>> list = new ArrayList<>();
    List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
    for (ObjectError errors:allErrors){
      Map<String,Object> map = new HashMap<>();
      map.put("defaultMessage",errors.getDefaultMessage());
      map.put("objectName",errors.getObjectName());
      // 这里拿到具体的某一个属性
      FieldError fieldError = (FieldError)errors;
      map.put("field",fieldError.getField());
      list.add(map);
    }
    return AjaxResult.fail("后台数据校验异常",list);
  }
}
