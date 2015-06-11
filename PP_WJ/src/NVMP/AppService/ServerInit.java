package NVMP.AppService;

import corenet.exchange.IConnectRule;
import NVMP.AppService.Interface.IAppRuntime;

/**
 * 用来提供给外在进行数据库初始化而用。
 * */
public abstract class ServerInit implements IConnectRule{

	private IAppRuntime iar;

	/**
	 * 获得当前运行环境。
	 * */
	final public IAppRuntime getAppRuntime() {
		return iar;
	}

	final public void setAppRuntime(IAppRuntime ir) {
		iar = ir;
	}

	/**
	 * 赋予服务器唯一标示
	 * */
	abstract public String getSessionID();
	
	/**
	 *赋予服务器启动的端口 
	 * */
	abstract public int getPort();
	
	
	/**
	 * 
	 * 赋予服务器启动的IP
	 * */
	abstract public String getIP();

	/**
	 * 在服务器业务运行之前，进行的初始化。一般内存数据初始在此处。
	 * */
	abstract public void init_BeginBusiness();

	/**
	 * 在服务器业务运行之后，进行的初始化。一般连接什么的都在这里。 在这里可以调用 this.getAppRuntime()来获得运行环境。 注明：
	 * AppRuntime.CreateLocalChannel是对外建立服务器连接 需要三个参数 1、 目的sessionid 2、IP地址 3、端口
	 * 
	 * */
	abstract public void init_AfterBusiness();
	
	
	/**
	 * 判断一个id或者IP能否正常登陆
	 * */
	 public boolean isAllowLogin(String sessionID,String sessionIP){
		 return true;
	 }
	
	
	
}
