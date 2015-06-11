package NVMP.DeviceManage.Implement.devicectrl;

public class ResolutionMap {
	public static int getResolutionTypeByData(ResolutionData data) {
		int resolutionType = -1;
		
		if (data.getX() == 528 && data.getY() == 384) {
			resolutionType = 0;
		} else if (data.getX() == 352 && data.getY() == 288) {
			resolutionType = 1;
		} else if (data.getX() == 176 && data.getY() == 144) {
			resolutionType = 2;
		} else if (data.getX() == 704 && data.getY() == 576) {
			resolutionType = 3;
		} else if (data.getX() == 704 && data.getY() == 288) {
			resolutionType = 4;
		} else if (data.getX() == 640 && data.getY() == 480) {
			resolutionType = 16;
		} else if (data.getX() == 1600 && data.getY() == 1200) {
			resolutionType = 17;
		} else if (data.getX() == 800 && data.getY() == 600) {
			resolutionType = 18;
		} else if (data.getX() == 1280 && data.getY() == 720) {
			resolutionType = 19;
		} else if (data.getX() == 1280 && data.getY() == 960) {
			resolutionType = 20;
		} else if (data.getX() == 1600 && data.getY() == 912) {
			resolutionType = 21;
		} else if (data.getX() == 1280 && data.getY() == 1024) {
			resolutionType = 22;
		} else if (data.getX() == 1920 && data.getY() == 1080) {
			resolutionType = 27;
		} else if (data.getX() == 2560 && data.getY() == 1920) {
			resolutionType = 28;
		} else if (data.getX() == 1600 && data.getY() == 304) {
			resolutionType = 29;
		} else if (data.getX() == 2048 && data.getY() == 1536) {
			resolutionType = 30;
		} else if (data.getX() == 2448 && data.getY() == 2048) {
			resolutionType = 31;
		} else if (data.getX() == 2448 && data.getY() == 1200) {
			resolutionType = 32;
		} else if (data.getX() == 2448 && data.getY() == 800) {
			resolutionType = 33;
		} else {
			System.out.println("没有这个分辨率");
		}
		
		return resolutionType;
	}
	
	public static ResolutionData getDataByResolutionType(int resolutionType) {
		ResolutionData data = new ResolutionData();
		
		if (resolutionType == 0) {
			data.setX(528);
			data.setY(384);
		} else if (resolutionType == 1) {
			data.setX(352);
			data.setY(288);
		} else if (resolutionType == 2) {
			data.setX(176);
			data.setY(144);
		} else if (resolutionType == 3) {
			data.setX(704);
			data.setY(576);
		} else if (resolutionType == 4) {
			data.setX(704);
			data.setY(288);
		} else if (resolutionType == 16) {
			data.setX(640);
			data.setY(480);
		} else if (resolutionType == 17) {
			data.setX(1600);
			data.setY(1200);
		} else if (resolutionType == 18) {
			data.setX(800);
			data.setY(600);
		} else if (resolutionType == 19) {
			data.setX(1280);
			data.setY(720);
		} else if (resolutionType == 20) {
			data.setX(1280);
			data.setY(960);
		} else if (resolutionType == 21) {
			data.setX(1600);
			data.setY(912);
		} else if (resolutionType == 22) {
			data.setX(1280);
			data.setY(1024);
		} else if (resolutionType == 27) {
			data.setX(1920);
			data.setY(1080);
		} else if (resolutionType == 28) {
			data.setX(2560);
			data.setY(1920);
		} else if (resolutionType == 29) {
			data.setX(1600);
			data.setY(304);
		} else if (resolutionType == 30) {
			data.setX(2048);
			data.setY(1536);
		} else if (resolutionType == 31) {
			data.setX(2448);
			data.setY(2048);
		} else if (resolutionType == 32) {
			data.setX(2448);
			data.setY(1200);
		} else if (resolutionType == 33) {
			data.setX(2448);
			data.setY(800);
		} else {
			System.out.println("没有这个分辨率");
			data = null;
		}
		
		return data;
	}
}
