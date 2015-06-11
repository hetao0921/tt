package NVMP.AppService.Implement;
import NVMP.AppService.Interface.*;

import java.net.URL;
import java.net.URLClassLoader;
import javassist.ClassPool;
import java.util.ArrayList;
import java.io.File;
import java.lang.reflect.Field;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.*;
import javassist.ClassPool;


public class JComLoader implements IComLoader
{
	class JComClassLoader extends URLClassLoader
	{
		public JComClassLoader(URL[] urls)
		{
			super(urls);
		}
 
		public void addURL(URL url)
		{
			super.addURL(url);
		}
	}

	private static JComClassLoader classLoader = null;

    public ArrayList<String> listJarFile(String jarPathName) throws Exception
	{
		ArrayList<String> classList = new ArrayList<String>();
        JarFile jarFile = new JarFile(jarPathName);
        Enumeration<JarEntry> jarEntrys = jarFile.entries();
		String CLASS = ".class";
        while(jarEntrys.hasMoreElements())
		{
            JarEntry jarEntry = jarEntrys.nextElement();
			String name = jarEntry.getName();

			try{
				if (!name.substring(name.length() - CLASS.length()).equals(CLASS))
					continue;
				name = name.substring(0, name.length() - CLASS.length()); 
				name = name.replace('/', '.');
			}catch(Exception e)
			{
				continue;
			}
			//System.out.println("listJarFile: " + name);
            classList.add(name);
        }

		return classList;
    }

	private void loadComponent(String comPath) throws Exception
	{
		if (classLoader == null)
		{
			synchronized(JComLoader.class)
			{
				if (classLoader == null)
				{
            		URL[] url = {new URL("file:" + comPath)};
            		classLoader = new JComClassLoader(url);
					return;
				}

			}
		}
		classLoader.addURL(new URL("file:" + comPath));
	}

		

	private boolean isImplementInterface(Class<?> a,String name)
	{
		for(Class<?> c : a.getInterfaces())
		{
			if (c.getSimpleName().equals(name))
				return true;
		}	
		return false;
	}

	@SuppressWarnings("rawtypes")
	private ArrayList<IBusinessDomain> LoadBusinessComponentImpl(String comPath, String url)
	{
		ArrayList<IBusinessDomain> BusinessDomains = new ArrayList<IBusinessDomain>();

		try {
			System.out.println("appendClassPath: " + comPath);		
			ClassPool.getDefault().appendClassPath(comPath);
			
	
			//loadComponent(comPath);
			ArrayList<String> classList = listJarFile(comPath);

			Iterator<String> iter = classList.iterator();
			while(iter.hasNext())
			{
				String className = iter.next();
				try{
					//Class c = classLoader.loadClass(className);	
					Class c = Class.forName(className);
					System.out.println(className);

					if (isImplementInterface(c,"IBusinessDomain"))
					{
						System.out.println("BusinessDomain: " + className);
						IBusinessDomain domain = (IBusinessDomain)c.newInstance();
					
						if (url.length() > 0)
						{
							if (domain.GetDomainName().equals(url))
							{
								BusinessDomains.add(domain);
								break;
							}
						}
						else
						{
							BusinessDomains.add(domain);
						}
					}

				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return BusinessDomains;
		
	}


	public final IBusinessDomain LoadBusinessComponent(String comPath, String url)
	{
		url = url.trim();
		if (url.length() == 0)
			return null;

		ArrayList<IBusinessDomain> BusinessDomains = LoadBusinessComponentImpl(comPath, url);

		return (BusinessDomains.size() == 0)?null:BusinessDomains.get(0);
	}

	public final ArrayList<IBusinessDomain> LoadBusinessComponent(String comPath)
	{
		return (ArrayList<IBusinessDomain>)LoadBusinessComponentImpl(comPath, "");
	}
}







