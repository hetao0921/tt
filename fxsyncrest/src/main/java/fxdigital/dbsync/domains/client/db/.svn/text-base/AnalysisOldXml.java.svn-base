package fxdigital.dbsync.domains.client.db;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fxdigital.syncclient.util.JdbcImpl;

public class AnalysisOldXml {
	
	public boolean analysis(String xml, String centerid) throws Exception {
		boolean result=false;
		List<String> sqls = new ArrayList<String>();
		Document doc = null;
		String path = "";
		try {

			doc  = DocumentHelper.parseText(xml);
			// doc = read.read(xml);
		} catch (Exception e) {

			e.printStackTrace();
		}
		Element root = doc.getRootElement();
	/*	Attribute attribute= root.attribute("version");
		if("1.0".equals(attribute)){
			
		}*/
		
		@SuppressWarnings("unchecked")
		List<Element> tabls = root.elements();
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < tabls.size(); i++) {
			names.add(tabls.get(i).getName());
		}

		// 添加删除表的语句
		for (int j = 0; j < names.size(); j++) {
			String sql = "delete from nvmp." + names.get(j) + " where centerid='"
					+ centerid + "'";
			sqls.add(sql);
		}

		for (int i = 0; i < names.size(); i++) {
			Element table = root.element(names.get(i));
			List<String> columns = new ArrayList<String>();
			List<String> types = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<Element> co = table.element("ColumnType").elements();
			for (int j = 0; j < co.size(); j++) {
				columns.add(co.get(j).getName());
				types.add(co.get(j).getText());
			}
			List<List<String>> records = new ArrayList<List<String>>();

			int temp = -1;

			List<Element> re = table.elements("Record");
			for (int j = 0; j < re.size(); j++) {
				Element r = re.get(j);
				@SuppressWarnings("unchecked")
				List<Element> rel = r.elements();
				List<String> record = new ArrayList<String>();
				for (int k = 0; k < rel.size(); k++) {
					String msg = rel.get(k).getText();

					if (types.get(k).equals("java.lang.String")) {
						if(msg == null || msg.equals("null") || ("''"
								.equals(msg))){
							record.add("''");
						}else{
							record.add("'" + msg + "'");
						}
						
					} else if (types.get(k).equals("java.lang.Integer")
							&& (msg == null || msg.equals("null") || ("''"
									.equals(msg)))) {
						record.add("0");
					} else if (types.get(k).equals("java.sql.Timestamp")
							&& (msg == null || msg.equals("null")||("''".equals(msg)))) {
						record.add(null);
						temp = k;
					} else if (types.get(k).equals("java.sql.Timestamp")
							&& msg != null) {
						record.add("'" + msg + "'");
					}else {
						record.add(msg);
					}
				}
				records.add(record);
			}

			String sql = "insert into nvmp." + names.get(i) + "(";
			for (int j = 1; j < columns.size(); j++) {
				if (j == (columns.size() - 1)) {
					if (j == temp) {
						sql += ") values(";
					} else {
						sql += columns.get(j) + ") values(";
					}

				}

				else {
					if (j == temp) {
						// sql +=") values(";
					} else {
						sql += columns.get(j) + ",";
					}

				}

			}
			for (int j = 0; j < records.size(); j++) {
				String sqq = sql;
				List<String> record = records.get(j);
				for (int k = 1; k < record.size(); k++) {
					if (k == (record.size() - 1)) {
						if (temp == k) {
							sqq += ")";
						} else {
							sqq += record.get(k) + ")";
						}
					}

					else {
						if (temp == k) {
							// sqq += ")";
						} else {
							sqq += record.get(k) + ",";
						}
					}

				}
				sqls.add(sqq);
			}
		}
		
		try {
			result=	JdbcImpl.getJdbcImpl().updateSqls(sqls);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return result;
	
	}
	

}
