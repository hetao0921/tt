package com.sqlite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.sqlite.conn.DBConne;
import com.sqlite.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {

	private DBConne dbc; 
	private Connection conn = null;
//	private Statement stat = null;
//	private PreparedStatement pst = null;
//	private ResultSet rs = null;

	public BaseDaoImpl() {
		dbc = DBConne.getDbConne();
	}
	
	public void setDbName(String name){
		dbc.setDbName(name);
	}

	public boolean runUpdateSql(String sql, Object[] params) {
		PreparedStatement pst = null;
		try {
			conn = dbc.getConn();
			// System.out.println("conn:"+conn);
			// System.out.println("sql:"+sql);
			pst = conn.prepareStatement(sql);
			// 设置参数
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
			}
			// 获取resultSet对象
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {

				pst.close();
				// conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				dbc.closeConn();
			}
		}
	}

	/**
	 * 根据SQL语句对表中数据增删改操作
	 * 
	 * @param sql
	 * @return
	 */
	public boolean runUpdateSql(String sql) {
		boolean result = false;
		Statement stat = null;
		try {
			conn = dbc.getConn();
			stat = conn.createStatement();
			// 获取resultSet对象
			stat.execute(sql);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
				dbc.closeConn();
			}
		}
		return result;
	}

	// 运行没有结果，有参数的sql语句
	public ResultSet runSelectSql(String sql, Object[] params) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		// Result result=null;
		try {
			con = dbc.getConn();
			ps = con.prepareStatement(sql);
			// 设置参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 获取resultSet对象
			res = ps.executeQuery();
			// 通过ResultSupport对象toResult方法来获取result对象
			// result=ResultSupport.toResult(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				ps.close();
				// con.close();
			} catch (Exception e) {
				e.printStackTrace();
				dbc.closeConn();
			}
		}
		return res;
	}

	public boolean updateInfo(String sql) {
		String[] sqls = { sql };
		return updateInfo(sqls);
	}

	@Override
	public boolean updateInfo(String[] sqls) {
		boolean result = false;
		for (int i = 0; i < sqls.length; i++) {
			result = runUpdateSql(sqls[i]);
		}
		return result;
	}

	public boolean initInsert(String[] sqls) {
		boolean result = false;
		DBConne db = DBConne.getDbConne();
		Connection conne = null;
		Statement stat = null;
		try {
			conne = db.getConn2();
			stat = conne.createStatement();
			// �����������,��ֹ�Զ��ύ�����ûع���
//			conne.setAutoCommit(false);
			// ������ɾ��
			if (sqls.length > 0) {
				for (int i = 0; i < sqls.length; i++) {
					stat.executeUpdate(sqls[i]);
				}
			}
			// �ύ����
//			conne.commit();
			result = true;
		} catch (SQLException e) {
			result = false;
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			// �ر�����
			try {
				if(stat!=null)
					stat.close();
				
				// conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				db.setConn2Null();
			}
		}
		return result;
	}

	@Override
	public int getIntMsg(String sql) {
		int status = -1;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				status = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ر�����
			try {
				if(rs!=null)
				rs.close();
				if(stat!=null)
				stat.close();
				// conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dbc.closeConn();
			}
		}
		return status;
	}

	@Override
	public String getStringMsg(String did) {
		String status = "";
		String sql = "select SwitchSvrID from DeviceStatus where DeviceID='"
				+ did + "'";
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				status = rs.getString("SwitchSvrID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ر�����
			try {
				if(rs!=null)
					rs.close();
					if(stat!=null)
					stat.close();
				// conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dbc.closeConn();
			}
		}
		return status;
	}

//	/**
//	 * 根据SQL语句，获取一个ResultSet对象
//	 * 
//	 * @param sql
//	 * @return
//	 */
//	public ResultSet getInfo(String sql) {
//		try {
//			conn = dbc.getConn();
//			Statement stat = conn.createStatement();
//			if (!sql.equals("")) {
//				rs = stat.executeQuery(sql);
//			}
//			stat.close();
//			// System.out.println("ResultSet:"+rs+"          ���е�ֵ:"+rs.next());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rs;
//	}

//	/**
//	 * 关闭所有连接
//	 */
//	public void closeAll() {
//		try {
//			if (rs != null)
//				rs.close();
//			if (stat != null)
//				stat.close();
//			// conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			dbc.closeConn();
//		}
//	}

	/**
	 * 判断内存数据库中的表是否存在
	 * 
	 * @param sql
	 * @return
	 */
	public int getTableIsExists(String sql) {
		// dbc = new DBConne();
		int status = -1;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				status = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ر�����
			try {
				if(rs!=null)
					rs.close();
					if(stat!=null)
					stat.close();
				// conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dbc.closeConn();
			}
		}
		return status;
	}

	// public boolean updateInfo(String sql,Object[]params){
	// boolean result=false;
	// try {
	// conn=dbc.getConn();
	// //设置回滚点
	// // conn.setAutoCommit(false);
	// pst=conn.prepareStatement(sql);
	// //设置PreparedStatement的参数值
	// if(params.length>0){
	// for(int i = 0;i<params.length;i++){
	// // DeviceStatus d = list.get(i);
	// //通过反射，获取参数的类型
	// String type = params[i].getClass().getSimpleName();
	// if(type.equals("Integer")){
	// pst.setInt(i+1, Integer.parseInt(params[i].toString()));
	// }else if(type.equals("String")){
	// pst.setString(i+1, params[i].toString());
	// }
	// }
	// }
	// pst.execute();
	// result=true;
	// } catch (SQLException e) {
	// result=false;
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }finally{
	// //�ر�����
	// try {
	// stat.close();
	// conn.close();
	// //conn.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }
	//
	// public boolean updateInfo(String sql,DeviceStatus d){
	// String [] sqls = {sql};
	// List<DeviceStatus> list = new ArrayList<DeviceStatus>();
	// return updateInfo(sqls, list);
	// }
	//
	// public boolean updateInfo(String[] sqls,List<DeviceStatus> list) {
	// boolean result=false;
	// try {
	// conn=dbc.getConn();
	// //设置回滚点
	// conn.setAutoCommit(false);
	// if(list.size()>0){
	// for(int i = 0;i<list.size();i++){
	// DeviceStatus d = list.get(i);
	// pst=conn.prepareStatement(sqls[i]);
	// pst.setString(1,d.getDevcieId());
	// pst.setString(2, d.getDevName());
	// pst.setString(3, d.getDevIp());
	// pst.setString(4, d.getDevMask());
	// pst.setString(5, d.getDevGate());
	// pst.setString(6, d.getDevMAC());
	// pst.setInt(7, d.getDevPort());
	// pst.setString(8, d.getUserName());
	// pst.setString(10, d.getPassword());
	// pst.setInt(11, d.getDevType());
	// pst.setInt(12, d.getDevSubType());
	// pst.setString(11, d.getDevModel());
	// pst.setString(12, d.getDevSN());
	// pst.setString(13, d.getDevVer());
	// pst.setString(14, d.getAreaID());
	// pst.setInt(15, d.getCameraNum());
	// pst.setInt(16, d.getAlarmPointNum());
	// pst.setInt(17, d.getOutPutPointNum());
	// pst.setInt(18, d.getIsDisable());
	// pst.setString(19, d.getRegisterTime());
	// pst.setString(20, d.getSwitchSvrID());
	// pst.setString(21, d.getCenterID());
	// pst.setInt(22, d.getDeviceStatus());
	// pst.execute();
	// }
	// }
	//
	// //�ύ����
	// conn.commit();
	// result=true;
	// } catch (SQLException e) {
	// result=false;
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }finally{
	// //�ر�����
	// try {
	// stat.close();
	// //conn.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }
}
