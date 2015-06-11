package NVMP.Command.Business;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.jws.WebMethod;

import org.misc.RefObject;

import com.fxdigital.bean.NvmpAreainfotab;
import com.fxdigital.bean.NvmpCommandteamtab;
import com.fxdigital.bean.NvmpNetmeetingtab;
import com.mysql.impl.ConvertMapUtil;
import com.mysql.impl.Hibernate;




public class DBAccess {

	static public void InitDB()// Ӧ�÷���������ʱ��ʼ����ݿ�����
	{
		// �������û�����Ϊ������״̬

		String strsql;
		strsql = "update NvmpUserinfotab set loginStatus=0";

		try {
			Hibernate.getHibernate().deleteOrUpdate(strsql, null);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	static public Boolean SetCommanderLogin(String UserID, boolean isLogin) {
		{	
			Boolean flag=true;
			String strsql;
			if (isLogin) {
				strsql = "update NvmpUserinfotab set loginStatus=1 where userId='"
						+ UserID + "'";
			} else {
				strsql = "update NvmpUserinfotab set loginStatus=0 where userId='"
						+ UserID + "'";
			}
			try {
				// ConnDoClose cdc = new ConnDoClose();
				flag = Hibernate.getHibernate().deleteOrUpdate(strsql, null);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag=false;
			}
			return flag;
		}

	}

	static public void EnterFree(String CommanderId) {
		try {

			String strSql = "update NvmpCommanddevtab set workStatus= 0 , commandTeamId = '"
					+ 0 + "'where clientUserId= '" + CommanderId + "'";

			Hibernate.getHibernate().deleteOrUpdate(strSql, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static public void ClearCommmandDevUser(String DeviceId) {
		String strsql = "update NvmpUserinfotab nu, NvmpCommanddevtab nc set nu.loginStatus = 0 where nu.UserId= nc.clientUserId and nc.deviceId ='"
				+ DeviceId + "'";

		try {
			Hibernate.getHibernate().deleteOrUpdate(strsql, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public void AddConference(String ConferenceId,
			String ConferenceName, String Description, String Chainman,
			int curConferenceType) {
		NvmpNetmeetingtab nm=new NvmpNetmeetingtab();
		nm.setMeetingId(ConferenceId);
		nm.setMeetingName(ConferenceName);
		nm.setMeetingDesc(Description);
		nm.setMeetingType(curConferenceType);
		nm.setMeetingSponsor(Chainman);
		nm.setMeetingSubject(ConferenceName);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		nm.setStartTime(d);
		nm.setMeetingStatus(1);
/*		String strSql = "insert into nvmp_netmeetingtab (MeetingID,MeetingName,MeetingDesc,MeetingType,MeetingSponsor,StartTime,MeetingSubject,MeetingStatus) values ('"
				+ ConferenceId
				+ "','"
				+ ConferenceName
				+ "','"
				+ Description
				+ "',"
				+ curConferenceType
				+ ",'"
				+ Chainman
				+ "',Now(),'"
				+ ConferenceName + "',1)";*/
		try {
		Hibernate.getHibernate().save(nm);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static public void ConferenceOver(String ConferenceId) {
		NvmpNetmeetingtab nn=new NvmpNetmeetingtab();
		nn.setMeetingId(ConferenceId);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		nn.setEndTime(d);
		nn.setMeetingStatus(2);
//		String strSql = "update NvmpNetmeetingtab set endTime=Now() ,meetingStatus = 2 where  meetingId='"
//				+ ConferenceId + "'";
		try {
			Hibernate.getHibernate().updateObject(nn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public void EnterCommandGroup(String CommandGroupId,
			String CommanderId, Boolean isEnter) {
		String strSql;
		try {
			if (isEnter == true) {

				strSql = "update NvmpCommanddevtab set workStatus= 1 ,commandTeamId = '"
						+ CommandGroupId
						+ "' where clientUserId= '"
						+ CommanderId + "'";
			} else {

				strSql = "update NvmpCommanddevtab set workStatus= 0 , commandTeamId = '"
						+ 0 + "'where clientUserId= '" + CommanderId + "'";
			}

//			int Result = ConnDo.getConnDo().executeUpdate(strSql.toString());
			Hibernate.getHibernate().deleteOrUpdate(strSql, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * ���û����������� *
	 */

	static public void EnterConference(String commenderId, String ConferenceId,
			boolean IsEnter) {
		String strSql;
		try {
			if (IsEnter == true) {

				strSql = "update NvmpCommanddevtab set workStatus= 2 ,commandTeamId = '"
						+ ConferenceId
						+ "' where clientUserId= '"
						+ commenderId + "'";
			} else {

				strSql = "update NvmpCommanddevtab set workStatus= 0  where commandTeamId= '"
						+ commenderId
						+ "' and commandTeamId = '"
						+ ConferenceId + "'";
			}

//			int Result = ConnDo.getConnDo().executeUpdate(strSql.toString());
			Hibernate.getHibernate().deleteOrUpdate(strSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * �����û����������
	 * */

	static public void EnterConsultation(String CommanderId,
			String ConsultationId, boolean IsEnter) {
		String strSql;
		try {
			if (IsEnter == true) {

				strSql = "update NvmpCommanddevtab set workStatus=3 , commandTeamId = '"
						+ ConsultationId
						+ "' where clientUserId= '"
						+ CommanderId + "'";
			} else {

				strSql = "update NvmpCommanddevtab set workStatus=0 where clientUserId= '"
						+ CommanderId
						+ "' and CommandTeamID = '"
						+ ConsultationId + "'";
			}

//			int Result = ConnDo.getConnDo().executeUpdate(strSql);
			Hibernate.getHibernate().deleteOrUpdate(strSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 对所有的数据进行下线
	 * */

	/**
	 * 判断该设备是否属于
	 * */
	static public boolean IsCommandDevice(String clientid) {
		boolean b = false;
		String strsql = " from NvmpServeripconfigtab where deviceType='FXH8060' and deviceId = '"
				+ clientid + "'";
		try {
			if (Hibernate.getHibernate().createQueryObjectArray(strsql).size() > 0)
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	/*
	 * �����û���¼ *
	 */

	static public void SetCommanderLogin(String TerminalId, String CommanderId,
			String TerminalIP, boolean IsOnline) {
		String strsql = "";
		if (IsOnline) {
			strsql = "update NvmpCommanddevtab set devIp='"
					+ TerminalIP
					+ "',isOnline= 1,WorkStatus= 0,CommandStatus= 0, conferenceStatus= 0,consultationStatus= 0,clientUserId='"
					+ CommanderId + "' where deviceId='" + TerminalId + "'";
		} else {
			strsql = "update NvmpCommanddevtab set isOnline= 0,WorkStatus= 0,CommandStatus= 0, conferenceStatus= 0,consultationStatus= 0,clientUserId='"
					+ 0 + "' where deviceId='" + TerminalId + "'";
		}
		try {

			Hibernate.getHibernate().deleteOrUpdate(strsql, null);
			System.out.println("������SetCommanderLogin��" + strsql);
			SetCommanderLogin(CommanderId, IsOnline);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ���û���ķ�����
	 * */

	static public void SetSpokeman(String conferenceId, String Spokeman) {
		// TODO Auto-generated method stub
		String strsql = "update NvmpNetmeetingtab set spokesmanId='"
				+ Spokeman + "' where meetingId='" + conferenceId + "'";
		try {

			Hibernate.getHibernate().deleteOrUpdate(strsql, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public void SetCommanderWordMode(String SendCommanderId,
			String DestCommanderId, CommanderWorkMode workMode) {

	}

	/*
	 * ���û���Ϊ����ģʽ * *
	 */

	static public void SetDiscuss(String ConferenceId, boolean IsStart) {

		String strsql = "";
		if (IsStart) {
			strsql = "update NvmpNetmeetingtab set isDiscussMode=0 where meetingId='"
					+ ConferenceId + "'";
		} else {
			strsql = "update NvmpNetmeetingtab set isDiscussMode=1 where meetingId='"
					+ ConferenceId + "'";
		}
		try {

			Hibernate.getHibernate().deleteOrUpdate(strsql, null);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public void GetAllCommanderFromDB(SystemBusiness systemBusiness) {
		java.util.List<HashMap<String, String>> getAllPerson = GetAllPerson();
		if (getAllPerson.size() > 0) {
			for (int i = 0; i < getAllPerson.size(); i++) {
				HashMap<String, String> hm = (HashMap<String, String>) getAllPerson
						.get(i);
				// String hmValue = hm.get("CommandStatus").toString();
				systemBusiness.AddCommander(new Commander(hm.get("UserID")
						.toString(), hm.get("UserNickName").toString(), hm.get(
						"DeviceID").toString(), "", CommanderWorkMode.General,
						CommanderWorkStatus.Free, false));
			}
		}

	}

	static public void GetAllCommandGroupFromDB(SystemBusiness systemBusiness) {
		java.util.List<HashMap<String, String>> getAllCommandGroupFromDB = GetCommandGroup();
		if (getAllCommandGroupFromDB.size() > 0) {
			for (int i = 0; i < getAllCommandGroupFromDB.size(); i++) {
				HashMap<String, String> hm = (HashMap<String, String>) getAllCommandGroupFromDB
						.get(i);
				CommandGroup cg = new CommandGroup(hm.get("TeamID").toString(),
						hm.get("TeamName").toString(), hm.get("TeamDesc")
								.toString());
				systemBusiness.AddCommandGroup(cg);
				java.util.List<HashMap<String, String>> getCommandGrouper = GetCommandGrouper(cg
						.getId());
				for (int j = 0; j < getCommandGrouper.size(); j++) {
					HashMap<String, String> hmgetCommandGrouper = (HashMap<String, String>) getCommandGrouper
							.get(j);
					java.util.List<HashMap<String, String>> hmLast = GetCommandGroupeRelation(
							hmgetCommandGrouper.get("NodeID").toString(),
							Integer.parseInt(hmgetCommandGrouper
									.get("NodeType").toString()));
					if (hmLast.size() > 0) {
						for (int k = 0; k < hmLast.size(); k++) {
							if (Integer.parseInt(hmgetCommandGrouper.get(
									"NodeType").toString()) == 2) {
								Relation aRelation = new Relation();
								aRelation.UpNodeId = hmgetCommandGrouper.get(
										"ParentNodeID").toString();
								aRelation.NodeId = hmgetCommandGrouper.get(
										"NodeID").toString();
								aRelation.Type = NodeType.Commander;
								aRelation.Level = 1;
								aRelation.Name = hmLast.get(0).toString();
								cg.AddCommandRelation(aRelation);
								Commander com = null;
								if (systemBusiness.GetCommanderById(
										hmgetCommandGrouper.get("NodeID")
												.toString(),
										new RefObject<Commander>(com))) {
									cg.AddCommander(com);
								}

							} else if (Integer.parseInt(hmgetCommandGrouper
									.get("NodeType").toString()) == 1) {
								Relation aRelation = new Relation();
								aRelation.UpNodeId = hmgetCommandGrouper.get(
										"ParentNodeID").toString();
								aRelation.NodeId = hmgetCommandGrouper.get(
										"NodeID").toString();
								aRelation.Type = NodeType.Org;
								aRelation.Level = 1;
								aRelation.Name = hmLast.get(0).toString();
								cg.AddCommandRelation(aRelation);
							}
						}
					}
				}
			}
		}
	}

	static public void GetAllConferenceGroupFromDB(SystemBusiness systemBusiness) {
		java.util.List<HashMap<String, String>> getListConferenceGroup = GetListConferenceGroup();
		Commander aCommander = null;
		if (getListConferenceGroup.size() > 0) {
			for (int i = 0; i < getListConferenceGroup.size(); i++) {
				HashMap<String, String> hm = (HashMap<String, String>) getListConferenceGroup
						.get(i);
				int hmValue = Integer.parseInt(hm.get("MeetingStatus"));
				if (systemBusiness.GetCommanderById(hm.get("MeetingSponsor")
						.toString(), new RefObject<Commander>(aCommander))) {

					ConferenceGroup cg = new ConferenceGroup(
							hm.get("MeetingID").toString(),
							hm.get("MeetingName").toString(),
							hm.get("MeetingDesc").toString(),
							aCommander,
							hm.get("SpokesmanID").toString(),
							hm.get("IsDiscussMode").toString() == "0" ? true
									: false,
							hm.get("MeetingType").toString() == "0" ? ConferenceType.Plan
									: ConferenceType.Temp,
							ConferenceStatus.Finished);
					systemBusiness.AddConferenceGroup(cg);
					java.util.List<HashMap<String, String>> getConferenceGrouper = GetConferenceGrouper(cg
							.getId());
					if (getConferenceGrouper.size() > 0) {

						for (int j = 0; j < getConferenceGrouper.size(); j++) {
							HashMap<String, String> hmNext = (HashMap<String, String>) getConferenceGrouper
									.get(j);
							Commander com = null;
							if (systemBusiness.GetCommanderById(
									hmNext.get("UserID").toString(),
									new RefObject<Commander>(com))) {
								cg.AddCommander(com);
							}
						}
					}

					// switch (hmValue) {
					// case 0:
					// ConferenceGroup cg = new
					// ConferenceGroup(hm.get("MeetingID").toString(),
					// hm.get("MeetingName").toString(),
					// hm.get("MeetingDesc").toString(), aCommander,
					// hm.get("SpokesmanID").toString(),
					// hm.get("IsDiscussMode").toString() == "0" ? true : false,
					// hm.get("MeetingType").toString() == "0" ?
					// ConferenceType.Plan : ConferenceType.Temp,
					// ConferenceStatus.Finished);
					// systemBusiness.AddConferenceGroup(cg);
					// java.util.List<HashMap<String, String>>
					// getConferenceGrouper = GetConferenceGrouper(cg.getId());
					// if(getConferenceGrouper.size()>0)
					// {
					//
					// for (int j = 0; j < getConferenceGrouper.size(); j++) {
					// HashMap<String, String> hmNext = (HashMap<String,
					// String>) getConferenceGrouper
					// .get(j );
					// Commander com=null;
					// if
					// (systemBusiness.GetCommanderById(hmNext.get("UserID").toString(),new
					// RefObject<Commander>(com)))
					// {
					// cg.AddCommander(com);
					// }
					// }
					// }
					// break;
					// case 1:
					//
					// break;
					// case 2:
					//
					// break;
					// case 3:
					//
					// break;
					// default:
					// break;
					// }
				}
			}
		}
	}

	static public void GetAllCameraGroupFromDB(SystemBusiness systemBusiness) {
		java.util.List<HashMap<String, String>> getAllCommandGroupFromDB = GetCameraGroupOrganizationNode();
		CameraGroup cg = new CameraGroup("1", "MySql", "MySql��Ʒ���");
		if (getAllCommandGroupFromDB.size() > 0) {
			for (int i = 0; i < getAllCommandGroupFromDB.size(); i++) {
				HashMap<String, String> hm = (HashMap<String, String>) getAllCommandGroupFromDB
						.get(i);
				OrganizationNode Organ = new OrganizationNode(hm.get("AreaID")
						.toString(), hm.get("AreaName").toString(), hm.get(
						"CenterID").toString(), hm.get("AreaParentID")
						.toString());
				cg.AddOrganizatinNode(Organ);
				java.util.List<HashMap<String, String>> getCameraGrouper = GetCameraGrouper(Organ
						.getParentId());
				if (getCameraGrouper.size() > 0) {
					for (int j = 0; j < getCameraGrouper.size(); j++) {
						HashMap<String, String> hmNext = (HashMap<String, String>) getAllCommandGroupFromDB
								.get(j);
						if (Integer.parseInt(hmNext.get("IsOnline").toString()) == 1) {
							Camera cam = new Camera(hmNext.get("DevName")
									.toString(), Integer.parseInt(hmNext.get(
									"DevType").toString()), hmNext.get(
									"DevModel").toString(), false, hmNext.get(
									"AreaID").toString());
							cg.AddCamera(cam);
							// cg.CameraList.Add(cam);

						} else if (Integer.parseInt(hmNext.get("IsOnline")
								.toString()) == 0) {
							Camera cam = new Camera(hmNext.get("DevName")
									.toString(), Integer.parseInt(hmNext.get(
									"DevType").toString()), hmNext.get(
									"DevModel").toString(), true, hmNext.get(
									"AreaID").toString());
							// cg.CameraList.Add(cam);
							cg.AddCamera(cam);
						}
					}
				}
			}
			systemBusiness.AddCameraGroup(cg);
		}
	}

	static public void GetAllSulatationGroupFromDB(SystemBusiness systemBusiness) {
		java.util.List<HashMap<String, String>> getListConferenceGroup = GetListConferenceGroup();
		if (getListConferenceGroup.size() > 0) {
			for (int i = 0; i < getListConferenceGroup.size(); i++) {
				HashMap<String, String> hm = (HashMap<String, String>) getListConferenceGroup
						.get(i);
				ConsultationGroup cg = new ConsultationGroup(hm
						.get("MeetingID").toString(), hm.get("MeetingName")
						.toString(), hm.get("MeetingDesc").toString());
				java.util.List<HashMap<String, String>> getConsultationGrouper = GetConsultationGrouper(cg
						.getId());
				if (getConsultationGrouper.size() > 0) {
					for (int j = 0; j < getConsultationGrouper.size(); j++) {
						HashMap<String, String> hmNext = (HashMap<String, String>) getConsultationGrouper
								.get(j);
						Commander com = null;
						if (systemBusiness.GetCommanderById(hmNext
								.get("UserID").toString(),
								new RefObject<Commander>(com))) {
							cg.AddCommander(com);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * ========================================================================
	 * ========================================
	 * 
	 * */
	/**
	 * 
	 * ��ȡ������
	 * */
	static public java.util.List<HashMap<String, String>> GetAllPerson() {
		
		String hql="select new Map(nu.userId as UserID,nu.userNickName as UserNickName,nc.deviceId as DeviceID,"
				+ "nc.workStatus as WorkStatus,nc.isOnline as IsOnline,nc.commandTeamId as CommandTeamID,"
				+ "nc.commandStatus as CommandStatus) from NvmpCommanddevtab nc,NvmpUserinfotab nu "
				+ " where nu.userId=nc.clientUserId and nu.loginStatus=1 ";
		
		
		String strsql ="select new Map( userId as UserID,userNickName as UserNickName,  "
				+ " '0' as DeviceID,0 as WorkStatus,0 as IsOnline,"
				+ " '' as CommandTeamID,0 as CommandStatus ) from NvmpUserinfotab where loginStatus=0";
		System.out.println("GetAllPerson:" + hql);
		java.util.List<HashMap<String, String>> lst;
		java.util.List<HashMap<String, String>> lst2;
		try {
			lst = Hibernate.getHibernate().createQuery(hql);
			lst2 = Hibernate.getHibernate().createQuery(strsql);
			
		
			for (HashMap<String, String> hashMap : lst2) {
				lst.add(hashMap);
			}
			// System.out.println(lst);
			return lst;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
/*	static public java.util.List<HashMap<String, String>> GetAllPerson() {
		String strsql = "(select nvmp_userinfotab.UserID,nvmp_userinfotab.UserNickName,nvmp_commanddevtab.DeviceID,"
				+ "nvmp_commanddevtab.WorkStatus,nvmp_commanddevtab.IsOnline,nvmp_commanddevtab.CommandTeamID,"
				+ "nvmp_commanddevtab.CommandStatus from nvmp_commanddevtab,nvmp_userinfotab "
				+ " where nvmp_userinfotab.UserID=nvmp_commanddevtab.ClientUserID and nvmp_userinfotab.LoginStatus=1) "
				+ "union all (select nvmp_userinfotab.UserID,nvmp_userinfotab.UserNickName,  "
				+ " \"0\" as DeviceID,0 as WorkStatus,0 as IsOnline,"
				+ " \"\" as CommandTeamID,0 as CommandStatus from nvmp_userinfotab where nvmp_userinfotab.LoginStatus=0)";
		System.out.println("��ȡ������GetAllPerson:" + strsql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = ConnDo.getConnDo().executeQuery(strsql);
			// System.out.println(lst);
			return lst;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
*/
	/**
	 * ָ�ӵ��ȷ���
	 * */
	static public java.util.List<HashMap<String, String>> GetCommandGroup() {
		String map= ConvertMapUtil.map(NvmpCommandteamtab.class);
		String strSql = "select "+map+" from NvmpCommandteamtab where teamType=1";
		System.out.println("ָ�ӵ��ȷ���GetCommandGroup:===" + strSql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst =Hibernate.getHibernate().createQuery(strSql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ָ�ӵ��ȷ���
	 * */

	static public java.util.List<HashMap<String, String>> GetCommandGrouper(
			String CommandGroupId) {
		String map= ConvertMapUtil.map(NvmpCommandteamtab.class);
		String strSql = "select "+map+" from NvmpCommandgrouprelationtab where commandGroupId='"
				+ CommandGroupId + "'";
		System.out.println("ָ�ӵ��ȷ���GetCommandGrouper:===" + strSql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = Hibernate.getHibernate().createQuery(strSql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��Ƶ������
	 * */
	static public java.util.List<HashMap<String, String>> GetListConferenceGroup() {
		// TODO Auto-generated method stub
		String strsql = "select new Map(a.meetingId as MeetingID,a.meetingName as MeetingName,a.meetingDesc as MeetingDesc,a.meetingSponsor as MeetingSponsor,a.meetingType as MeetingType,a.meetingStatus as MeetingStatus,a.spokesManId as SpokesmanID,a.isDiscussMode as IsDiscussMode)from NvmpNetmeetingtab a,NvmpCommandteamtab b where a.meetingId= b.teamId and b.teamType=2";
		System.out.println("��Ƶ������GetListConferenceGroup:===" + strsql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = Hibernate.getHibernate().createQuery(strsql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		DBAccess access=new DBAccess();
		access.GetListConferenceGroup();
	}
	

	/**
	 * ��Ƶ������
	 * */
	static public java.util.List<HashMap<String, String>> GetConferenceGrouper(
			String ConferenceGroupID) {

		String strsql = "select new Map(c.userId as UserID,c.userName as UserName,c.userNickName as  UserNickName,c.loginStatus as LoginStatus) from NvmpCommandgroupuserrelationtab a , NvmpUserinfotab c where a.userId=c.userId and a.commandGroupId='"
				+ ConferenceGroupID + "'";
		System.out.println("��Ƶ������GetConferenceGrouper:===" + strsql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = Hibernate.getHibernate().createQuery(strsql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��Ƶ��ؽṹ
	 * */

	static public java.util.List<HashMap<String, String>> GetCameraGroupOrganizationNode() {
		String map=ConvertMapUtil.map(NvmpAreainfotab.class);
		String Strsql = "select "+map+" from NvmpAreainfotab";
		System.out
				.println("��Ƶ��ؽṹGetCameraGroupOrganizationNode:===" + Strsql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = Hibernate.getHibernate().createQuery(Strsql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��Ƶ�����
	 * */
	static public java.util.List<HashMap<String, String>> GetCameraGrouper(
			String ParentId) {
		String strsql = "select new Map(a.devname as Devname,a.devType as DevType,a.devModel as DevModel,a.areaId as AreaID ,b.isOnline as IsOnline )from NvmpVideodevtab a , NvmpDevsnmptab b where a.deviceId=b.deviceId and a.areaId='"
				+ ParentId + "'";
		System.out.println("��Ƶ�����GetCameraGrouper:===" + strsql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = Hibernate.getHibernate().createQuery(strsql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Զ�̻�����
	 * */
	static public java.util.List<HashMap<String, String>> GetConsultationGrouper(
			String ConsultationGroupID) {
		String strSql = "select new Map (c.userId as UserID,c.userName as UserName,c.userNickName as UserNickName,c.loginStatus as LoginStatus ) from NvmpCommandgroupuserrelationtab a , NvmpUserinfotab c where a.UserID=c.UserID and a.CommandGroupId='"
				+ ConsultationGroupID + "'";
		System.out.println("Զ�̻�����GetCameraGrouper:===" + strSql);
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = Hibernate.getHibernate().createQuery(strSql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ָ�ӵ��ȷ���
	 * */

	static public java.util.List<HashMap<String, String>> GetCommandGroupeRelation(
			String Id, int TypeInfo) {

		// select
		// ID,UserID,UserName,UserNickName,Password,OPLevel,DepartID,UserDesc,IsDisable,UserType,LoginStatus,LastLoginTime,RoleID,CenterID,UUID

		String strsql = "";

		if (TypeInfo == 2) {
			strsql = "select new Map (userNickName as UserNickName) from NvmpUserinfotab where userId='"
					+ Id + "'";
		} else if (TypeInfo == 1) {
			strsql = "select new Map (departName as DepartName) from NvmpDepartmenttab where departId='"
					+ Id + "' ";
		}
		java.util.List<HashMap<String, String>> lst;
		try {
			lst = Hibernate.getHibernate().createQuery(strsql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

/*	public static void main(String[] args) {
		// SetCommanderLogin("hsj001", "hsj", "192.168.1.101", false);
		// InitDB();
		// GetAllPerson();
		// GetCommandGroup();
		// GetListConferenceGroup();
		// GetCameraGroupOrganizationNode();
		// SystemBusiness sb = new SystemBusiness();
		// GetAllCommanderFromDB(sb);
		// GetAllCommandGroupFromDB(sb);
		// GetAllConferenceGroupFromDB(sb);
		// GetAllCameraGroupFromDB(sb);
		// GetAllSulatationGroupFromDB(sb);
		// GetConsultationGrouper();
	}*/
}
