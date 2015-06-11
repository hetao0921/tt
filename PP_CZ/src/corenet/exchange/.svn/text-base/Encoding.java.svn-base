package corenet.exchange;

import java.util.UUID;

public class Encoding {
	public static String byteToString(byte[] a) {
		if (a == null)
			return null; 
		String str = "";
		try {
			if (a != null)
				str = new String(a, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();

			if (a != null) {

				for (byte m : a) {
					System.out.println(m);

				}

			}

		}
		return str;
	}

	public static byte[] StringToByte(String str) {
		if (str == null)
			return null;
		try {
			return str.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String AdsToGroupProtocol(String str) {
		return "Group://" + str;

	}

	public static String AdsToPointProtocol(String str) {
		return "Session://" + str;

	}

	public static String GroupProtocoToAds(String str) {
		return str.substring(8);
	}

	public static String PointProtocoToAds(String str) {
		return str.substring(10);
	}

	// 生成唯一ID,32位的字符串
	public static String getUuid() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

}