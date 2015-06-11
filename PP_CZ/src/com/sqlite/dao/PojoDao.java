package com.sqlite.dao;

import java.util.List;
import java.util.Map;

import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.DeviceStatus;

public interface PojoDao {
     
      
     /**
      * ��ȡ�豸״̬
      * @param devId
      * @param channelId
      * @param typeid
      * @return
      */
     public int getStatus(String devId,int channelId,int typeid);
	
//     /**
//      * ��ȡ�豸ͨ����
//      * @param devId
//      * @param typeid
//      * @return
//      */
//     public String getChannel(String devId,int typeid);
     
     /**
      * ��ʼ����ݿ����Ϣ
      * @param url
      */
     public void init(String url,String dbname);
     
     /**
      * �����豸��Ϣ
      * @param bpo
      * @return
      */
     public boolean updateInfo(BasePO bpo);
     
     /**
      * �����豸��Ϣ
      * @param devId
      * @param channelId
      * @param devStatus
      * @param typeid
      * @return
      */
     public boolean updateInfo(String devId,int channelId,int devStatus,int typeid);
     
     /**
      * ��ѯ�������߻����ߵ��豸��Ϣ
      * @param status
      * @return
      */
     public List<DeviceStatus> getAllDeivceStatus(int status);
     
     /**
      * ������������������ѯ����Ӧ�����е��豸��Ϣ
      * @return
      */
     public List<Map<String,String>> getDeviceBySwitchSvrID(String switchSvrId);
     
     /**
      * ����豸ID������ID����ѯ��Ӧ��ͨ������
      * @param divID
      * @param typeId
      * @return
      */
     public List<BasePO> getBasePOByDeviceId(String divID,int typeId);
     
     
}
