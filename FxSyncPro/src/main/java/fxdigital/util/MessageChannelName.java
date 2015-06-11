package fxdigital.util;

public class MessageChannelName {
	
	public static String LOCAL_POST_CHANNEL="PostAddress";
	
	public static String LOCAL_POSTCLIENT_CHANNEL="005008047DEA@001_DBSyncContent";
	
	public static String UP_SERVER_CHANNEL="nvmp.serverQueue";
	
	public static String UP_CLIENT_CHANNEL="nvmp.clientQueue";
	
	
	public static String getPostClientChannel(String centerid){
		return centerid+"_DBSyncContent";
	}
	

}
