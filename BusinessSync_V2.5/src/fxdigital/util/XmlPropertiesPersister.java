package fxdigital.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.PropertiesPersister;

/**
 * 加载xml或者properties文件（xml文件的文件名后缀可以不是.xml）
 * 
 * @author fucz
 * @version 2014-6-11
 */
public class XmlPropertiesPersister implements PropertiesPersister {
	
	public XmlPropertiesPersister(){
	}

	/**
	 * 预处理文件流（区分文件是xml格式还是properties格式）
	 */
	@Override
	public void load(Properties props, InputStream is) throws IOException {
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		byte[] b = new byte[10];
		int num = is.read(b);
		//读取文件前面10个字节的内容
		String top = new String(b);
		
		//is流数据已变更，需要保存原数据到另一个流is2
		os.write(b,0,num);
		while((num = is.read(b)) > 0){
			os.write(b,0,num);
		}
		InputStream is2 = new ByteArrayInputStream(os.toByteArray());
		
		//判断文件是否是xml格式
		if(top.contains("<?xml")){
			loadFromXml(props,is2);
		}else{
			//以properties文件的格式解析
			props.load(is2);
		}
	}

	@Override
	public void load(Properties arg0, Reader arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * 以xml格式解析文件流
	 */
	@Override
	public void loadFromXml(Properties props, InputStream is)
			throws IOException {
		if (is == null)
			throw new NullPointerException();
		Document doc = null;
		SAXReader saxReader = new SAXReader();
		try {
			doc = saxReader.read(is);
			Element root = doc.getRootElement();
			loadAll(props,root.getName(),root);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		is.close();
	}
	
	//使用递归方法解析xml所有节点
	@SuppressWarnings({ "rawtypes" })
	private void loadAll(Properties props,String parents,Element root){
		for (Iterator j = root.elementIterator(); j.hasNext();) {
			Element node = (Element) j.next();
			//元素值
			props.put(parents+"."+node.getName(), node.getTextTrim());
			for(Iterator i = node.attributeIterator(); i.hasNext();){
				Attribute a = (Attribute) i.next();
				//元素的属性值
				props.put(parents+"."+node.getName()+":"+a.getName(), a.getText());
			}
			loadAll(props,parents+"."+node.getName(),node);
		}
	}

	@Override
	public void store(Properties arg0, OutputStream arg1, String arg2)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(Properties arg0, Writer arg1, String arg2)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeToXml(Properties arg0, OutputStream arg1, String arg2)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeToXml(Properties arg0, OutputStream arg1, String arg2,
			String arg3) throws IOException {
		// TODO Auto-generated method stub

	}
	

}
