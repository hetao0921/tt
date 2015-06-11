package com.hoo.chat;

import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletContext;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.ServletContextAware;

import com.hoo.entity.Message;

public class ChatMessageClient implements ApplicationListener, ServletContextAware{
    private ServletContext ctx;

    public void setServletContext(ServletContext ctx) {

        this.ctx = ctx;

    }

    

    @SuppressWarnings("deprecation")

    public void onApplicationEvent(ApplicationEvent event) {

        //如果事件类型是ChatMessageEvent就执行下面操作

        if (event instanceof ChatMessageEvent) {

            Message msg = (Message) event.getSource();

            ServerContext context = ServerContextFactory.get();

            //获得客户端所有chat页面script session连接数

 

            Collection<ScriptSession> sessions = context.getScriptSessionsByPage(ctx.getContextPath() + "/chat.jsp");

            for (ScriptSession session : sessions) {

                ScriptBuffer sb = new ScriptBuffer();

                Date time = msg.getTime();

                String s = time.getYear() + "-" + (time.getMonth() + 1) + "-" +  time.getDate() + " " 

                        +  time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();

                //执行setMessage方法

 

                sb.appendScript("showMessage({msg: '")

                .appendScript(msg.getMsg())

                .appendScript("', time: '")

                .appendScript(s)

                .appendScript("'})");

                System.out.println(sb.toString());

                //执行客户端script session方法，相当于浏览器执行JavaScript代码

                  //上面就会执行客户端浏览器中的showMessage方法，并且传递一个对象过去

 

                session.addScript(sb);

            }

        }

    }
}
