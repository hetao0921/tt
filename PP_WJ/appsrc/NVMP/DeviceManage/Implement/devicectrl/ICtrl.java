package NVMP.DeviceManage.Implement.devicectrl;

import java.util.Map;

public interface ICtrl {
	String getCMD();//获取操作指令 
	Map<String,String> getMap();//获取操作元素
}
