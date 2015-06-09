/**
 * 文件名		：EmanualRuntimeException.java
 * 创建日期	：2012-10-25
 * Copyright (c) 2003-2012 北京联龙博通 * All rights reserved.
 */
package com.fxdigital.manage.pub.exception;




/**
 * 描述: 自定义异常,Type is Runtime.
 * 
 * @version 1.00
 * @author xieyg
 * 
 */

public class MpfRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 8648615007554705616L;
  /** 默认系统错误代码 */
  private static final String SYS_ERR_CODE = "EML_0001m";
  
  /** 错误代码 */
  private String errCode;

  /** 错误消息 */
  private String errMessage;
  
  public MpfRuntimeException() {
      this(SYS_ERR_CODE);
  }
  
  public MpfRuntimeException(String errorCode) {
      super(errorCode);
      errCode = errorCode;
      errMessage = errorCode;
  }
  
  /**
   * 
   * @param cause 异常对象
   */
  public MpfRuntimeException(Throwable cause) {
    this(SYS_ERR_CODE, cause);
  }
  
  /**
   * @param errorCode 异常代码
   * @param cause 异常对象
   */
  public MpfRuntimeException(String errorCode, Throwable cause) {
    super(errorCode, cause);
    errCode = errorCode;
    errMessage = errorCode;
  }
  
  /**
   * @param errorCode 异常代码
   */
  public MpfRuntimeException (String errorCode, String defaultMessage) {
    super(errorCode);
    errCode = errorCode;
    errMessage = errorCode;
    if (errMessage==null){ //未取到，使用默认消息
      errMessage = defaultMessage; 
    }
  }
  
  /**
   * @return the errCode
   */
  public String getErrCode() {
    return errCode;
  }

  /**
   * @return the errMessage
   */
  public String getErrMessage() {
    return errMessage;
  }
}
