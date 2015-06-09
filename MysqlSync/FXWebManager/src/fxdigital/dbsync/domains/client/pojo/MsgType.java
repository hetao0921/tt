package fxdigital.dbsync.domains.client.pojo;
/**
 * @author  het
 * 与服务器交互消息标识类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.pojo
 */
public class MsgType {

	//上传初始化c-s
	public static final String UP_INIT_CS = "10";
	//上传初始化s-c
	public static final String UP_INIT_SC = "11";
	//下载初始化c-s
	public static final String Load_INIT_CS = "12";
	//下载初始化s-c
	public static final String Load_INIT_SC = "13";
	//上传命令c-s
	public static final String UP_COMMAND_CS = "0";
	//上传命令c-s
	public static final String UP_COMMAND_SC = "16";
	//下载命令c-s
	public static final String Load_COMMAND_CS = "1";
	//下载命令s-c
	public static final String Load_COMMAND_SC = "2";
	//还原命令c-s
	public static final String Load_RESET_CS = "3";
	//还原命令s-c
	public static final String Load_RESET_SC = "4";
	//还原命令c-s
	public static final String Load_RESET_SECOND_CS = "5";
	//还原命令s-c
	public static final String Load_RESET_SECOND_SC= "6";
	
	//同步服务s0-s1(下级到上级)
	public static final String AUTO_SYNC_S0S1 = "7";
	//同步服务s1-s0(上级到下级)
	public static final String AUTO_SYNC_S1S0= "8";
	
	//同步服务s0-s1(下级再到上级)
	public static final String AUTO_SYNC_SECOND_S0S1 = "9";
	
	//查看服务器日志c-s
	public static final String VIEW_LOGS_CS= "14";
	//查看服务器日志s-c
	public static final String VIEW_LOGS_SC = "15";



}
