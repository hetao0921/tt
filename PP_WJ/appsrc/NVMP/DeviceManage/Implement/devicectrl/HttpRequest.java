package NVMP.DeviceManage.Implement.devicectrl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
	private String ip = "";
	private String urlSuffix = "/goform/TL_http_std_Set?In_Code=8,100,28,0,1,#";	// 暂时是固定的
	
	public HttpRequest(String rtspURL) {
		String strIPPORT = "";
		
		if (rtspURL.contains("@")) {
			int sindex = rtspURL.indexOf("@");
			int sindex2 = rtspURL.indexOf("/", 7);
			strIPPORT = rtspURL.substring(sindex + 1, sindex2);

			if (strIPPORT.contains(":")) {
				int sindex3 = strIPPORT.indexOf(":");
				ip = strIPPORT.substring(0, sindex3);
			} else {
				ip = strIPPORT;
			}
		} else {
			int sindex4 = rtspURL.indexOf("/", 7);
			strIPPORT = rtspURL.substring(7, sindex4);
			if (strIPPORT.contains(":")) {
				int sindex3 = strIPPORT.indexOf(":");
				ip = strIPPORT.substring(0, sindex3);
			} else {
				ip = strIPPORT;
			}
		}
	}
	
    public String sendGetRequest() {
    	System.out.println("------------------sendGetRequest, 发送Http请求--------------------");
    	String url = "http://" + ip + urlSuffix;
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.connect();
            
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("D:\\Program Files\\VideoLAN\\VLC\\vlc.exe rtsp://192.168.1.135/test");
            new HttpRequest("rtsp://192.168.1.135/test").sendGetRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
