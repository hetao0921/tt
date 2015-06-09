package fxdigital.util;


/**
 * 相关参数
 * @author fucz
 * @version 2014-7-18
 */
public class ArgsUtil {
	
	public static final String POST_CHANNEL = "PostAddress";
	public static final String EXCHANGE_CHANNEL = "Exchange";
	
	public static String getPostAddress(){
//		String postAddress = ConfigUtil.getString("mqPostAddress");
//		if(StringUtils.isNullOrEmpty(postAddress)){
//			postAddress = POSTADDRESS;
//		}
		return POST_CHANNEL;
	}
	
	public static String getExchangeAddress(){
		return EXCHANGE_CHANNEL;
	}
	
}
