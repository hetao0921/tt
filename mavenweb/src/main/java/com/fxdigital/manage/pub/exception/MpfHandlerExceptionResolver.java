/**
 * 文件名		：EmanualHandlerExceptionResolver.java
 * 创建日期	：2012-9-30
 * Copyright (c) 2003-2012 北京联龙博通
 * All rights reserved.
 */
package com.fxdigital.manage.pub.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 描述:异常处理
 * 
 * @version 1.00
 * @author xieyg
 * 
 */
public class MpfHandlerExceptionResolver extends SimpleMappingExceptionResolver {

  /**日志对象*/
  private Log logger = LogFactory.getLog(MpfHandlerExceptionResolver.class);

  protected ModelAndView doResolveException(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      java.lang.Object o, java.lang.Exception e) {
    String errCode = "";
    String errMessage = "";
    if (e instanceof MpfException) { // 类型是EmanualException
      MpfException eobj = (MpfException) e;
      errCode = eobj.getErrCode();
      errMessage = eobj.getErrMessage();
      logger.error("EmanualException----errCode is: " + errCode);
      logger.error("EmanualException----errMessage is: " + errMessage);
      httpServletRequest.setAttribute("exceptionobj", eobj);
    } else if (e instanceof MpfRuntimeException) { // 类型是EmanualRuntimeException
      MpfRuntimeException eobj = (MpfRuntimeException) e;
      errCode = eobj.getErrCode();
      errMessage = eobj.getErrMessage();
      logger.error("EmanualRuntimeException----errCode is: " + errCode);
      logger.error("EmanualRuntimeException----errMessage is: " + errMessage);
      httpServletRequest.setAttribute("exceptionobj", eobj);
    } else {
      MpfException eobj = new MpfException((Throwable) e);
      errCode = eobj.getErrCode();
      logger.error("EmanualException----the " + errCode + " is defaultErrCode");
      httpServletRequest.setAttribute("exceptionobj", eobj);
    }
    logger.error("----error e:" + e.getMessage(), e);
    return super.doResolveException(httpServletRequest,
        httpServletResponse, o, e);
  }
}
