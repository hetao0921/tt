package com.fxdigital.tcp.util.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

  /** 
     * 心跳超时处理 
     * KeepAliveFilter 在没有收到心跳消息的响应时，会报告给的KeepAliveRequestTimeoutHandler。 
     * 默认的处理是 KeepAliveRequestTimeoutHandler.CLOSE 
     * （即如果不给handler参数，则会使用默认的从而Close这个Session） 
     * @author cruise 
     * 
     */  
  
  public class KeepAliveRequestTimeoutHandlerImpl implements  
          KeepAliveRequestTimeoutHandler {  
  
    
      @Override  
      public void keepAliveRequestTimedOut(KeepAliveFilter filter,  
              IoSession session) throws Exception {  
         MessageReceiveServer.LOG.info("心跳超时！");  
          session.close(true);
      }  
  
  }  