/**
 * 文件名		：EmanualException.java
 * 创建日期	：2012-10-24
 * Copyright (c) 2003-2012 北京联龙博通 * All rights reserved.
 */
package com.fxdigital.manage.pub.exception;





/**
 * 描述: 自定义异常
 * 
 * @version 1.00
 * @author xieyg
 * 
 */

public class MpfException extends Exception{

  private static final long serialVersionUID = 7927610504187626240L;
  
  /** 默认系统错误代码 */
  private static final String SYS_ERR_CODE = "EML_0000m";
  
  /** 错误代码 */
  private String errCode;

  /** 错误消息 */
  private String errMessage;
  
  public MpfException () {
    this(SYS_ERR_CODE);
  }

  /**
   * @param errorCode 异常代码
   */
  public MpfException (String errorCode) {
    super(errorCode);
    errCode = errorCode;
    errMessage = errorCode;
  }
  
  /**
   * 
   * @param cause 异常对象
   */
  public MpfException(Throwable cause) {
    this(SYS_ERR_CODE, cause);
  }
  
  /**
   * @param errorCode 异常代码
   * @param cause 异常对象
   */
  public MpfException(String errorCode, Throwable cause) {
    super(errorCode, cause);
    errCode = errorCode;
    errMessage =errorCode;
  }
  
  /**
   * @param errorCode 异常代码
   */
  public MpfException (String errorCode, String defaultMessage) {
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
