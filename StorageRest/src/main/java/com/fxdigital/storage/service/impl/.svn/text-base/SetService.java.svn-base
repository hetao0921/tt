package com.fxdigital.storage.service.impl;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.syncclient.bean.NvmpRecordStorageInfo;
import com.fxdigital.syncclient.dao.NvmpRecordStorageInfoDao;

@Service
public class SetService {

	@Autowired
	private NvmpRecordStorageInfoDao nvmpRecordStorageInfoDao;


	/**
	 * 保存存储服务器信息
	 * 
	 * @param storageIp
	 * @param storagePort
	 */
	public void save(String storageIp, int storagePort) {
		NvmpRecordStorageInfo nvmpRecordStorageInfo = new NvmpRecordStorageInfo();
		nvmpRecordStorageInfo.setStorageip(storageIp);
		nvmpRecordStorageInfo.setStorageport(storagePort);
		String storageRtsp = "rtsp://" + storageIp + ":" + storagePort
				+ "/storage";
		nvmpRecordStorageInfo.setStoragertsp(storageRtsp);
		nvmpRecordStorageInfoDao.deleteAllObject();
		nvmpRecordStorageInfoDao.save(nvmpRecordStorageInfo);

	}

	/**
	 * 初始化存储服务器信息
	 */
	public void initStorageInfo() {
	
		String centerIp =getLocalIp();
		save(centerIp, 8001);
	}

	
	
	/**
	 * 获取本机IP地址
	 * @return
	 */
	public String getLocalIp() {
		String localip = "";
		Enumeration allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals("127.0.0.1")&&!ip.getHostAddress().equals("")) {
					System.out.println("本机的IP = " + ip.getHostAddress());
					localip=ip.getHostAddress();
				}
			}
		}

		return localip;
	}

	public static void main(String[] args) {
		SetService setService = new SetService();
		setService.getLocalIp();
	}
}
