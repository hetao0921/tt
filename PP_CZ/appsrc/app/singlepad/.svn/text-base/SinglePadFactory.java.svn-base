package app.singlepad;

import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

/**
 * 单兵实例工厂
 * 
 * @author hxht
 * @version 2014-9-17
 */
public class SinglePadFactory {

	static {
//		try{
//			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//			Class cla = classLoader.getClass();
//			while (cla != ClassLoader.class)
//				cla = cla.getSuperclass();
//			Field field = cla.getDeclaredField("classes");
//			field.setAccessible(true);
//			Vector v = (Vector) field.get(classLoader);
//			for (int i = 0; i < v.size(); i++) {
//				System.out.println(((Class) v.get(i)).getName());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		try {
			Class.forName("app.singlepad.impl.DefaultSinglePadImpl");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 单兵默认类型
	public static final String DEFAULT_TYPE = "SinglePad";

	private static Map<String, Class<? extends AbstractSinglePad>> adapter;

	// 单兵类型注册
	public static void register(String type,
			Class<? extends AbstractSinglePad> clazz) {
		if(adapter == null){
			adapter = new HashMap<String, Class<? extends AbstractSinglePad>>();
		}
		adapter.put(type, clazz);
	}

	// 生产指定类型的单兵实例
	public static ISinglePad createSinglePad(String type) {
		ISinglePad singlePad = null;
		try {
			if (StringUtils.isNullOrEmpty(type)) {
				singlePad = adapter.get(DEFAULT_TYPE).newInstance();
			} else {
				singlePad = adapter.get(type).newInstance();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return singlePad;
	}
	//
	// public static List<ISinglePad> createSinglePads(String type){
	// List<ISinglePad> pads = new ArrayList<ISinglePad>();
	// ISinglePad singlePad = createSinglePad(type);
	// if(singlePad != null){
	// List<? extends AbstractSinglePadBean> beans = singlePad.getSinglePads();
	// for(ISinglePadBean bean : beans){
	// ISinglePad pad = createSinglePad(type);
	// pad.setBean(bean);
	// pads.add(pad);
	// }
	// }
	// return pads;
	// }

}
