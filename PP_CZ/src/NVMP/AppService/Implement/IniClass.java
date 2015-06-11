package NVMP.AppService.Implement;

public class IniClass
{
	// 声明INI文件的写操作函数 WritePrivateProfileString()

	private static native long WritePrivateProfileString(String section, String key, String val, String filePath);
	static 
	{
		//System.loadLibrary("kernel32");
	}

	// 声明INI文件的读操作函数 GetPrivateProfileString()

	private static native int GetPrivateProfileString(String section, String key, String def, StringBuilder retVal, int size, String filePath);


	private String sPath = null;
	public IniClass(String path)
	{
		this.sPath = path;
	}

	public final void WriteValue(String section, String key, String value)
	{

		// section=配置节，key=键名，value=键值，path=路径

		//WritePrivateProfileString(section, key, value, sPath);

	}

	public final int ReadInt(String section, String key, int def)
   {
		String value = ReadValue(section, key);

		try
		{
			return Integer.parseInt(value);
		}
		catch(RuntimeException e)
		{
			return def;
		}
	}

	public final String ReadValue(String section, String key)
	{

		// 每次从ini中读取多少字节

		StringBuilder temp = new StringBuilder(255);

		// section=配置节，key=键名，temp=上面，path=路径

		//GetPrivateProfileString(section, key, "", temp, 255, sPath);

		return temp.toString();

	}


}
