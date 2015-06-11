package NVMP.DeviceManage.Implement.devicectrl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class URLCtrl {

	private URLCtrl() {
	};

	static public final URLCtrl getInstance() {
		return _URLCtrl.INSTANCE;
	}

	private static class _URLCtrl {
		private static final URLCtrl INSTANCE = new URLCtrl();
	}

	public Map<String, String> getCtrlResult(ICtrl ctrl) throws IOException {
		HashMap<String, String> hp = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		sb.append(ctrl.getCMD());
		if (!ctrl.getMap().isEmpty()) {
			sb.append("?");
			int i = 0;
			for (Iterator<Entry<String, String>> iter = ctrl.getMap()
					.entrySet().iterator(); iter.hasNext();) {
				Entry<String, String> e = iter.next();
				if (i > 0) {
					sb.append("&");
				}
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				i++;
			}

		}
		System.out.println(sb.toString());
		
		
		URL url = new URL(sb.toString());

		URLConnection connection = url.openConnection();
		connection.setReadTimeout(2000);
		connection.connect();

		Scanner in = new Scanner(connection.getInputStream(), "gbk");
		while (in.hasNext()) {
			String str = in.next();
			if (str != null && str.contains("=")) {
				String[] array_str = str.split("=");
				hp.put(array_str[0], array_str[1]);
			} else if (str != null && !str.contains("=")) {
				hp.put("single", str);
			}
		}

		return hp;
	}

}
