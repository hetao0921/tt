package NVMP.Proxy;

import java.util.HashMap;

import org.misc.log.LogUtil;



import NVMP.AppService.Interface.IAppRuntime;

/**
 * by zzw
 * 
 * �����洢������ģ�ÿ������ʱ����м���
 * 
 * */
public class ProxyList {

	private HashMap<String, Object> proxyHp;

	public ProxyList() {
		proxyHp = new HashMap<String, Object>();
	}

	/** �жϴ����Ƿ��Ѿ��д��� */
	public boolean IsExist(String domain) {
		return proxyHp.containsKey(domain);
	}

	/** ������� */
	public boolean addProxy(String domain, Object obj) {
		if (!IsExist(domain)) {
			proxyHp.put(domain, obj);
			return true;
		} else {
			return false;
		}

	}

	/** ȡ������ */
	public Object foundProxy(String domain) {
		if (IsExist(domain)) {
			return proxyHp.get(domain);
		} else {
			return null;
		}

	}

	/** �Ƴ���� */
	public boolean removeProxy(String domain) {
		if (IsExist(domain)) {
			proxyHp.remove(domain);
			return true;
		} else {
			return false;
		}

	}

	/** ��ɴ���ʵ���Ҽ��� */
	public void loadProxy(String domain,IAppRuntime ia) {
		String className = "NVMP.Proxy." + domain+"Proxy";
		try {
		

		if (IsExist(domain)) {
//			LogUtil.debug("���ڴ�����" + className + ",����Ҫ����");
			Object o= this.foundProxy(domain);
			o.getClass().getMethod("load").invoke(o, ia);
			
		} else {

		
				Class<?> c = Class.forName(className);
				Object o = c.newInstance();
				o.getClass().getMethod("load",IAppRuntime.class).invoke(o, ia);
				//this.addProxy(domain, c.newInstance());
				LogUtil.debug("������" + className + ",���سɹ�");


		}
		} catch (Exception e) {
	
			LogUtil.error("������" + className + ",����ʧ�ܣ�"+e.getMessage());
		}
	}

}
