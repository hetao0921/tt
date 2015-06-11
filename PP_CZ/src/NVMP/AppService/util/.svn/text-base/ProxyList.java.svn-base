package NVMP.AppService.util;

import java.util.HashMap;

import org.misc.log.LogUtil;

import NVMP.AppService.Interface.IAppRuntime;

/** 
 * by zzw
 * 
 * ÓÃÀŽŽæŽ¢ŽúÀíÀàµÄ£¬Ã¿ŽÎÔËÐÐÊ±ºòœøÐÐŒÓÔØ
 * 
 * */
public class ProxyList {

	private HashMap<String, Object> proxyHp;

	public ProxyList() {
		proxyHp = new HashMap<String, Object>();
	}

	/** ÅÐ¶ÏŽúÀíÊÇ·ñÒÑŸ­ÓÐŽæÔÚ */
	public boolean IsExist(String domain) {
		return proxyHp.containsKey(domain);
	}

	/** ·ÅÈëŽúÀí */
	public boolean addProxy(String domain, Object obj) {
		if (!IsExist(domain)) {
			proxyHp.put(domain, obj);
			return true;
		} else {
			return false;
		}

	}

	/** È¡³öŽúÀí */
	public Object foundProxy(String domain) {
		if (IsExist(domain)) {
			return proxyHp.get(domain);
		} else {
			return null;
		}

	}

	/** ÒÆ³ýŽúÀí */
	public boolean removeProxy(String domain) {
		if (IsExist(domain)) {
			proxyHp.remove(domain);
			return true;
		} else {
			return false;
		}

	}

	/** Éú³ÉŽúÀíÊµÀý£¬²¢ÇÒŒÓÔØ */
	public void loadProxy(String domain,IAppRuntime ia) {
		String className = "NVMP.Proxy." + domain+"Proxy";
		try {
		

		if (IsExist(domain)) {
//			LogUtil.debug("ŽæÔÚŽúÀíÀà" + className + ",²»ÐèÒªŒÓÔØ");
			Object o= this.foundProxy(domain);
			o.getClass().getMethod("load").invoke(o, ia);
			
		} else {

		
				Class<?> c = Class.forName(className);
				Object o = c.newInstance();
				o.getClass().getMethod("load",IAppRuntime.class).invoke(o, ia);
				//this.addProxy(domain, c.newInstance());
				LogUtil.debug("加载代理" + className + ",成功");


		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.error("ŽúÀíÀà" + className + ",ŒÓÔØÊ§°Ü£º"+e.getMessage());
		}
	}

}
