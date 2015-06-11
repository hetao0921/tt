package NVMP.AppService.Implement;

import java.io.File;
import java.util.Map.Entry;

class SysProperty {

	private final static boolean debugFlag = false;

	public static void main(String... args) {
//		 System.out.println(SysProperty.class.getResource("NVMP.AppService.Implement.SysProperty.class").toString());

		System.out.println(SysProperty.class.getProtectionDomain().getCodeSource().getLocation());

		System.out.println(new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath()); 
		//		for (Entry<String, String> s : System.getenv().entrySet()) {
//			System.out.println(s.getKey() + "    |    " + s.getValue());
//
//		}

	}

	public static String getAbsoluteDirectory() {
	
//			return System.getProperty("user.dir");
		System.out.println("hahaha + |" + SysProperty.class.getProtectionDomain().getCodeSource().getLocation());

//		String AbsoluteDirectory = SysProperty.class.getResource("").toString();
//		System.out.println("hahaha + |" + SysProperty.class.getProtectionDomain().getCodeSource().getLocation());
		String AbsoluteDirectory = SysProperty.class.getProtectionDomain().getCodeSource().getLocation().toString();
		// System.out.println("**************"+AbsoluteDirectory);
		try {
			int start = AbsoluteDirectory.indexOf("/");
			int end = AbsoluteDirectory.lastIndexOf("/");
	
			if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
			{
				AbsoluteDirectory = AbsoluteDirectory.substring(start, end);
			} else {

				AbsoluteDirectory = AbsoluteDirectory.substring(start + 1, end);
			}

//			end = AbsoluteDirectory.lastIndexOf("/");
			System.out.println("--------------" + AbsoluteDirectory + "    "
					+ end);
//			AbsoluteDirectory = AbsoluteDirectory.substring(0, end);

		} catch (Exception e) {
			e.printStackTrace();
			AbsoluteDirectory = "";
		}

		return AbsoluteDirectory;
	}

	// 获取生成代理文件的目录
	public static String getGenProxyDireictory() {
		if (System.getProperty("os.name").equals("Linux"))
			return "/tmp/";
		else
			return "d:\\";
	}

	public static String getDefaultBusinessDirectory() {
		return getAbsoluteDirectory() + File.separator + "business";
	}

	public static String getConfPath() {
		// 使用当前目录下的 conf/AppService.conf 作为配置文件

		if (debugFlag) {
			if (System.getProperty("os.name").equals("Linux"))
				return "/etc/fxconf/AppService/AppService.conf";
			else
				return "d:\\fxconf\\AppService\\AppService.conf";
		}

		String s = getAbsoluteDirectory();

		if (!new java.io.File(getAbsoluteDirectory()).exists()) {
			System.out.println("当前目录不存在");
		}

		String confPath = getAbsoluteDirectory() + File.separator + "conf"
				+ File.separator + "AppService.conf";
		try {
			File f = new File(confPath);
			do {
				if (!f.isFile()) {
					System.out
							.println("-------------------------------------------");
					System.out.println(confPath + " not file");
					confPath = "";
					break;
				}

				if (!f.exists()) {
					System.out
							.println("-------------------------------------------");
					System.out.println(confPath + " not exists");
					confPath = "";
					break;
				}

			} while (false);

		} catch (Exception e) {
			confPath = "";
		}

		// 不存在 conf/AppService.conf，使用 /etc/fxconf/AppService/AppService.conf
		if (confPath.length() == 0) {
			if (System.getProperty("os.name").equals("Linux"))
				return "/etc/fxconf/AppService/AppService.conf";
			else
				return "d:\\fxconf\\AppService\\AppService.conf";
		}

		return confPath;

	}

}
