package NVMP.SyncDomain;

import java.io.RandomAccessFile;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class JdbcToXml {

	public String getXml(String centerid) throws SQLException {
		DBConn db = DBConn.getDBConn();
		List<String[]> list = null;
		String xml = "";
		xml += "{TableMsg}"; 
		//��ȡ��Ҫ��ѯ�ı����
		list = db.executeQueryXml("select * from nvmp_datasynctab where SyncFlag = 1");
		String [] tableNames = new String[list.size()-2];
//		xml += "{TableName}";
		for(int i =2;i<list.size();i++){
			tableNames[i-2] = list.get(i)[1];
//			xml += "{table}"+tableNames[i-2]+"{/table}";
		}
//		xml += "{/TableName}";
		//��ѯÿһ��������ת��xml��ʽ
		for(int i =0;i<tableNames.length;i++){
			list = db.executeQueryXml("select * from "+tableNames[i]+" where centerid='"+centerid+"'");
			//��xml����ӱ���
			xml += "{"+tableNames[i]+"}";
			//��ȡ���е�������ӵ�XML��
//			List<String> columns = new ArrayList<String>();
			String [] columnNames = list.get(0);
//			xml += "{ColumnName}";
//			for(int j=0;j<columnNames.length;j++){
//				
//				xml += "{name"+j+"}"+columnNames[j]+"{/name"+j+"}";
//			}
//			xml += "{/ColumnName}";
			//��ȡ���е�������ӵ�XML��
			xml += "{ColumnType}";
			String [] types = list.get(1);
			for(int j = 0;j<types.length;j++)
				xml += "{"+columnNames[j]+"}"+types[j]+"{/"+columnNames[j]+"}";
			xml += "{/ColumnType}";
			//��ȡ���е���ݣ���ӵ�XML��
			for(int j =2;j<list.size();j++){
				xml += "{Record}";
				String []msg = list.get(j);
				for(int k = 0;k<msg.length;k++){
					xml += "{"+columnNames[k]+"}"+msg[k]+"{/"+columnNames[k]+"}";
				}
				xml += "{/Record}";
			}
			xml +="{/"+tableNames[i]+"}";
		}
		xml += "{/TableMsg}";
		return xml;
	}
	
	/**
	 * ��XMLת����insert���
	 * @param xml
	 */
	public List<String> xmlToInsert(String xml,String centerid){
		List<String> sqls = new ArrayList<String>();
		SAXReader read = new SAXReader();
		Document doc = null;
		String path  = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/etc/sync"+centerid+".xml";
		} else {
			// System.out.println("==============="+System.getProperty("user.dir"));
			path = "c:\\sync"+centerid+".xml";
		}
		wirteXml(path,xml);
		try{
			
			doc = read.read(path);
//			doc = read.read(xml);
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
		Element root = doc.getRootElement();
		//��ȡxml�ļ��еı����
		@SuppressWarnings("unchecked")
		List<Element> tabls = root.elements();
		List<String> names = new ArrayList<String>();
		for(int i=0;i<tabls.size();i++){
			names.add(tabls.get(i).getName());
//			System.out.println(names.get(i));
		}
		
		//添加删除表的语句
		for(int j=0;j<names.size();j++){
			String sql = "delete from "+names.get(j)+" where centerid='"+centerid+"'";
			sqls.add(sql);
		}
		
		//ѭ����ȡÿ�������Ϣ
		for(int i=0;i<names.size();i++){
			Element table = root.element(names.get(i));
			//��ȡ���ÿһ�����������
			List<String> columns = new ArrayList<String>();
			List<String> types = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<Element> co = table.element("ColumnType").elements();
			for(int j = 0;j<co.size();j++){
				columns.add(co.get(j).getName());
				types.add(co.get(j).getText());
			}
			//��ȡ���е����м�¼��
			List<List<String>> records = new ArrayList<List<String>>();
			
			int temp = -1;
			
			@SuppressWarnings("unchecked")
			List<Element> re = table.elements("Record");
			for(int j = 0;j<re.size();j++){
				Element r = re.get(j);
				@SuppressWarnings("unchecked")
				List<Element> rel = r.elements();
				List<String> record = new ArrayList<String>();
				for(int k =0;k<rel.size();k++){
					String msg = rel.get(k).getText();
					if(types.get(k).equals("java.lang.String")){
						record.add("'"+msg+"'");
					}else if(types.get(k).equals("java.sql.Timestamp") && (msg==null || msg.equals("null"))){
						record.add("''");
						temp = k;
					}else if(types.get(k).equals("java.sql.Timestamp") && msg!=null){
						record.add("'"+msg+"'");
					}else{
						record.add(msg);
					}
				}
				records.add(record);
			}
			
			//����SQL���
			String sql = "insert into "+names.get(i)+"(";
			for(int j = 1;j<columns.size();j++){
				if(j==(columns.size()-1)){
					if(j==temp){
						sql +=") values(";
					}else{
						sql += columns.get(j)+") values(";
					}
					
				}
					
				else{
					if(j==temp){
//						sql +=") values(";
					}else{
						sql += columns.get(j)+",";
					}
					
					
				}
					
			}
			for(int j=0;j<records.size();j++){
				String sqq = sql;
				List<String> record = records.get(j);
				for(int k=1;k<record.size();k++){
					if(k==(record.size()-1)){
						if(temp==k){
							sqq += ")";
						}else{
							sqq += record.get(k)+")";
						}
					}
						
					else{
						if(temp==k){
//							sqq += ")";
						}else{
							sqq += record.get(k)+",";
						}
					}
						
				}
				sqls.add(sqq);
			}
		}
		return sqls;
	}
	
	public void wirteXml(String address,String xml){
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
//	public static void main(String[]args){
//		JdbcToXml jdbc = new JdbcToXml();
//		List<String> sqls = jdbc.xmlToInsert("C:\\sync.xml", "eace166c@001");
//		for(int i=0;i<sqls.size();i++){
//			System.out.println(sqls.get(i));
//		}
//			
//		JdbcImpl jdb = JdbcImpl.getJdbcImpl();
//		boolean re = jdb.updateSqls(sqls);
//		
//		System.out.println("执行事物完毕，结果："+re);
//	}
//	
//	public static void testInsert(){
//		JdbcToXml j = new JdbcToXml();
//		List<String> sql = j.xmlToInsert("c://a.xml");
////		System.out.println("���ϣ�");
//		for(String s:sql)
//			System.out.println(s);
//	}
	
//	public static void testXml(){
//		JdbcToXml j = new JdbcToXml();
//		try {
//			String s = j.getXml();
//			String ss = s.replace("{", "<");
//			ss = ss.replace("}", ">");
////			System.out.println("����ݿ��ȡ��ݳɹ�");
//			RandomAccessFile raf = new RandomAccessFile("c://a.xml","rwd");
//			raf.write(ss.getBytes("utf-8"));
//			raf.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("д���ļ��ɹ�");
//	}
}
