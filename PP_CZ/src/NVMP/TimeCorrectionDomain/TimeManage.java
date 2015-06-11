package NVMP.TimeCorrectionDomain;

import java.util.Calendar;
import java.util.TimeZone;

import org.misc.log.LogUtil;

import NVMP.AppService.Remoting;
import NVMP.Proxy.TimeCorrectionDomainProxy;
import corenet.exchange.Encoding;
import corenet.rpc.IMessage;

public class TimeManage extends Thread {
	public ITimeManage TimeManage;

	private int n;

	public TimeManage() {
		this.start();
	}
 
	/**
	 * 修改循环间隔时间，同时进行一次广播
	 * 
	 * */
	@Remoting
	public void SetWaitTime(Integer wait) {
		try {
			n = wait;
			AnnounceTime("ALL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接收时间，修改系统时间，进行广播
	 * */
	/**
	 * 接收时间，修改系统时间，进行广播
	 * */
	@Remoting
	public void SetSystemTime(Integer year, Integer month, Integer day,
			Integer hour, Integer minute, Integer second) { 
//		Calendar SysCalendar = Calendar.getInstance(TimeZone
//				.getTimeZone("GMT+8"));
		try {
//			SysCalendar.set(year, month - 1, day, hour, minute, second);
			String cmd;
			if (System.getProperty("os.name").equals("Linux")) {
				String dayStr = day.toString();
				if (day < 10)
					dayStr = "0" + dayStr;
				String monStr = month.toString();
				if (month < 10)
					monStr = "0" + monStr;

				cmd = "date " + monStr + dayStr + hour + minute + year + "."
						+ second;
				LinuxCmd.processUseBasic(cmd);
				// // 格式 HH:mm:ss
				// cmd = "  date -s " + hour + ":" + minute + ":" + second;
				// Runtime.getRuntime().exec(cmd);
				//
				// cmd = "  date -s " + year + monStr + dayStr;
				// Runtime.getRuntime().exec(cmd);

			}

			else {

				// 格式 HH:mm:ss
				cmd = "  cmd /c time " + hour + ":" + minute + ":" + second;
				Runtime.getRuntime().exec(cmd);
				// 格式：yyyy-MM-dd
				cmd = " cmd /c date " + year + "-" + month + "-" + day;
				Runtime.getRuntime().exec(cmd);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		AnnounceTime("Local://ALL");

	}

	@Remoting
	public void GetSystemTime(String ClientId) {

		AnnounceTime(Encoding.AdsToPointProtocol(ClientId));
	}

	public void AnnounceTime(String channel) {
		Calendar SysCalendar = Calendar.getInstance(TimeZone
				.getTimeZone("GMT+8"));
		(TimeCorrectionDomain.AppRunTime()).SetCurChannel(channel);
		TimeManage.OnAnnounceTime(SysCalendar.get(Calendar.YEAR),
				SysCalendar.get(Calendar.MONTH) + 1,
				SysCalendar.get(Calendar.DATE),
				SysCalendar.get(Calendar.HOUR_OF_DAY),
				SysCalendar.get(Calendar.MINUTE),
				SysCalendar.get(Calendar.SECOND));

	}

	public void run() {
		// TODO Auto-generated method stub
		n = 3;
		while (true) {
			// LogUtil.TestInfo("now send time contrl");
			try {
				Thread.sleep(n * 60 * 1000);

				Calendar SysCalendar = Calendar.getInstance(TimeZone
						.getTimeZone("GMT+8"));
				AnnounceTime("Local://ALL");
				IMessage message = TimeCorrectionDomainProxy
						.GrobalTimeEdit_Copy(SysCalendar.get(Calendar.YEAR),
								SysCalendar.get(Calendar.MONTH) + 1,
								SysCalendar.get(Calendar.DATE),
								SysCalendar.get(Calendar.HOUR_OF_DAY),
								SysCalendar.get(Calendar.MINUTE),
								SysCalendar.get(Calendar.SECOND));
				TimeCorrectionDomain.AppRunTime().LocalDownSendMessage(message,
						null, null);
				NVMP.Proxy.StateManageDomainProxy.SetDeviceTime(
						SysCalendar.get(Calendar.YEAR),
						SysCalendar.get(Calendar.MONTH) + 1,
						SysCalendar.get(Calendar.DATE),
						SysCalendar.get(Calendar.HOUR_OF_DAY),
						SysCalendar.get(Calendar.MINUTE),
						SysCalendar.get(Calendar.SECOND));
			} catch (Exception e) {
				for (StackTraceElement s : e.getStackTrace()) {
					LogUtil.TestInfo("error by ==" + s.toString());
				}
			}
		}
	}

	@Remoting
	public void GrobalTimeEdit(Integer year, Integer month, Integer day,
			Integer hour, Integer minute, Integer second) {

//		System.out.println("=========上级通知下级进行修改了=======");
		LogUtil.TestInfo("grobaltimeedit" + year.intValue() + " " +
				month.intValue()+" " + 
				day.intValue()+ " " +
				hour.intValue()+ " " + 
				minute.intValue() + " " +
				second.intValue());
//		Calendar SysCalendar = Calendar.getInstance(TimeZone
//				.getTimeZone("GMT+8"));
		try {
//			SysCalendar.set(year, month - 1, day, hour, minute, second);
			String cmd;
			if (System.getProperty("os.name").equals("Linux")) {
				String dayStr = day.toString();
				if (day < 10)
					dayStr = "0" + dayStr;
				String monStr = month.toString();
				if (month < 10)
					monStr = "0" + monStr;

//				cmd = " date " + monStr + dayStr + hour + minute + year + "."
//						+ second;
				cmd = "date -s \""+year+monStr+dayStr+" "+hour+":"+minute+":"+second+"\"";
				LogUtil.TestInfo(cmd);
				
				String result = LinuxCmd.processUseBasic(cmd);
				
				LogUtil.TestInfo(result);
				
//				String[] comands = new String[] {"/bin/sh", "-c", cmd };
//				Process p = Runtime.getRuntime().exec(comands);
//				
////				ProcessBuilder builder = new ProcessBuilder(cmd); 
////				builder.redirectErrorStream(true);  
////				Process p = builder.start();
//				BufferedReader br = new BufferedReader(new InputStreamReader(p  
//					  .getErrorStream()));  
//				String readLine = br.readLine();  
//				while (readLine != null) {  
//					LogUtil.TestInfo(readLine);  
//					readLine = br.readLine();  
//				}  


				// cmd = "  date -s " + year + monStr + dayStr;
				// Runtime.getRuntime().exec(cmd);
				// // 格式 HH:mm:ss
				// cmd = "  date -s " + hour + ":" + minute + ":" + second;
				// Runtime.getRuntime().exec(cmd);

			}

			else {

				// 格式 HH:mm:ss
				cmd = "  cmd /c time " + hour + ":" + minute + ":" + second;
				Runtime.getRuntime().exec(cmd);
				// 格式：yyyy-MM-dd
				cmd = " cmd /c date " + year + "-" + month + "-" + day;
				Runtime.getRuntime().exec(cmd);
			}

		} catch (Exception e) {
			
			LogUtil.BusinessError("timecorrectionDomain error!!!" + e.getCause());
			for (StackTraceElement s : e.getStackTrace()) {
				LogUtil.BusinessError("error by ==" + s.toString());
			}

		}

	}

}
